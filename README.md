# API de Gerenciamento de Produtos 📦

Esta é uma API REST desenvolvida durante o **Bootcamp Java Deloitte**. O projeto evoluiu de uma estrutura básica para uma aplicação de nível de produção, aplicando princípios de boas práticas de arquitetura e os princípios do SOLID, persistência de dados e automação de deploy na nuvem.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.2.2**
* **Spring Data JPA** (Persistência de dados)
* **H2 Database** (Banco de dados em memória)
* **Maven** (Gerenciador de dependências)
* **SpringDoc OpenAPI (Swagger)** (Documentação da API)
* **GitHub Actions** (Pipeline de CI/CD)
* **Postman** (Para testes de API)
* **Microsoft Azure** (Hospedagem em nuvem)

### 📂 Estrutura de Pastas e Camadas

A arquitetura do projeto segue o padrão de camadas do Spring Boot, garantindo a separação de responsabilidades:

```text
src/main/java/com/example/demo/
├── controller/    # Camada de entrada (Endpoints REST)
├── dto/           # Objetos de transferência de dados (Request/Response)
├── exception/     # Tratamento global de erros e exceções customizadas
├── mapper/        # Conversão entre Entidades e DTOs
├── model/         # Entidades persistidas no banco de dados
├── repository/    # Interface de acesso ao banco (Spring Data JPA)
└── service/       # Regras de negócio e lógica da aplicação
│   └── validation/ # Implementação de validadores baseados em SOLID
├── DemoApplication.java # Classe principal que inicia o Spring Boot
└── OpenAPIConfig.java   # Configurações personalizadas do Swagger
```
## Boas Práticas e Arquitetura
* **Princípios SOLID**
* **Tratamento Global de Exceções:** Uso de `@RestControllerAdvice` para capturar e tratar erros de forma padronizada, retornando códigos HTTP apropriados:
    * **400 (Bad Request):** Para violações de regras de negócio (BusinessException).
    * **404 (Not Found):** Para recursos não localizados (ResourceNotFoundException).
* **Camada de DTOs:** Isolamento da entidade de banco de dados da camada de apresentação para maior segurança e controle dos dados.

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

## 📖 Exemplos de Uso
1. Criar Produto (POST /produtos)

```json
{
  "nome": "Notebook Gamer",
  "preco": 4500.00
}
```
2. Atualização Completa (PUT /produtos/{id})
 ```json
{
  "nome": "Mouse Gamer Wireless",
  "preco": 280.00
}     
```
3. Atualização Parcial (PATCH /produtos/{id})
 ```json
{
  "preco": 255.00
}
```

4. Resposta padrão para sucesso
```json
{
  "id": 1,
  "nome": "Mouse Gamer Wireless",
  "preco": 255.00
}
```
5. Resposta de erro
   _Exemplo de quando um ID não existe no banco de dados._
```json
{
  "timestamp": "2026-05-20T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Produto com ID 199 não encontrado",
  "path": "/produtos/199"
}
```   

## ☁️ Deploy e CI/CD
A aplicação conta com uma esteira automatizada de **Integração e Entrega Contínua (CI/CD)**:
* **CI:** Build e testes automatizados via GitHub Actions a cada push na branch `main`.
* **CD:** Deploy automático para o **Azure App Service** após o sucesso do build.
* **Link da API na Nuvem:** [https://bootcamp-dabrevb9agfba4gw.brazilsouth-01.azurewebsites.net/produtos](https://bootcamp-dabrevb9agfba4gw.brazilsouth-01.azurewebsites.net/produtos)

## 📚 Documentação da API (Swagger)
A documentação interativa está disponível no ambiente de produção: [https://bootcamp-dabrevb9agfba4gw.brazilsouth-01.azurewebsites.net/swagger-ui/index.html](https://bootcamp-dabrevb9agfba4gw.brazilsouth-01.azurewebsites.net/swagger-ui/index.html)

## ⚙️ Como Executar Localmente
### Passo a Passo
1. Clone o repositório:
   ```bash
   git clone https://github.com/ArthurCNM/spring-boot-produtos-api.git
2. Instale as dependências e compile o projeto:
   ```bash
   mvn clean install
3. Execute a aplicação <br>
   A API estará disponível em http://localhost:8080/produtos. <br>
   o H2 estará disponível em http://localhost:8080/h2-console. <br>
   o Swagger estará disponível em http://localhost:8080/swagger-ui/index.html#/. <br>

##🗄️ Tabela de Credenciais H2 Local
| Propriedade | Valor |
| :--- | :--- |
| **Interface Web** | `http://localhost:8080/h2-console` |
| **Driver class** | `org.h2.Driver` |
| **JDBC URL** | `jdbc:h2:mem:testdb` |
| **User Name** | `sa` |
| **Password** | *(vazio)* |
