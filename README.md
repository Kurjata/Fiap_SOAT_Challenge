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

## Diagramas de Arquitetura de Negócio e Infra

Miro: https://miro.com/app/board/uXjVJXrmXqc=/

## Como Este Projeto se Destaca

1. **Arquitetura Reativa**: Utiliza Spring WebFlux, implementando programação reativa para garantir alto throughput, consumo eficiente de recursos e escalabilidade simplificada com operações não-bloqueantes.
2. **Clean Architecture**: Estrutura o sistema em camadas independentes, separando regras de negócio de detalhes externos, o que facilita testes, manutenção e evolução do software.
3. **Uso de MongoDB**: Oferece uma estrutura flexível de armazenamento e facilita operações de grande volume de dados sem esquemas rígidos.
4. **Facilidade de Deploy**: Com Docker, é possível containerizar e executar a aplicação de forma simples, garantindo portabilidade e padronização do ambiente.
5. **Documentação com Swagger**: A aplicação inclui uma interface Swagger para facilitar a exploração das APIs, tornando o desenvolvimento e testes mais ágeis.
6. **Kubernetes**: Gerencia o ciclo de vida dos containers, automatizando o deploy, escalonamento, alta disponibilidade e exposição dos serviços da aplicação.

## Como executar o Projeto

### Pré-requisitos
- **Java 21**: Certifique-se de ter o JDK 21 instalado em sua máquina.
- **Docker Desktop**: Instale o Docker Desktop para Windows, Linux ou Mac e habilite o kubernetes.
- **Kubernetes**: Kubernetes deve estar habilitado na computador. Alternativamente se estiver utilizando um Mac, pode-se utilizar o `Orbstack`.

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

4. **Construa a imagem Docker da aplicação**:
   ```bash
   docker build -t fiap_soat_challenge-app:latest -f .docker/prod/Dockerfile .
   ```

5. **Altere o local de armazenamento do pv**: Dentro do arquivo k8s/pv.yaml altere a propriedade spec.hostPath.path, e adicione o caminho completo, desde a raíz do sistema até a pasta `mongo-data`.

6. **Aplique os manifests do Kubernetes para criar os recursos (metrics, secrets, configmap, etc.)**:
   ```bash
   # Metrics
   kubectl apply -f k8s/infra/metrics.yaml

   # Secrets
   kubectl apply -f k8s/db-secrets.yaml

   # ConfigMap
   kubectl apply -f k8s/app-configmap.yaml

   # Persistent Volum
   kubectl apply -f k8s/pv.yaml

   # Persistent Volum Claim
   kubectl apply -f k8s/pvc.yaml

   # Deployment
   kubectl apply -f k8s/db-deployment.yaml
   kubectl apply -f k8s/app-deployment.yaml

   # Horizontal Auto Scaler
   kubectl apply -f k8s/app-hpa.yaml
   ```

7. **Verifique se os pods subiram corretamente**:
   ```bash
   kubectl get pods
   ```

8.  **Expõe o serviço da aplicação**:
    ```bash
   # Service
   kubectl apply -f k8s/db-service.yaml
   kubectl apply -f k8s/app-service.yaml
    ```

 #### Acesse a documentação do Swagger - http://localhost:30080/webjars/swagger-ui/index.html
