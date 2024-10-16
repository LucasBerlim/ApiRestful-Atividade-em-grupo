CREATE TABLE enderecos
(
	id SERIAL PRIMARY KEY,
	cep VARCHAR(9),
	rua VARCHAR(255),
	bairro VARCHAR(255),
	cidade VARCHAR(255),
	numero VARCHAR(255),
	complemento VARCHAR(255),
	up VARCHAR(2)
)