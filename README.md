# SOAT

Bem-vindo(a) ao projeto **SOAT**! Este repositório faz parte da **Pós-Graduação em Arquitetura de Software da FIAP** e tem como objetivo demonstrar uma aplicação desenvolvida em **Java** com **Spring WebFlux** e persistência de dados em **MongoDB**, além de estar preparada para execução em **Docker**.

## Sobre o Projeto

- **Instituição**: FIAP
- **Programa**: Pós-Graduação em Arquitetura de Software
- **Stack Tecnológica**:
  - **Java** (versão 21)
  - **Spring WebFlux**
  - **MongoDB** (banco de dados)
  - **Docker**

Este projeto explora conceitos e boas práticas de desenvolvimento reativo, aproveitando o ecossistema do Spring WebFlux e a praticidade de um banco de dados não relacional como o MongoDB. Também inclui a possibilidade de containerização e orquestração de serviços por meio do Docker, tornando a aplicação escalável e fácil de gerenciar.

## Como Este Projeto se Destaca

1. **Arquitetura Reativa**: Utiliza Spring WebFlux, implementando programação reativa para garantir alto throughput, consumo eficiente de recursos e escalabilidade simplificada com operações não-bloqueantes.
2. **Arquitetura Hexagonal**: Segue o padrão de arquitetura hexagonal, promovendo uma separação clara entre as camadas de domínio, aplicação e infraestrutura, facilitando a manutenção e evolução do código.
3. **Uso de MongoDB**: Oferece uma estrutura flexível de armazenamento e facilita operações de grande volume de dados sem esquemas rígidos.
4. **Facilidade de Deploy**: Com Docker, é possível containerizar e executar a aplicação de forma simples, garantindo portabilidade e padronização do ambiente.
5. **Documentação com Swagger**: A aplicação inclui uma interface Swagger para facilitar a exploração das APIs, tornando o desenvolvimento e testes mais ágeis.

## Como executar o Projeto

### Pré-requisitos
- **Java 21**: Certifique-se de ter o JDK 21 instalado em sua máquina.
- **Docker**: Instale o Docker para executar a aplicação em um contêiner.

### Passos para Execução
1. **Clone o Repositório**:
   ```bash
   git clone https://github.com/Kurjata/Fiap_SOAT_Challenge.git
    ```
2. **Navegue até o Diretório do Projeto**:
    ```bash
    cd Fiap_SOAT_Challenge
    ```
3. **Execute o Projeto usando o comando docker**:
   ```bash
   docker compose up --build
   ```
4. **Utilize o Swagger via browser para navegar entre as API's disponíveis**:
   ```
   http://localhost:8080/webjars/swagger-ui/index.html
   ```

 #### Verifique se a porta 8080 está livre, caso contrário, a altere no arquivo `docker-compose.yml`.