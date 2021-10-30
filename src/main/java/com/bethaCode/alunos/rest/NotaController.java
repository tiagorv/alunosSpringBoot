package com.bethaCode.alunos.rest;

import com.bethaCode.alunos.model.dto.NotaDTO;
import com.bethaCode.alunos.model.entity.Aluno;
import com.bethaCode.alunos.model.entity.Disciplina;
import com.bethaCode.alunos.model.entity.Nota;
import com.bethaCode.alunos.model.repository.AlunoRepository;
import com.bethaCode.alunos.model.repository.DisciplinaRepository;
import com.bethaCode.alunos.model.repository.NotaRepository;
import com.bethaCode.alunos.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController{

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nota salvar(@RequestBody NotaDTO notaDTO){

        LocalDate dataDaNota = LocalDate.parse(
                                    notaDTO.getDataNota(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        BigDecimal valorDaNota = bigDecimalConverter.converter(notaDTO.getNota());

        Integer idAluno = notaDTO.getIdAluno();
        Aluno valorDoAluno = alunoRepository
                            .findById(idAluno)
                            .orElseThrow(()-> new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "O aluno " + idAluno + " não existe em nossa app!"
                            ));

        Integer idDisciplina = notaDTO.getIdDisciplina();
        Disciplina valorDaDisciplina = disciplinaRepository
                                            .findById(idDisciplina)
                                            .orElseThrow(()-> new ResponseStatusException(
                                                    HttpStatus.BAD_REQUEST,
                                                    "A Disciplina " + idDisciplina + " não existe em nosso app!"
                                            ));

        Nota notaASalvar = new Nota();
        notaASalvar.setDataNota(dataDaNota);
        notaASalvar.setNota(valorDaNota);
        notaASalvar.setAluno(valorDoAluno);
        notaASalvar.setDisciplina(valorDaDisciplina);
        return notaRepository.save(notaASalvar);
    }

    @GetMapping("{id}")
    public Nota acharPorId(@PathVariable Integer id){
        return notaRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A nota " + id + " não existe em nosso app!"
                ));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        notaRepository
                .findById(id)
                .map(
                        notaListada -> {
                            notaRepository.delete(notaListada);
                            return Void.TYPE;
                        }
                ).orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A nota " + id + " não existe em nosso app"
        ));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody NotaDTO dto){

        //Pegamos a data do DTO e convertemos em LocalDate
        LocalDate dataDaNota = LocalDate.parse(
                dto.getDataNota(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );

        //Tira a (, virgula) da casa decimal e troca por (. ponto)
        BigDecimal valorDaNota = bigDecimalConverter.converter(dto.getNota());

        Integer idAluno = dto.getIdAluno();
        Aluno alunoDoBanco = alunoRepository
                                .findById(idAluno)
                                .orElseThrow(()-> new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "O Aluno " + idAluno + " não existe em nossa app."
                                ));

        Integer idDisciplina = dto.getIdDisciplina();
        Disciplina discipNoBanco = disciplinaRepository
                                        .findById(idDisciplina)
                                        .orElseThrow(()-> new ResponseStatusException(
                                                HttpStatus.BAD_REQUEST,
                                                "A disciplina " + idDisciplina + " não existe em nossa app!"
                                        ));

        notaRepository
             .findById(id)
             .map(notaCadastrada -> {
                 notaCadastrada.setNota(valorDaNota);
                 notaCadastrada.setDataNota(dataDaNota);
                 notaCadastrada.setAluno(alunoDoBanco);
                 notaCadastrada.setDisciplina(discipNoBanco);
                 return notaRepository.save(notaCadastrada);
             }).orElseThrow(()-> new ResponseStatusException(
                     HttpStatus.BAD_REQUEST,
                     "A nota " + id + " não existe em nossa app!"
        ));

    }
}
