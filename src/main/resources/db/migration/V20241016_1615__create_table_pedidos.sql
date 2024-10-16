CREATE TABLE pedidos
(
	id SERIAL PRIMARY KEY,
	data_pedido DATE,
	data_entrega DATE,
	data_envio DATE,
	status BOOLEAN,
	valor_total DECIMAL,
	cliente_id BIGINT,
	constraint fk_cliente_id foreign key (cliente_id)
    references clientes(id)
)