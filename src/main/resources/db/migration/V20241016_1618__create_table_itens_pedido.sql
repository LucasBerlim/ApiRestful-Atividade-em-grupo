CREATE TABLE itens_pedido
(
	id SERIAL PRIMARY KEY,
	quantidade INT,
	preco_venda DECIMAL,
	percentual_deconto DECIMAL,
	valor_bruto DECIMAL,
	valor_liquido DECIMAL,
	pedido_id BIGINT,
	produto_id BIGINT,
	constraint fk_pedido_id foreign key (pedido_id)
    references pedidos(id) ON DELETE CASCADE,
	constraint fk_produto_id foreign key (produto_id)
    references produtos(id) ON DELETE CASCADE
)