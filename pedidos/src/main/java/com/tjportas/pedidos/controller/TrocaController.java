package com.tjportas.pedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tjportas.pedidos.entity.Troca;
import com.tjportas.pedidos.repository.TrocaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TrocaController {

    @Autowired
    TrocaRepository repository;

    // Create - Post
    @PostMapping("/troca")
    public ResponseEntity<Troca> salvar(@RequestBody Troca troca) {
        Troca trocaSalva = repository.save(troca);

        return new ResponseEntity<>(trocaSalva, HttpStatus.OK);
    }

    // Read - Get
    @GetMapping("/troca")
    public ResponseEntity<List<Troca>> listar() {
        List<Troca> trocas = new ArrayList<>();
        repository.findAll().forEach(trocas::add);

        return new ResponseEntity<>(trocas, HttpStatus.OK);
    }

    // Update - Put
    @PutMapping("/troca/{id}")
    public ResponseEntity<Troca> atualizar(@PathVariable Long id, @RequestBody Troca troca) {

        Optional<Troca> trocaExistente = repository.findById(id);

        if (trocaExistente.isPresent()) {
            Troca trocaAtualizado = trocaExistente.get();
            trocaAtualizado.setDataTroca(troca.getDataTroca());
            trocaAtualizado.setMotivo(troca.getMotivo());
            trocaAtualizado.setTroca(troca.getTroca());

            repository.save(trocaAtualizado);
            return new ResponseEntity<>(trocaAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - Delete
    @DeleteMapping("/troca/{id}")
    public ResponseEntity<Troca> deletar(@PathVariable Long id) {

        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
