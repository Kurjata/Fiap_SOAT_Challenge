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
  - **Kubernet**

Este projeto explora conceitos e boas práticas de desenvolvimento reativo, aproveitando o ecossistema do Spring WebFlux e a praticidade de um banco de dados não relacional como o MongoDB. Também inclui a possibilidade de containerização e orquestração de serviços por meio do Docker, tornando a aplicação escalável e fácil de gerenciar.

## Event Storming (Domain-Driven Design)

Diagrama para fluxo do desenvolvimento da aplicação. Link: https://miro.com/app/board/uXjVI6zdSIY=/.

## Vídeo explicativo do projeto

YouTube: https://www.youtube.com/watch?v=JePb8g_2d8A.
SERÁ SUBSTITUÍDO PELA PARTE 2

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
   
3. **Configurar as variáveis de ambiente:**
    ```bash
   # Cria uma cópia do arquivo
   cp .env.exemple .env
   
   # Abra o arquivo .env e inclua os valores correspondentes.
   ```

4. **Inicie o Minikube**:
   ```bash
   minikube start
   ```
5. **Construa a imagem Docker da aplicação**:
   ```bash
   docker build -t fiap_soat_challenge-app:latest -f .docker/prod/Dockerfile .
   ```

6. **Carregue a imagem da aplicação no ambiente do Minikube**:
   ```bash
   minikube image load fiap_soat_challenge-app:latest
   ```

7. **Carregue a imagem oficial do MongoDB no Minikube**:
   ```bash
   minikube image load mongo:8.0
   ```

8. **Aplique os manifests do Kubernetes para criar os recursos (pods, serviços, etc.)**:
   ```bash
   kubectl apply -f k8s/
   ```

9. **Verifique se os pods subiram corretamente**:
   ```bash
   kubectl get pods
   ```

10. **Acesse a aplicação via Minikube**:
    ```bash
     minikube service pedidos-api
    ```
</br>

 #### Acesse a documentação do Swagger - http://127.0.0.1:44585/webjars/swagger-ui/index.html
