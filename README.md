📚 Sistema de Gestão de Biblioteca (API REST)
Este projeto é uma API Web completa para gerenciamento de uma biblioteca, desenvolvida como requisito para a Avaliação de Recuperação da disciplina de Arquitetura e Desenvolvimento Back-end.


O sistema implementa o CRUD completo para cinco entidades interconectadas e gerencia regras de negócio complexas de empréstimos, seguindo padrões de projeto e boas práticas de arquitetura.


🚀 Tecnologias Utilizadas

Linguagem: Java 21 (JDK 17+)


Framework: Spring Boot


Banco de Dados: MySQL


ORM: Hibernate / JPA

Gerenciamento de Dependências: Maven


Ferramentas de Teste: Postman / Insomnia

🏗️ Arquitetura
O projeto foi estruturado seguindo a Arquitetura em 3 Camadas para garantir a coesão e o Clean Code:


Controller: Camada responsável por receber as requisições HTTP (REST) e enviar para o Service.


Service: Implementa a lógica de negócio complexa, validações e regras de empréstimo.


Repository: Responsável pelo acesso e manipulação de dados com o banco (ORM).

🗂️ Entidades e Relacionamentos
O sistema gerencia as seguintes entidades com seus respectivos relacionamentos:

Livro (1:N com Autor, N:N com Empréstimo)

Autor (1:N com Livro)

Usuário/Cliente (1:N com Empréstimo)

Funcionário (1:N com Empréstimo)

Empréstimo (Entidade central que conecta Usuário, Livro e Funcionário)

⚙️ Regras de Negócio Implementadas
A camada de serviço (Service Layer) valida as seguintes regras obrigatórias :

Limite de Empréstimos: Um Usuário não pode ter mais que 3 livros emprestados simultaneamente. O sistema bloqueia novos empréstimos se esse limite for atingido.


Status Automático do Livro:

Ao realizar um novo empréstimo (POST), o status disponivel do livro é alterado automaticamente para false.

Ao realizar a devolução (PUT), o status retorna para true.

🔒 Segurança
Autenticação via JWT (JSON Web Token) para proteção das rotas sensíveis.

🛠️ Como Executar o Projeto
Pré-requisitos
Java 21 instalado.

MySQL Server instalado e rodando.

Maven.

Passo a Passo
Clone o repositório:

Bash

git clone https://github.com/SEU-USUARIO/NOME-DO-REPOSITORIO.git
Configuração do Banco de Dados:

Crie um banco de dados no MySQL com o nome gestao_biblioteca (ou o nome definido no application.properties).

Verifique se o usuário e senha no arquivo src/main/resources/application.properties correspondem ao seu MySQL local:

Properties

spring.datasource.url=jdbc:mysql://localhost:3306/gestao_biblioteca
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
Executar a Aplicação:

Abra o projeto no IntelliJ IDEA ou VS Code.

Execute a classe principal BibliotecaApplication.java.

A API estará disponível em http://localhost:8080.

🧪 Testando a API
Você pode testar as rotas utilizando o Postman ou Insomnia. As coleções de teste cobrem o CRUD completo das 5 entidades e a validação das regras de negócio.

Autor: [Diego Silva]


Disciplina: Eletiva 01 - Arquitetura e Desenvolvimento Back-end