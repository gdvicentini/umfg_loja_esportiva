CREATE TABLE cliente (
id SERIAL PRIMARY KEY,
nome VARCHAR(100),
telefone VARCHAR(20)
);

CREATE TABLE usuario (
id SERIAL PRIMARY KEY,
nome VARCHAR(100),
login VARCHAR(50),
senha VARCHAR(50),
ativo BOOLEAN
);

CREATE TABLE produto (
id SERIAL PRIMARY KEY,
nome VARCHAR(100),
descricao TEXT,
preco NUMERIC(10,2)
);

CREATE TABLE estoque (
id SERIAL PRIMARY KEY,
produto_id INTEGER REFERENCES produto(id),
quantidade INTEGER
);

CREATE TABLE venda (
id SERIAL PRIMARY KEY,
cliente_id INTEGER REFERENCES cliente(id),
usuario_id INTEGER REFERENCES usuario(id),
data TIMESTAMP,
valor_total NUMERIC(10,2)
);

CREATE TABLE item_venda (
id SERIAL PRIMARY KEY,
venda_id INTEGER REFERENCES venda(id),
produto_id INTEGER REFERENCES produto(id),
quantidade INTEGER,
preco_unitario NUMERIC(10,2),
subtotal NUMERIC(10,2)
);