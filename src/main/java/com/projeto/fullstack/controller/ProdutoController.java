package com.projeto.fullstack.controller;

import com.projeto.fullstack.model.Produto;
import com.projeto.fullstack.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable String id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable String id, @Valid @RequestBody Produto dados) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(dados.getNome());
                    produto.setDescricao(dados.getDescricao());
                    produto.setPreco(dados.getPreco());
                    produto.setQuantidade(dados.getQuantidade());
                    return ResponseEntity.ok(repository.save(produto));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}