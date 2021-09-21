package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.dto.setup.SetupDto;
import com.softtech.personalitytest.ptbackend.dto.setup.SetupQuestion;
import com.softtech.personalitytest.ptbackend.exception.SetupException;
import com.softtech.personalitytest.ptbackend.model.Category;
import com.softtech.personalitytest.ptbackend.model.Question;
import com.softtech.personalitytest.ptbackend.repository.CategoryRepository;
import com.softtech.personalitytest.ptbackend.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SetupService {

    private static final Logger logger = LoggerFactory.getLogger(SetupService.class);

    private static final String CONDITIONAL = "single_choice_conditional";

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    QuestionService questionService;

    public String setup(SetupDto setupDto) throws SetupException {
        String testId = UUID.randomUUID().toString();
        persistCategories(setupDto, testId);
        persistQuestions(setupDto, testId);
        return  testId;

    }

    public List<Question> persistQuestions(SetupDto setupDto, String testId) {
        Map<String,List<SetupQuestion>> map = generateMapByCategory(setupDto);
        List<Question> questions = new ArrayList<Question>();
        map.forEach((k,v)-> {questions.addAll(generateQuestionsList(v, testId));});
        questionService.saveAll(questions);
        return  questions;
    }

    private Map<String, List<SetupQuestion>> generateMapByCategory(SetupDto setupDto) {
        Map<String,List<SetupQuestion>> map = new HashMap<String,List<SetupQuestion>>();
        setupDto.getQuestions().forEach(q->{
            if(map.containsKey(q.category)){
                map.get(q.category).add(q);
            }else{
                List<SetupQuestion> list = new ArrayList<SetupQuestion>();
                list.add(q);
                map.put(q.getCategory(),list);
            }

        });
        return  map;
    }

    private Question constructQuestion(SetupQuestion q, int sequence, int conditionalSequence, String testId, boolean conditional) {
        // If Question has condition, we assume that we have a predicate
        // which contains control value that is in the 2^nd element of the list.
        String predicate = q.getQuestion_type().getCondition() != null ? q.getQuestion_type().getCondition().predicate.exactEquals.get(1) : null;
        return Question.builder().question(q.question).questionType(q.getQuestion_type().getType()).
                category(q.category).options(q.getQuestion_type().getOptions()).predicateValue(predicate).
                testId(testId).sequence(sequence).conditionalSequence(conditionalSequence).build();
    }

    private List<Question> generateQuestionsList(List<SetupQuestion> setupQuestions, String testId) {
        List<Question> questions = new ArrayList<Question>();

        AtomicInteger sequenceNum = new AtomicInteger(0);
        setupQuestions.forEach(sq -> {
            addToQuestionList(questions, sq,  sequenceNum.incrementAndGet(), 0, testId);
        });

        return questions;
    }

    private void addToQuestionList(List<Question> questions, SetupQuestion sq,  int lastSequence, int lastConditionalSequence, String testId) {

        // if this question is not a child of any conditional question, increase lastSequence.
        // Else don't increase lastSequence, but conditionalSequence
        if(lastConditionalSequence!=0)
             lastConditionalSequence++;

        if (CONDITIONAL.equals(sq.getQuestion_type().getType()) ) {

            // if we find any conditional first time, then set conditionalSequence as 1
            if(lastConditionalSequence==0) lastConditionalSequence=1;
            questions.add(constructQuestion(sq, lastSequence, lastConditionalSequence, testId,true));
            addToQuestionList(questions,sq.getQuestion_type().getCondition().if_positive, lastSequence,lastConditionalSequence,testId);
        } else {
            questions.add(constructQuestion(sq, lastSequence, lastConditionalSequence, testId,false));
        }
    }

    public List<Category> persistCategories(SetupDto setupDto, String testId) {
        List<Category> categoryList = new ArrayList<Category>();
        setupDto.getCategories().forEach(c -> {
            categoryList.add(Category.builder().testId(testId).name(c).build());
        });
        logger.info("Saving categories with size of : " + categoryList.size());
        categoryRepository.saveAll(categoryList);
        logger.info("Saved categories for test : " + testId);
        return categoryList;
    }
}
