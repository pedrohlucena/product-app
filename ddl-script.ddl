DROP TABLE T_PRODUTO CASCADE CONSTRAINTS;
DROP TABLE T_CATEGORIA CASCADE CONSTRAINTS;
DROP TABLE T_USUARIO CASCADE CONSTRAINTS;

DROP SEQUENCE SQ_T_PRODUTO;
DROP SEQUENCE SQ_T_CATEGORIA;

-- T_PRODUTO

CREATE TABLE T_PRODUTO (
    cd_produto INT NOT NULL, 
    nm_produto VARCHAR(100) NOT NULL, 
    vl_produto NUMBER NULL,
    cd_categoria INT NOT NULL,
    qt_produto INT NULL,
    dt_fabricacao DATE NULL
);

ALTER TABLE T_PRODUTO 
    ADD CONSTRAINT PK_T_PRODUTO PRIMARY KEY (cd_produto);

-- T_CATEGORIA

CREATE TABLE T_CATEGORIA (
    cd_categoria INT NOT NULL, 
    nm_categoria VARCHAR(50) NOT NULL
);

ALTER TABLE T_CATEGORIA 
    ADD CONSTRAINT PK_T_CATEGORIA PRIMARY KEY (cd_categoria);
    
-- T_USUARIO

CREATE TABLE T_USUARIO (
    ds_email VARCHAR(150) NOT NULL, 
    ds_senha VARCHAR(50) NOT NULL
);

ALTER TABLE T_USUARIO 
    ADD CONSTRAINT PK_T_USUARIO PRIMARY KEY (ds_email);    
    
-- Sequences    
    
CREATE SEQUENCE SQ_T_PRODUTO 
    MINVALUE 1 
    START WITH 1 
    INCREMENT BY 1;    

CREATE SEQUENCE SQ_T_CATEGORIA 
MINVALUE 1 
START WITH 1 
INCREMENT BY 1;

-- Foreign keys

ALTER TABLE T_PRODUTO 
    ADD CONSTRAINT cd_categoria FOREIGN KEY (cd_categoria) 
    REFERENCES T_CATEGORIA(cd_categoria);