package com.klu.submission_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.submission_service.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}