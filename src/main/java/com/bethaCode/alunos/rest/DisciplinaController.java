package com.bethaCode.alunos.rest;

import com.bethaCode.alunos.model.entity.Aluno;
import com.bethaCode.alunos.model.entity.Disciplina;
import com.bethaCode.alunos.model.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin("http://localhost:4200")
public class DisciplinaController {

    private final DisciplinaRepository repository;

    @Autowired
    public DisciplinaController(DisciplinaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina salvar(@Valid @RequestBody Disciplina disciplina){
        return repository.save(disciplina);
    }

    @GetMapping
    public List<Disciplina> acharTodos(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Disciplina acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A Disciplina " + id + " não existe em nossa aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(disciplinaASerExcluida -> {
                    repository.delete(disciplinaASerExcluida);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A disciplina " + id + " não existe em nossa aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,@Valid @RequestBody Disciplina dadoDaRequisicao){
        repository
                .findById(id)
                .map(disciplina -> {
                    disciplina.setDescricao(dadoDaRequisicao.getDescricao());
                    disciplina.setNumeroHoras(dadoDaRequisicao.getNumeroHoras());
                    return repository.save(disciplina);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "A Disciplina " + id + " não existe em nossa aplicação!"));
    }
}
