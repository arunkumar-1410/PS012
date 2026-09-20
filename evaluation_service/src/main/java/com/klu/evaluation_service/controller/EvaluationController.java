package com.klu.evaluation_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.evaluation_service.entity.Evaluation;
import com.klu.evaluation_service.service.EvaluationService;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(
            @RequestBody Evaluation evaluation) {

        return new ResponseEntity<>(
                evaluationService.createEvaluation(evaluation),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {

        return ResponseEntity.ok(
                evaluationService.getAllEvaluations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(
            @PathVariable long id) {

        return ResponseEntity.ok(
                evaluationService.getEvaluationById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(
            @PathVariable long id,
            @RequestBody Evaluation evaluation) {

        return ResponseEntity.ok(
                evaluationService.updateEvaluation(id, evaluation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(
            @PathVariable long id) {

        evaluationService.deleteEvaluation(id);

        return ResponseEntity.noContent().build();
    }
}