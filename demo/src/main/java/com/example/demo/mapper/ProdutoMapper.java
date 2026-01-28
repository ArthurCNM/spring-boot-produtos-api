package com.example.demo.mapper;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.dto.ProdutoResponseDTO;
import com.example.demo.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {


    public Produto toEntity(ProdutoRequestDTO dto) {
        return new Produto(null, dto.nome(), dto.preco());
    }


    public ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }
}