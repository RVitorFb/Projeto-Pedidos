package com.tjportas.pedidos.controller;

import org.springframework.web.bind.annotation.RestController;
import com.tjportas.pedidos.repository.OrcamentoRepository;

import com.tjportas.pedidos.entity.Orcamento;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class OrcamentoController {

    @Autowired
    OrcamentoRepository repository;

    // Create - Post
    @PostMapping("/orcamento")
    public ResponseEntity<Orcamento> salvar(@RequestBody Orcamento orcamento) {
        Orcamento orcamentoSalvo = repository.save(orcamento);

        return new ResponseEntity<>(orcamentoSalvo, HttpStatus.OK);
    }

    // Read - Get
    @GetMapping("/orcamento")
    public ResponseEntity<List<Orcamento>> listar() {
        List<Orcamento> orcamentos = new ArrayList<>();
        repository.findAll().forEach(orcamentos::add);

        return new ResponseEntity<>(orcamentos, HttpStatus.OK);
    }

    // Update - Put
    @PutMapping("/orcamento/{id}")
    public ResponseEntity<Orcamento> atualizar(@PathVariable Long id, @RequestBody Orcamento orcamento) {

        Optional<Orcamento> orcamentoExistente = repository.findById(id);

        if (orcamentoExistente.isPresent()) {
            Orcamento orcamentoAtualizado = orcamentoExistente.get();
            orcamentoAtualizado.setDataOrcamento(orcamento.getDataOrcamento());
            orcamentoAtualizado.setValorTotal(orcamento.getValorTotal());
            orcamentoAtualizado.setCliente(orcamento.getCliente());

            repository.save(orcamentoAtualizado);
            return new ResponseEntity<>(orcamentoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - Delete
    @DeleteMapping("/orcamento/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Orcamento> orcamentoExistente = repository.findById(id);

        if (orcamentoExistente.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
