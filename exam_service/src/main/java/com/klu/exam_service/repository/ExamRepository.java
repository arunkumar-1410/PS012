package com.klu.exam_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.exam_service.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
