# TimeTracking API

Trata-se de uma API para gerenciamento de horas trabalhadas.

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=TimeTracking%20API&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fangvamberg%2Ftimetracking-api%2Fmaster%2Fsrc%2Fmain%2Fresources%2FTimeTrackingAPI.json)
<img src="https://img.shields.io/static/v1?label=Spring&message=TimeTracking-API&color=36d648&style=for-the-badge&logo=Spring"/>

## Índice
<p align="center">
 <a href="#instalacao">Instalacao</a> •
 <a href="#api-endpoints">API Endpoints</a> • 
 <a href="#sobre">Sobre</a> • 
 <a href="#consideracoes">Consideracoes</a> • 
 <a href="#licenca">Licenca</a> 
</p>

## Instalacao

Para rodar o projeto é necessário executar os seguintes comandos na pasta do projeto:

```bash
./mvnw clean
./mvnw install
./mvnw spring-boot:run
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

Para abertura do projeto em IDE's poderá ser necessário realizar configurações específicas de cada ferramenta, já que o projeto utiliza-se do LOMBOK.

## API Endpoints

A documentação do projeto gerada pelo Swagger poder ser encontrada no endereço http://localhost:8080/swagger-ui.html

Além disso, pode ser encontrado no início deste README um botão com chamadas preparadas para cada endpoint da API, gerado pelo Insomnia.

Vale ressaltar que é necessário considerar os seguintes valores (quando requisitados) para a utilização da API:
```bash
tipoPeriodo: 1 para MATUTINO e 2 para VESPERTINO
tipoRegistro: 1 para ENTRADA e 2 para SAIDA
datas no geral: formato dd/MM/yyyy
horarios/tempoAlocado: formato HH:mm

requisições que usam mesAno pela url: MM-yyyy
```

## Sobre
Esta API foi criada com intuito de atender o desafio backend proposto pela AIS Digital e utiliza-se de alguns recursos, como:
<ul>
 <li>MapStruct</li>
 <li>Lombok</li>
 <li>Swagger</li>
 <li>GitMoji</li>
 <li>SpringBoot, SpringData, SpringMVC</li>
</ul>


## Consideracoes
O desafio, apesar de uma proposta simples, não é de fato trivial. A criação do projeto por completo envolveu várias habilidades como: tratamento e manipulação de datas, validação de regras de negócio, mapeamento de entidades, documentação, versionamento, transações, testes, e principalmente, o design de uma API a partir de um problema.

O curto período de tempo para desenvolvimento também faz com que priorizemos certas atividades e acabemos deixando outras para posterior melhoria. A propósito, uma melhoria feita foi a de obtenção de todos os registros de um usuário dado um determinado mês-ano.

Experimentei utilizar alguns recursos que ainda não havia utilizado como MapStruct e Gitmoji, e cairam como uma luva!

Por fim, foram dois dias intensos (😅) e muito válidos.


## Licenca
[MIT](https://choosealicense.com/licenses/mit/)
