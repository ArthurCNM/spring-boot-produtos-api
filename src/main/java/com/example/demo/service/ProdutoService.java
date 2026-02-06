package com.example.demo.service;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.dto.ProdutoResponseDTO;
import java.util.List;
import java.util.Map;

public interface ProdutoService {
    ProdutoResponseDTO salvar(ProdutoRequestDTO dto);
    List<ProdutoResponseDTO> listar();
    ProdutoResponseDTO buscarPorId(Long id);
    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto);
    ProdutoResponseDTO atualizarParcial(Long id, Map<String, Object> campos);
    void remover(Long id);
}