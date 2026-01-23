package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

class Produto {
	private Long id;
	private String nome;
	private double preco;

	public Produto() {}

	public Produto(Long id, String nome, double preco){
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}

}

// CONTROLLER
@RestController
@RequestMapping("/produtos")
class ProdutoController{

	// Endpoint de verificação
	@GetMapping("/hello")
	public String hello() {
		return "API de Produtos rodando com Spring Boot";
	}

	private final List<Produto> produtos = new ArrayList<>(List.of(
			new Produto(1L,"Teclado mecânico",150.00)
	));

	// GET /produtos -> retorna a lista de produtos
	@GetMapping
	public List<Produto> listarTodos(){
		return produtos;
	}

	//GET /produtos/id -> retorna um produto pelo id
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Long id) {
		return produtos.stream()
				.filter(p -> p.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	//POST /produtos -> adiciona um produto
	@PostMapping
	public Produto adicionar(@RequestBody Produto produto){
		produtos.add(produto);
		return produto;
	}

	//DELETE /produtos/id -> remove um produto

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		boolean removido = produtos.removeIf(p -> p.getId().equals(id));
		return removido ? "Produto com id " + id + " removido!" : "Produto não encontrado.";
	}

}