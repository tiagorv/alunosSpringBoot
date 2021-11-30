package com.bethaCode.alunos.model.repository;

import com.bethaCode.alunos.model.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

    @Query(" select N from Nota N join N.aluno A where upper(A.nome) like upper(:nome)")
    List<Nota> findByNomeAluno(@Param("nome") String nome);

}