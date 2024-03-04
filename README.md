# TEXTO Test API

Implementação de uma API solicitada para avaliação na segunda etapa do processo de admissão da empresa TEXTO

## Especificação do Teste
Desenvolver uma API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Requisito do sistema:
1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a aplicação.

## Requisitos da API:
1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido, seguindo a especificação de formato definida na
página 2;
Requisitos não funcionais do sistema:
1. O web service RESTful deve ser implementado com base no nível 2 de maturidade
de Richardson;
2. Devem ser implementados somente testes de integração. Eles devem garantir que
os dados obtidos estão de acordo com os dados fornecidos na proposta;
3. O banco de dados deve estar em memória utilizando um SGBD embarcado (por
exemplo, H2). Nenhuma instalação externa deve ser necessária;
4. A aplicação deve conter um readme com instruções para rodar o projeto e os
testes de integração.
O código-fonte deve ser disponibilizado em um repositório git (Github, Gitlab, Bitbucket,
etc).

## Instalação

1. Obter o código fonte do repositório:
	`https://github.com/bernardocioglia/texto-test-api.git`
2. Importar o projeto em uma IDE. (e.g. Eclipse. STS, InteliJ IDEA, VS Code)
   - obs: _O projeto foi implementado usando Spring Tool Suite 4_
   - obs2: _Este projeto foi implementado usando JDK 21_

3. Importar o arquivo texto-test-api.postman_collection.json no Postman
   - obs: arquivo se encontra na pasta raiz do projeto

## Como testar

1. Iniciar o serviço "texto-test-api".
2. Executar os testes de integração usando o postman.

## End poits
### Foram criados 3 pontos de teste de integração:
1. Visualizar todos os registros inseridos no banco: **AllMovies**
2. Visualizar todos os filmes premiados: **AllWinners**
3. Visualizar os produtores com maiores e menores periodos entre dois prêmios consecutivos: **ProducersMinMax**

