package com.bethaCode.alunos.model.repository;

import com.bethaCode.alunos.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}