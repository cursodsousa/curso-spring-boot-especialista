package io.github.dougllasfps.rest.controller;

import io.github.dougllasfps.domain.entity.Cliente;
import io.github.dougllasfps.domain.entity.Produto;
import io.github.dougllasfps.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController( Produtos produtos ) {
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save( @RequestBody Produto produto){
        return produtos.save(produto);
    }

    @GetMapping("{id}")
    public Produto getById( @PathVariable Integer id ){
        return produtos
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void update(
            @PathVariable Integer id, @RequestBody Produto produto){
        produtos
            .findById(id)
            .map( produtoEncontrado -> {
                produto.setId(id);
                produtos.save(produto);
                return produto;
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos
            .findById(id)
            .map( produtoEncontrado -> {
                produtos.deleteById(id);
                return Void.TYPE;
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Produto> find( Produto produto ){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> example = Example.of(produto, matcher);
        return produtos.findAll(example);
    }
}
