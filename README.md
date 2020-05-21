# Transaction Demo API

## Stack:

- Java 11
- Spring Boot 2.2.1
- Postgres

## Estrutura de pacotes principais:

|            Pacote               |                 Descrição                         |
|---------------------------------|:-------------------------------------------------:|
|  com.example.demo.controllers   |  Endpoints para consumo                           |
|  com.example.demo.interactions  |  Caso de uso atendido pelo projeto                |
|  com.example.demo.repositories  |  Lógica para manipulação de dados no banco        |

## Como configurar o ambiente de desenvolvimento

1. Instalar banco de dados mysql conforme sua versão de SO;
2. Criar o banco de dados transaction (Utilizar usuário root e senha root)
3. Dentro da pasta raiz do projeto, executar o seguinte comando no terminal
 `mvn spring-boot:run`
4. Para teste dos endpoints, utilizar a url do swagger:
`http://localhost:8080/swagger-ui.html`
 
## Rodar o projeto usando docker-compose
`docker-compose up`
