
package com.tjportas.pedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tjportas.pedidos.entity.Devolucao;
import com.tjportas.pedidos.repository.DevolucaoRepository;

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
public class DevolucaoController {

    @Autowired
    DevolucaoRepository repository;

    // Create - Post
    @PostMapping("/devolucoes")
    public ResponseEntity<Devolucao> salvar(@RequestBody Devolucao devolucao) {
        Devolucao devolucaoSalva = repository.save(devolucao);

        return new ResponseEntity<>(devolucaoSalva, HttpStatus.OK);
    }

    // Read - Get
    @GetMapping("/devolucoes")
    public ResponseEntity<List<Devolucao>> listar() {
        List<Devolucao> devolucoes = new ArrayList<>();
        repository.findAll().forEach(devolucoes::add);

        return new ResponseEntity<>(devolucoes, HttpStatus.OK);
    }

    // Update - Put
    @PutMapping("/devolucao/{id}")
    public ResponseEntity<Devolucao> atualizar(@PathVariable Long id, @RequestBody Devolucao devolucao) {

        Optional<Devolucao> devolucaoDesatualizada = repository.findById(id);

        if (devolucaoDesatualizada.isPresent()) {
            Devolucao devolucaoAtualizada = devolucaoDesatualizada.get();
            devolucaoAtualizada.setProduto(devolucao.getProduto());
            devolucaoAtualizada.setMotivo(devolucao.getMotivo());
            devolucaoAtualizada.setDataDevolucao(devolucao.getDataDevolucao());

            repository.save(devolucaoAtualizada);
            return new ResponseEntity<>(devolucaoAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - Delete
    @DeleteMapping("/devolucoes/{id}")
    public ResponseEntity<Devolucao> deletar(@PathVariable Long id) {

        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
