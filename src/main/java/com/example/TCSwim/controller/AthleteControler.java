package com.example.TCSwim.controller;


import com.example.TCSwim.dto.AthleteDto;
import com.example.TCSwim.model.Athlete;
import com.example.TCSwim.repository.AthleteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class AthleteControler {
    //@Autowired
    //BemDisponivelRepository bemDisponivelRepository;
    @Autowired
    AthleteRepository athleteRepository;
    @PostMapping("/athlete")
    public ResponseEntity<Athlete> saveBem(@RequestBody @Valid AthleteDto athleteDto){
        var athleteModel = new Athlete();
//        var respCoach = athleteRepository.findById(athleteDto.idClub());
//        if (respCoach.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        BeanUtils.copyProperties(athleteDto, athleteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(athleteRepository.save(athleteModel));
    }



    @GetMapping("/athlete")
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        return ResponseEntity.status(HttpStatus.OK).body((athleteRepository.findAll()));
    }

    @GetMapping("/athlete/{id}")
    public ResponseEntity<Object> getBemById(@PathVariable(value = "id") Long id) {
        Optional<Athlete> athlete = athleteRepository.findById(id);
        return athlete.<ResponseEntity<Object>>map(athlete1 -> ResponseEntity.status(HttpStatus.OK)
                .body(athlete1)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Atleta não encontrado!"));
    }
/*
    @PutMapping("/bens/{id}")
    public ResponseEntity<Object> updateBemDisponivel(@PathVariable(value = "id") Long id,
                                                      @RequestBody @Valid BemDisponivelRecordDto bemDisponivelRecordDto){
        var bem = bemDisponivelRepository.findById(id);
        var categoria = categoriaRepository.findById(bemDisponivelRecordDto.categoriaId());
        if(bem.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Bem disponível não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        if (categoria.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Categoria não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        var bemModel = bem.get();
        BeanUtils.copyProperties(bemDisponivelRecordDto, bemModel);
        bemModel.setCategoria(categoria.get());
        return ResponseEntity.status(HttpStatus.OK).body(bemDisponivelRepository.save(bemModel));
    }

    @PatchMapping("/bens/{id}")
    public ResponseEntity<Object> patchBemDisponivel(@PathVariable(value = "id") Long id,
                                                     @RequestBody JsonNode bemDisponivel){
        var bemOptional = bemDisponivelRepository.findById(id);
        if(bemOptional.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Bem disponível não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        var bem = bemOptional.get();
        var codigo = bemDisponivel.has("codigo") ? bemDisponivel.get("codigo").asText() : bem.getCodigo();
        var descricao = bemDisponivel.has("descricao") ? bemDisponivel.get("descricao").asText() : bem.getDescricao();
        var observacao = bemDisponivel.has("observacao") ? bemDisponivel.get("observacao").asText() : bem.getObservacao();
        var departamento = bemDisponivel.has("departamento") ? bemDisponivel.get("departamento").asText() : bem.getDepartamento();
        var ramal = bemDisponivel.has("ramal") ? bemDisponivel.get("ramal").asText() : bem.getRamal();
        var categoriaId = bemDisponivel.has("categoriaId") ? bemDisponivel.get("categoriaId").asLong() : bem.getCategoria().getIdCategoria();
        var categoria = categoriaRepository.findById(categoriaId);
        if (categoria.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Categoria não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        bem.setCodigo(codigo);
        bem.setDescricao(descricao);
        bem.setObservacao(observacao);
        bem.setDepartamento(departamento);
        bem.setRamal(ramal);
        return ResponseEntity.status(HttpStatus.OK).body(bemDisponivelRepository.save(bem));
    }

    @DeleteMapping("/bens/{id}")
    public ResponseEntity<Object> deleteBem(@PathVariable(value = "id") Long id){
        Optional<BemDisponivel> bem = bemDisponivelRepository.findById(id);
        if(bem.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Bem disponível não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        bemDisponivelRepository.delete(bem.get());
        var map = new HashMap<String, String>();
        map.put("message", "Bem disponível encontrado");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
 */
}

