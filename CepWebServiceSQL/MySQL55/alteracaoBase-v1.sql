USE cep;

CREATE TABLE cep.requisicao (
	id 			BIGINT 		NOT NULL AUTO_INCREMENT,
  	endereco_ip VARCHAR(45) NULL,
  	data_hora 	DATETIME 	NOT NULL,
  	PRIMARY KEY (id)
);
 
create index IDX_ENDERECO_IP on cep.requisicao (endereco_ip);
