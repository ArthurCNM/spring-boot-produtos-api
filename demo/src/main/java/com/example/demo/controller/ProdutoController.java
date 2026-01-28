package com.example.demo.controller;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.dto.ProdutoResponseDTO;
import com.example.demo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("API de Produtos rodando!"); // Status 200 (OK)
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO novoProduto = service.salvar(dto);
        // Status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        List<ProdutoResponseDTO> lista = service.listar();
        return ResponseEntity.ok(lista); // Status 200 (OK)
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProdutoResponseDTO produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto); // Status 200 (OK)
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado); // Status 200 (OK)
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        ProdutoResponseDTO atualizado = service.atualizarParcial(id, campos);
        return ResponseEntity.ok(atualizado); // Status 200 (OK)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.remover(id);
        // Status 204 No Content: Remoções bem-sucedidas
        return ResponseEntity.noContent().build();
    }
}