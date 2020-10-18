# TimeTracking API

Trata-se de uma API para gerenciamento de horas trabalhadas.

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=TimeTracking%20API&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fangvamberg%2Ftimetracking-api%2Fmaster%2Fsrc%2Fmain%2Fresources%2FTimeTrackingAPI.json)
<img src="https://img.shields.io/static/v1?label=Spring&message=TimeTracking&color=36d648&style=for-the-badge&logo=Spring"/>

## Índice
<p align="center">
 <a href="##objetivo">Instalação</a> •
 <a href="##roadmap">API Endpoints</a> • 
 <a href="##tecnologias">Sobre</a> • 
 <a href="##contribuicao">Licença</a> 
</p>

## Instalação

Para rodar o projeto é necessário executar os seguintes comandos na pasta do projeto:

```bash
mvn clean
mvn install
```

É necessário também configurar as seguintes variáveis de ambiente para acesso ao banco em memória H2.

```bash
${DATASOURCE_USERNAME}
${DATASOURCE_PASSWORD}
```

O console do banco pode ser aberto no endereço http://localhost:8080/h2, e caso necessite da URL datasource:

```bash
jdbc:h2:mem:mydb;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL;INIT=CREATE SCHEMA IF NOT EXISTS TIMETRACKING
```
## API Endpoints

A documentação do projeto gerada pelo swagger poder ser encontrada no endereço http://localhost:8080/swagger-ui.html

Além disso, pode ser encontrado no início deste README um botão com chamadas preparadas para cada endpoint.


## Sobre
Esta API foi criada com intuito de atender o desafio backend proposto pela AIS Digital.

## Licença
[MIT](https://choosealicense.com/licenses/mit/)
