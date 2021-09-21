package com.mb.personalitytest.ptbackend.service;

import com.mb.personalitytest.ptbackend.exception.InvalidQuestionParametersException;
import com.mb.personalitytest.ptbackend.exception.NoQuestionFoundForTestException;
import com.mb.personalitytest.ptbackend.exception.QuestionNotAnsweredYetException;
import com.mb.personalitytest.ptbackend.helper.QuestionHelper;
import com.mb.personalitytest.ptbackend.model.Answer;
import com.mb.personalitytest.ptbackend.model.Question;
import com.mb.personalitytest.ptbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private static final String FIRST_NON_CONDITIONAL_INTERNAL_ID = "1-0";
    private static final String FIRST_CONDITIONAL_INTERNAL_ID = "1-1";
    private static final int FIRST_CONDITONAL_SEQUENCE_OF_NEXT_QUESTION = 1;
    private static final int FIRST_NON_CONDITONAL_SEQUENCE_OF_NEXT_QUESTION = 0;
    private static final String CONDITIONAL_TYPE = "single_choice_conditional";
    @Autowired
    QuestionRepository repository;

    @Autowired
    AnswerService answerService;

    @Autowired
    CachedQuestionService cachedService;

    /**
     * Fetch next question
     *
     * @param testId    id of the test
     * @param category  the category
     * @param sequence  it is like a specific id of question
     * @param csequence id of the sub question for conditional questions
     * @return
     */
    public Question fetchNextQuestion(String testId, String category, int sequence, int csequence) {
        if (sequence == 0) {
            return firstQuestion(testId, category);
        }

        Question currentQuestion = fetchQuestionMap(testId, category).get(QuestionHelper.generateInternalId(sequence, csequence));

        if (currentQuestion == null)
            throw new InvalidQuestionParametersException();

        //Answer internal id is being concatanating with category
        if (CONDITIONAL_TYPE.equals(currentQuestion.getQuestionType())) {
            Answer answer = answerService.findAnswerOfTheQuestion(testId, currentQuestion.getCategory() + "-" + QuestionHelper.generateInternalId(sequence, csequence));
            if (answer == null) {
                throw new QuestionNotAnsweredYetException();
            }
            if (answer.getAnswer().equals(currentQuestion.getPredicateValue())) {
                // go to condtional question
                return fetchQuestionMap(testId, category).get(QuestionHelper.generateInternalId(sequence, ++csequence));
            }

        }
        // increase to get next question
        ++sequence;
        // go to next non-conditional question
        Question nextQuestion = fetchQuestionMap(testId, category).get(QuestionHelper.generateInternalId(sequence, FIRST_NON_CONDITONAL_SEQUENCE_OF_NEXT_QUESTION));
        if (nextQuestion != null) return nextQuestion;
        else {
            // check next conditional question
            nextQuestion = fetchQuestionMap(testId, category).get(QuestionHelper.generateInternalId(sequence, FIRST_CONDITONAL_SEQUENCE_OF_NEXT_QUESTION));
            if (nextQuestion != null) return nextQuestion;
            else
                return null;
        }

    }

    public Map<String, Question> fetchQuestionMap(String testId, String category) {
        return cachedService.fetchQuestionMap(testId,category);
    }

        private Question firstQuestion(String testId, String category) {
        // Check  non-conditional question which is first
        Question question = fetchQuestionMap(testId, category).get(FIRST_NON_CONDITIONAL_INTERNAL_ID);
        if (question == null) {
            // Check conditional question which is first
            question = fetchQuestionMap(testId, category).get(FIRST_CONDITIONAL_INTERNAL_ID);
        }

        if (question == null) {
            throw new NoQuestionFoundForTestException(testId, category);
        }
        return question;

    }


    public void saveAll(List<Question> questions) {
        repository.saveAll(questions);
    }

    public List<Question> findAllQuestions(String testId) {
        return repository.findQuestionsByTestId(testId);
    }


}
