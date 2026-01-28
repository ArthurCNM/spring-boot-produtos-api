package com.example.demo.service.validation;

import com.example.demo.dto.ProdutoRequestDTO;

public interface ServiceValidation {
    void validar(ProdutoRequestDTO dto);
}

// Preço não pode ser negativo
@org.springframework.stereotype.Component
class ValidadorPreco implements ServiceValidation {
    @Override
    public void validar(ProdutoRequestDTO dto) {
        if (dto.preco() < 0) throw new RuntimeException("Preço não pode ser negativo");
    }
}