package com.bethaCode.alunos.model.repository;

import com.bethaCode.alunos.model.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
}
