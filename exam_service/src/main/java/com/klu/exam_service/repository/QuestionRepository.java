package com.klu.exam_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.exam_service.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}