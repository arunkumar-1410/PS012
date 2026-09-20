package com.klu.submission_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.submission_service.entity.Submission;
import com.klu.submission_service.repository.SubmissionRepository;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public Submission createSubmission(Submission submission) {
        submission.setStatus("SUBMITTED");
        submission.setScore(0);
        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    public Submission updateSubmission(long id, Submission submission) {
        Submission existing = getSubmissionById(id);

        existing.setStudentId(submission.getStudentId());
        existing.setExamId(submission.getExamId());
        existing.setAnswers(submission.getAnswers());
        existing.setScore(submission.getScore());
        existing.setStatus(submission.getStatus());

        return submissionRepository.save(existing);
    }

    public void deleteSubmission(long id) {
        Submission submission = getSubmissionById(id);
        submissionRepository.delete(submission);
    }
}