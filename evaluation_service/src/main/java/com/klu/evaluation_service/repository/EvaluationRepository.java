package com.klu.evaluation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.evaluation_service.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

}