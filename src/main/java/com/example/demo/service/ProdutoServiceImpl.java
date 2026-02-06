package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProdutoMapper;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.service.validation.ValidadorProduto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final List<ValidadorProduto> validadores; // OCP: Lista de regras

    public ProdutoServiceImpl(ProdutoRepository repository, ProdutoMapper mapper, List<ValidadorProduto> validadores) {
        this.repository = repository;
        this.mapper = mapper;
        this.validadores = validadores;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        validadores.forEach(v -> v.validar(dto));
        Produto produto = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(produto));
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        return mapper.toDTO(repository.save(produto));
    }

    @Override
    public ProdutoResponseDTO atualizarParcial(Long id, Map<String, Object> campos) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        campos.forEach((chave, valor) -> {
            if (chave.equals("nome")) produto.setNome((String) valor);
            if (chave.equals("preco") && valor instanceof Number n) produto.setPreco(n.doubleValue());
        });

        return mapper.toDTO(repository.save(produto));
    }

    @Override
    public void remover(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("Produto não encontrado");
        repository.deleteById(id);
    }
}