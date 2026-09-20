package com.klu.submission_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.submission_service.entity.Submission;
import com.klu.submission_service.service.SubmissionService;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(
            @RequestBody Submission submission) {

        return new ResponseEntity<>(
                submissionService.createSubmission(submission),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions() {

        return ResponseEntity.ok(
                submissionService.getAllSubmissions()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(
            @PathVariable long id) {

        return ResponseEntity.ok(
                submissionService.getSubmissionById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(
            @PathVariable long id,
            @RequestBody Submission submission) {

        return ResponseEntity.ok(
                submissionService.updateSubmission(id, submission)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(
            @PathVariable long id) {

        submissionService.deleteSubmission(id);

        return ResponseEntity.noContent().build();
    }
}