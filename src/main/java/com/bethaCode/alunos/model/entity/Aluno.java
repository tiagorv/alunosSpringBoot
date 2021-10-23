package com.bethaCode.alunos.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter@Setter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo nome deve ser informado para o Aluno!")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column
    @Min(value = 15, message = "O Aluno deve ter idade mínima de 15 anos!")
    private Integer idade;

    @Column(name = "data_matricula")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataMatricula;

    @Column(length = 10)
    private String numero;

    @Column(length = 100)
    private String rua;

    @Column(length = 8)
    private String cep;

    @Column(length = 60)
    private String bairro;

    @Column(length = 60)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @PrePersist
    public void prePersiste(){
        setDataMatricula(LocalDate.now());
    }
}