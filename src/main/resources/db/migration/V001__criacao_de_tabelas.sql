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
  status varchar(1) NOT NULL,
  data_nascimento DATE,
  data_cadastro DATE,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_pessoa_ponto FOREIGN KEY FK_pessoa_ponto (id_ponto)
    REFERENCES ponto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Definition of table frequencia
--
CREATE TABLE frequencia (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_pessoa BIGINT(20) NOT NULL,
  presente boolean NOT NULL default false,
  data_distribuicao DATE,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_frequencia_pessoa FOREIGN KEY FK_frequencia_pessoa (id_pessoa)
    REFERENCES pessoa (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;