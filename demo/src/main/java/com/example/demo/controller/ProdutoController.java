package com.example.demo.controller;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.dto.ProdutoResponseDTO;
import com.example.demo.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "API de Produtos rodando!";
    }

    @PostMapping
    public ProdutoResponseDTO adicionar(@RequestBody ProdutoRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listarTodos() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    public ProdutoResponseDTO atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return service.atualizarParcial(id, campos);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        service.remover(id);
        return "Produto com id " + id + " removido com sucesso!";
    }
}