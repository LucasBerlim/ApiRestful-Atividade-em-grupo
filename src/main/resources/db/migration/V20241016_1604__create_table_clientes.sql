CREATE TABLE clientes
(
	id SERIAL PRIMARY KEY,
	email VARCHAR(255),
	nome_completo VARCHAR(255),
	cpf VARCHAR(14),
	telefone VARCHAR(20),
	data_nascimento DATE,
	endereco_id BIGINT,
	CONSTRAINT fk_endereco_id FOREIGN KEY (endereco_id)
	REFERENCES enderecos(id)
)