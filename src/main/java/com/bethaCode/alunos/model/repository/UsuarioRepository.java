package com.bethaCode.alunos.model.repository;

import com.bethaCode.alunos.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}