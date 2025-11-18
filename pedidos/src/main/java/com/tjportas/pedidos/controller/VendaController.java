package com.tjportas.pedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tjportas.pedidos.entity.Venda;
import com.tjportas.pedidos.repository.VendaRepository;

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
public class VendaController {

    @Autowired
    VendaRepository repository;

    // Create - Post
    @PostMapping("/Venda")
    public ResponseEntity<Venda> salvar(@RequestBody Venda venda) {
        Venda vendaSalva = repository.save(venda);

        return new ResponseEntity<>(vendaSalva, HttpStatus.OK);
    }

    // Read - Get
    @GetMapping("/venda")
    public ResponseEntity<List<Venda>> listar() {
        List<Venda> venda = new ArrayList<>();
        repository.findAll().forEach(venda::add);

        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

    // Update - Put
    @PutMapping("/venda/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {

        Optional<Venda> vendaDesatualizada = repository.findById(id);

        if (vendaDesatualizada.isPresent()) {
            Venda vendaAtualizada = vendaDesatualizada.get();
            vendaAtualizada.setDatavenda(venda.getDatavenda());
            vendaAtualizada.setValorVenda(venda.getValorVenda());

            repository.save(vendaAtualizada);
            return new ResponseEntity<>(vendaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - Delete
    @DeleteMapping("/venda/{id}")
    public ResponseEntity<Venda> deletar(@PathVariable Long id) {

        repository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
