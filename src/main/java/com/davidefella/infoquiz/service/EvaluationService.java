package com.davidefella.infoquiz.service;

import com.davidefella.infoquiz.model.persistence.Evaluation;
import com.davidefella.infoquiz.model.persistence.users.Teacher;
import com.davidefella.infoquiz.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<Evaluation> saveAll(List<Evaluation> evaluations) {
        return evaluationRepository.saveAll(evaluations);
    }

    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> findByUUID(UUID uuid) {
        return evaluationRepository.findByUuid(uuid);
    }

    public List<Evaluation> findByAssignedTeacher(Teacher teacher) {
        return evaluationRepository.findByAssignedTeacher(teacher);
    }

    public List<Evaluation> findByEmailAssignedTeacher(String email) {
        return evaluationRepository.findByAssignedTeacherEmail(email);
    }

}

