package com.klu.exam_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.exam_service.entity.Exam;
import com.klu.exam_service.repository.ExamRepository;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    public Exam updateExam(long id, Exam exam) {

        Exam existingExam = getExamById(id);

        existingExam.setTitle(exam.getTitle());
        existingExam.setDescription(exam.getDescription());
        existingExam.setDate(exam.getDate());
        existingExam.setDurationMinutes(exam.getDurationMinutes());
        existingExam.setStatus(exam.isStatus());
        existingExam.setTotalMarks(exam.getTotalMarks());

        return examRepository.save(existingExam);
    }

    public void deleteExam(long id) {
        Exam exam = getExamById(id);
        examRepository.delete(exam);
    }
}