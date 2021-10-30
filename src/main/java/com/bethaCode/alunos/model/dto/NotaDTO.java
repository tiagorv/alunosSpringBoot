package com.bethaCode.alunos.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NotaDTO {

    private Integer idAluno;
    private Integer idDisciplina;
    private String nota;
    private String dataNota;

    public NotaDTO(){

    }
}
