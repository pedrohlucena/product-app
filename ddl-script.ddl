--DROP TABLE T_PRODUTO;

CREATE TABLE T_PRODUTO (
    cd_produto INT NOT NULL, 
    nm_produto VARCHAR(100) NOT NULL, 
    vl_produto NUMBER NULL,
    qt_produto INT NULL,
    dt_fabricacao DATE NULL,
    dt_validade DATE NULL
);

ALTER TABLE T_PRODUTO 
    ADD CONSTRAINT PK_T_PRODUTO PRIMARY KEY (cd_produto);

CREATE SEQUENCE SQ_T_PRODUTO 
    MINVALUE 1 
    START WITH 1 
    INCREMENT BY 1;