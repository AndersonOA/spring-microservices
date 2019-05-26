# Microservices com Spring Boot

> Exemplo de uma arquitetura de microserviço usando Spring Cloud

## Arquitetura

Nesse reprositório descreverei como construir uma arquitetura minimalista utilizando o **Spring Cloud**. 
Nossa arquitetura será composta por quatro serviços e será desenvolvida com base em containers **Docker** para facilitar o processo de **“deploy”**:

## Visão Global

A arquitetura é composta por quatro serviços:

- `discovery-service`: Serviço de Descoberta de outros Serviços com **Eureka**
- `api-gateway`: Gateway de API criado com **Zuul** que usa o `discovery-service` para enviar os pedidos para os serviços. Usa **Ribbon** como balanceador de carga.
- `article-service`: Serviço REST simples criado com **Spring Boot** para usar como exemplo
- `author-service`: Serviço REST simples criado com **Spring Boot** para usar como exemplo

Os serviços: `api-gateway`, `article-service` e `author-service` já estão configurados com **Hystrix (biblioteca de latência e tolerância a falhas)** e estão fornecendo um **stream** que você pode usar para monitorar com um **Hystrix/Turbine** painel de controle. Você pode verificar o **Hystrix Stream** acessando o URL de serviço com `/hystrix.stream` (exemplo: `http://localhost:8765/hystrix.stream`)

## Como usar

Para testar essa arquitetura, você precisará ter: **JDK 8+**, **Docker** e **Maven** instalados

- Clone este repositório e insira-o

- Execute o script `start.sh`, ele usará o **Maven** para construir o arquivo `.jar` para cada serviço e então usará o **Docker** para construir os containers com os arquivos `.jar`

Na configuração padrão, você terá:

- **Discovery Service** rodando na porta `8761`, acesso `http://localhost:8761` para ver o painel
- **API Gateway** rodando na porta `8765`, você enviará as solicitações para este serviço
- **Two Article Services** rodando em portas: `8080` e `9080`
- **Two Author Services** rodando em portas: `8081` e `9081`

Depois de executar os recipientes, siga para `http://localhost:8761` para se certificar de que os quatro serviços (dois **article** e dois **author**) estão registrados no **Discovery Service**, quando todos estão registrados, você pode testar o aplicativo com `curl` fazendo solicitações para os endpoints abaixo:

- `curl http://localhost:8765/api/v1/articles`
- `curl http://localhost:8765/api/v1/articles/id`
- `curl http://localhost:8765/api/v1/articles/author/id`
- `curl http://localhost:8765/api/v1/authors`
- `curl http://localhost:8765/api/v1/authors/id`

## Contribuindo

Relatórios de bugs e solicitações de recebimento são bem-vindos no GitHub em [https://github.com/AndersonOA/spring-microservices](https://github.com/AndersonOA/spring-microservices). Este projeto pretende ser um espaço seguro e acolhedor para colaboração.

## Licença

Este projeto está disponível como open source sob os termos do [MIT License](http://opensource.org/licenses/MIT).
