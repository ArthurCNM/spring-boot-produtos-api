# API de Gerenciamento de Produtos 📦

Esta é uma API REST iniciada durante o **Bootcamp Java Deloitte** para gerenciar um catálogo de produtos. O projeto utiliza **Spring Boot** e demonstra operações básicas de CRUD (Create, Read, Update, Delete) com manipulação de dados em memória.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4.0.2**
* **Spring Data JPA** (Persistência de dados)
* **H2 Database** (Banco de dados em memória)
* **Maven** (Gerenciador de dependências)
* **Postman** (Para testes de API)

## 🛠️ Funcionalidades

* **Listar todos os produtos**: Retorna uma lista JSON com todos os itens cadastrados.
* **Buscar por ID**: Localiza um produto específico através do seu identificador único.
* **Adicionar produto**: Registra um novo produto enviando um JSON via POST.
* **Atualizar produto**: (Completo): Substitui os dados de um produto existente via PUT.
* **Atualizar produto** (Parcial): Modifica campos específicos (nome ou preço) via PATCH.
* **Remover produto**: Exclui um produto do catálogo pelo ID.

## 📡 Endpoints da API 
* **GET /produtos/hello Endpoint de teste de status**
* **GET /produtos Retorna todos os produtos**
* **GET /produtos/{id} Retorna um produto por ID**
* **POST /produtos Adiciona um novo produto**
* **PUT /produtos/{id} Atualiza todos os dados do produto**
* **PATCH /produtos/{id} Atualiza campos parciais do produto**
* **DELETE /produtos/{id} Remove um produto por ID**

* **A API estará disponível em http://localhost:8080/produtos**
* **O console do banco H2 pode ser acessado em http://localhost:8080/h2-console (verificar configurações no application.properties).**
