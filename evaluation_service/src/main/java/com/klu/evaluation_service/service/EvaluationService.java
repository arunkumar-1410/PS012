package com.klu.evaluation_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.evaluation_service.entity.Evaluation;
import com.klu.evaluation_service.repository.EvaluationRepository;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation createEvaluation(Evaluation evaluation) {
        evaluation.setStatus("EVALUATED");
        return evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluationById(long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }

    public Evaluation updateEvaluation(long id, Evaluation evaluation) {
        Evaluation existing = getEvaluationById(id);

        existing.setSubmissionId(evaluation.getSubmissionId());
        existing.setExamId(evaluation.getExamId());
        existing.setStudentId(evaluation.getStudentId());
        existing.setTotalMarks(evaluation.getTotalMarks());
        existing.setObtainedMarks(evaluation.getObtainedMarks());
        existing.setStatus(evaluation.getStatus());

        return evaluationRepository.save(existing);
    }

    public void deleteEvaluation(long id) {
        Evaluation evaluation = getEvaluationById(id);
        evaluationRepository.delete(evaluation);
    }
}