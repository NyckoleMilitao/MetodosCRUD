package com.projeto.java1.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projeto.java1.domain.Produto;
import com.projeto.java1.domain.RequestProductDTO;
import com.projeto.java1.repositories.ProdutoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public List<Produto> listar()
    {
        return produtoRepository.findAll();
        
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarId(@PathVariable Long id)
    {
        
        return produtoRepository.findById(id).map(produto -> ResponseEntity.ok().body(produto)).orElse(ResponseEntity.notFound().build());

    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> adicionar (@RequestBody @Valid RequestProductDTO data)
    {
        Produto nProduto = new Produto(data);
        produtoRepository.save(nProduto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> putProduto(@PathVariable Long id, @RequestBody @Valid RequestProductDTO data) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(data.nome());
            produto.setPreco(data.preco());
            produto.setDescricao(data.descricao());

            Produto updatedProduto = produtoRepository.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id){

        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
            
        }
    }
}
