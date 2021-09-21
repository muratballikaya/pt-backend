package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.exception.NoQuestionFoundForTestException;
import com.softtech.personalitytest.ptbackend.model.Question;
import com.softtech.personalitytest.ptbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CachedQuestionService {

    @Autowired
    QuestionRepository repository;

    /**
     * key: internal id (if we assume sequence = 1 and conditional sequence = 0 then internal id is 1-0)
     * value : Question
     * @param testId
     * @param category
     * @return
     */
    @Cacheable("questionCache")
    public Map<String, Question> fetchQuestionMap(String testId, String category) {
        List<Question> questionList = repository.findQuestions(testId, category);

        if (questionList == null || questionList.isEmpty()) {
            throw new NoQuestionFoundForTestException(testId, category);
        }

        return questionList.stream().collect(Collectors.toMap(Question::internalId, Question::getItSelf));
    }
}
