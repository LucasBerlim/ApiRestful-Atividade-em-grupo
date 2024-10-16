CREATE TABLE produtos
(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255),
	descricao VARCHAR(255),
	qtd_estoque INT,
	data_cadastro DATE,
	valor_unitario DECIMAL,
	imagem VARCHAR(255),
	categoria_id BIGINT,
	CONSTRAINT fk_categoria_id FOREIGN KEY (categoria_id)
	REFERENCES categorias(id)
)