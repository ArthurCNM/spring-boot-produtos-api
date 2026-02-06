package com.example.demo.service.validation;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.exception.BusinessException;

public interface ValidadorProduto {
    void validar(ProdutoRequestDTO dto);
}

//Nome do produto não pode ser vazio
@org.springframework.stereotype.Component
class ValidadorNome implements ValidadorProduto{
    @Override
    public void validar(ProdutoRequestDTO dto){
        if (dto.nome() ==null || dto.nome().isBlank()){
            throw new BusinessException("Nome do produto não pode ser vazio");}
    }
}

// Preço não pode ser negativo
@org.springframework.stereotype.Component
class ValidadorPreco implements ValidadorProduto {
    @Override
    public void validar(ProdutoRequestDTO dto) {
        if (dto.preco() < 0) throw new BusinessException("Preço não pode ser negativo");
    }
}

