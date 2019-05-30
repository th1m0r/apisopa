--
-- Definition of table ponto
--
CREATE TABLE ponto (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table ponto
--
/*!40000 ALTER TABLE ponto DISABLE KEYS */;
INSERT INTO ponto (nome) VALUES 
("PASSO DA PATRIA"),
("ALECRIM"),
("CIDADE ALTA");
/*!40000 ALTER TABLE ponto ENABLE KEYS */;

--
-- Definition of table pessoa
--
CREATE TABLE pessoa (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_ponto BIGINT(20) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  data_nascimento DATE,
  data_cadastro DATE,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_pessoa_ponto FOREIGN KEY FK_pessoa_ponto (id_ponto)
    REFERENCES ponto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;