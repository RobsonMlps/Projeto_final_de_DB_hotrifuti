CREATE TABLE CATEGORIA (
    ID_Categoria SERIAL PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Descricao TEXT
);

CREATE TABLE FORNECEDOR (
    ID_Fornecedor SERIAL PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    CNPJ VARCHAR(18) UNIQUE NOT NULL,
    Email VARCHAR(100)
);

CREATE TABLE CLIENTE (
    ID_Cliente SERIAL PRIMARY KEY,
    CPF VARCHAR(14) NOT NULL,
    Nome VARCHAR(100) NOT NULL
);

CREATE TABLE TELEFONE_CLIENTE (
    ID_Telefone SERIAL PRIMARY KEY, -- 'SERIAL' já é auto-incremento
    ID_Cliente INT,
    Numero_Telefone VARCHAR(20),
    FOREIGN KEY (ID_Cliente) REFERENCES CLIENTE(ID_Cliente)
);

CREATE TABLE PRODUTO (
    ID_Produto SERIAL PRIMARY KEY,
    ID_Categoria INT,
    Nome VARCHAR(100) NOT NULL,
    Descricao TEXT,
    Peso_KG DECIMAL(10,2), -- Adicionei (10,2) para limitar casas decimais
    FOREIGN KEY (ID_Categoria) REFERENCES CATEGORIA(ID_Categoria)
);

CREATE TABLE LOTE (
    ID_Lote SERIAL PRIMARY KEY,
    ID_Produto INT,
    ID_Fornecedor INT,
    Data_Entrada DATE,
    Data_Validade DATE,
    Custo DECIMAL(10,2), -- Dinheiro precisa de precisão
    FOREIGN KEY (ID_Produto) REFERENCES PRODUTO(ID_Produto),
    FOREIGN KEY (ID_Fornecedor) REFERENCES FORNECEDOR(ID_Fornecedor)
);

CREATE TABLE VENDA (
    ID_Venda SERIAL PRIMARY KEY,
    ID_Cliente INT,
    Data_Hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Mudado de DATETIME para TIMESTAMP
    FOREIGN KEY (ID_Cliente) REFERENCES CLIENTE(ID_Cliente)
);

CREATE TABLE ITEM_VENDA (
    -- Geralmente Item de Venda não tem ID próprio, usa-se chave composta (Venda + Produto)
    ID_Venda INT,
    ID_Produto INT,
    Quantidade_Vendida INT NOT NULL,
    Preco_Unidade DECIMAL(10,2) NOT NULL,
    
    -- Definindo Chave Primária Composta (Uma venda não pode ter o mesmo produto 2x, ela soma a quantidade)
    PRIMARY KEY (ID_Venda, ID_Produto),
    
    FOREIGN KEY (ID_Venda) REFERENCES VENDA(ID_Venda),
    FOREIGN KEY (ID_Produto) REFERENCES PRODUTO(ID_Produto)
);

SELECT * FR