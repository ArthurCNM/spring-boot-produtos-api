package com.example.demo.service;

import com.example.demo.dto.ProdutoRequestDTO;
import com.example.demo.dto.ProdutoResponseDTO;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto(null, dto.nome(), dto.preco());
        return converterParaDTO(repository.save(produto));
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return converterParaDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());

        return converterParaDTO(repository.save(produto));
    }

    @Override
    public ProdutoResponseDTO atualizarParcial(Long id, Map<String, Object> campos) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        campos.forEach((chave, valor) -> {
            switch (chave) {
                case "nome" -> produto.setNome((String) valor);
                case "preco" -> {
                    if (valor instanceof Number n) {
                        produto.setPreco(n.doubleValue());
                    }
                }
            }
        });

        return converterParaDTO(repository.save(produto));
    }

    @Override
    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        repository.deleteById(id);
    }

    private ProdutoResponseDTO converterParaDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }
}