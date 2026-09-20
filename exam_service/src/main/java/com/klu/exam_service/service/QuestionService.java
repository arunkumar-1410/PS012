package com.klu.exam_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.exam_service.entity.Question;
import com.klu.exam_service.repository.QuestionRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public Question updateQuestion(long id, Question question) {

        Question existingQuestion = getQuestionById(id);

        existingQuestion.setQuestionText(question.getQuestionText());
        existingQuestion.setOptionA(question.getOptionA());
        existingQuestion.setOptionB(question.getOptionB());
        existingQuestion.setOptionC(question.getOptionC());
        existingQuestion.setOptionD(question.getOptionD());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        existingQuestion.setMarks(question.getMarks());

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }
}