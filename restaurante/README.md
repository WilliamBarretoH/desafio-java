# Seja muito bem-vindo avaliador(a)!!

## Objetivo:

Meu objetivo aqui é ser breve quanto a informações que julgo relevantes para você.

Abaixo segue alguns Curls que podem ser uteis:

Um adendo (adendao aliás rsrs), minha configuração da autenticação com Token Jwt e spring Secutiry não ficou
muito completa por motivos maiores que eu, um deles foi o tempo haha, outro que eu por azar configurei o projeto
com SpringSecutiry 6 e foi bem chato implementar as novas formas de fazer a roda kk.

O endpoint de cadastrar o usuario no swagger está bugado, 'objeto recursivo'

Você vai precisar do Token de um usuário cadastrado.

Criar usuário:

curl --request POST \
--url http://localhost:8080/auth/save \
--header 'Content-Type: application/json' \
--header 'customer: CUSTOMER:6EEDC0FE0717450784FA2A5C49B17AE4' \
--data '{

	"username" : "barreto",
	"password" : "admin",
	"roles": [ {
		"id" : 1,
		"description": "ADMIN"
	}]
}'

Login para capturar o acessToken

curl --request POST \
--url http://localhost:8080/auth/api/v1/login \
--header 'Content-Type: application/json' \
--header 'customer: CUSTOMER:6EEDC0FE0717450784FA2A5C49B17AE4' \
--data '{

	"userName" : "barreto",
	"password" : "admin"

}'

Request com Token:

curl --request GET \
--url 'http://localhost:8080/products?account=unused%20in%20V2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXJyZXRvIiwiaWF0IjoxNzA2ODMwODM2LCJleHAiOjE3MDY4MzQ0MzZ9.5LFFWK6genAMEYQ_4umHLutd_PrCmMxpa-7IXS6U5ZI' \
--header 'accept: application/vnd.pagseguro.api.v2+json'
