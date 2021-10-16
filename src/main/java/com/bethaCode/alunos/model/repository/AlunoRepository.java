package com.bethaCode.alunos.model.repository;

import com.bethaCode.alunos.model.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}