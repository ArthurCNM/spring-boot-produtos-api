# API de Gerenciamento de Produtos 📦

Esta é uma API REST iniciada durante o **Bootcamp Java Deloitte** para gerenciar um catálogo de produtos. O projeto utiliza **Spring Boot** e demonstra operações básicas de CRUD (Create, Read, Update, Delete) com manipulação de dados em memória.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gerenciador de dependências)
* **Postman** (Para testes de API)

## 🛠️ Funcionalidades

* **Listar todos os produtos**: Retorna uma lista JSON com todos os itens cadastrados.
* **Buscar por ID**: Localiza um produto específico através do seu identificador único.
* **Adicionar produto**: Registra um novo produto enviando um JSON via POST.
* **Remover produto**: Exclui um produto do catálogo pelo ID.

Método,Endpoint,Descrição
GET,/produtos,Retorna todos os produtos
GET,/produtos/{id},Retorna um produto por ID
POST,/produtos,Adiciona um novo produto
DELETE,/produtos/{id},Remove um produto por ID
