# Compre-Venda

**Compre-Venda** é um projeto que iniciou com uma aplicação monolítica e foi refatorado para uma arquitetura baseada em microsserviços, utilizando **Java**, **Spring Boot**, **Spring Cloud**, **PostgreSQL** e **RabbitMQ**. O objetivo foi explorar os benefícios dessa transição, focando em escalabilidade, testabilidade e manutenção do código.

## Desafio

O projeto começou como uma aplicação monolítica, mas à medida que o sistema crescia, o acoplamento entre os serviços se tornou um problema. Foi então que a decisão foi tomada para dividir a aplicação em microsserviços. Utilizando **Spring Cloud**, foi possível facilitar a comunicação entre os microsserviços, gerenciar a configuração centralizada e implementar um sistema de mensageria com **RabbitMQ** para comunicação assíncrona.

## Tecnologias Utilizadas

- **Backend:** Java com Spring Boot
- **Arquitetura de Microsserviços:** Spring Cloud (para registro de serviços, descoberta, configuração centralizada e balanceamento de carga)
- **Banco de Dados:** PostgreSQL
- **Mensageria:** RabbitMQ
- **Testes:** JUnit (para testes unitários)

## Estrutura do Projeto

- **Versão Monolítica:** A versão inicial da aplicação, construída com uma arquitetura monolítica.
- **Versão Microsserviços:** Refatoração da aplicação para microsserviços, usando os módulos do **Spring Cloud** para facilitar a integração entre os serviços.

