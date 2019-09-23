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
("CATEDRAL"),
("PRAÇA DA MÃO"),
("ALECRIM");
/*!40000 ALTER TABLE ponto ENABLE KEYS */;

--
-- Definition of table assistido
--
CREATE TABLE assistido (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_ponto BIGINT(20) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  situacao varchar(1) NOT NULL,
  data_nascimento DATE,
  data_cadastro DATE,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_assistido_ponto FOREIGN KEY FK_assistido_ponto (id_ponto)
    REFERENCES ponto (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Definition of table frequencia
--
CREATE TABLE frequencia (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  id_assistido BIGINT(20) NOT NULL,
  presente TINYINT(4) NOT NULL default false,
  data_distribuicao DATE,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_frequencia_assistido FOREIGN KEY FK_frequencia_assistido (id_assistido)
    REFERENCES assistido (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;