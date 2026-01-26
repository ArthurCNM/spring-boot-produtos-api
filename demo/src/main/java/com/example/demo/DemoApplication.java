package com.example.demo;

import jakarta.persistence.*; // Importante para as anotações JPA
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Entity // Transforma a classe em uma entidade JPA
class Produto {
	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do id
	private Long id;
	private String nome;
	private double preco;

	public Produto() {}

	public Produto(Long id, String nome, double preco){
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	//GETTERS e SETTERS
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Double getPreco() {return preco;}
	public void setPreco(Double preco) {this.preco = preco;}

}

// --- CAMADA DE REPOSITÓRIO ---
@Repository
interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// Métodos save, findAll, findById, deleteById, etc.
}

// --- CONTROLLER ---
@RestController
@RequestMapping("/produtos")
class ProdutoController {

	private final ProdutoRepository repository;

	// Injeção de dependência via construtor
	public ProdutoController(ProdutoRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/hello")
	public String hello() {
		return "API de Produtos rodando com Spring Boot!";
	}

	// Listar produtos
	@GetMapping
	public List<Produto> listarTodos() {
		return repository.findAll();
	}

	// Buscar por ID
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Long id) {
		return repository.findById(id).orElse(null);
	}

	// Salvar produto
	@PostMapping
	public Produto adicionar(@RequestBody Produto produto) {
		return repository.save(produto);
	}

	// Atualizar produto
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
		return repository.findById(id).map(p -> {
			p.setNome(produtoAtualizado.getNome());
			p.setPreco(produtoAtualizado.getPreco());
			return repository.save(p);
		}).orElse(null);
	}
	// Atualizar produto
	@PatchMapping("/{id}")
	public Produto atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		return repository.findById(id).map(produto -> {
			// Se no JSON do Postman vier "nome", atualiza o nome
			if (campos.containsKey("nome")) {
				produto.setNome((String) campos.get("nome"));
			}
			// Se "preco", atualiza o preço
			if (campos.containsKey("preco")) {
				produto.setPreco(Double.parseDouble(campos.get("preco").toString()));
			}
			return repository.save(produto);
		}).orElse(null);
	}

	// Remover produto
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Produto com id " + id + " removido!";
		}
		return "Produto não encontrado.";
	}
}