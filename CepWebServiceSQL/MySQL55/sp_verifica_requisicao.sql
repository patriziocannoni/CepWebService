DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_verifica_requisicao`(IN enderecoIp VARCHAR(45))
BEGIN

declare dataHoraAnterior DATETIME;
declare idUltimaRequisicao BIGINT;
declare dataHoraAtual DATETIME;

select NOW() into dataHoraAtual;

select max(id) into idUltimaRequisicao
from cep.requisicao
where endereco_ip = enderecoIp;

select data_hora into dataHoraAnterior
from cep.requisicao
where id = idUltimaRequisicao;

CASE
    WHEN
		-- Foi deita uma requisição pelo mesmo IP a menos de x segundos: descarta.
		-- TODO logar o erro e inserir o IP em uma lista negra caso ultrapasse X requisições bloqueadas. 
		TIMESTAMPDIFF(SECOND, (select max(data_hora) from requisicao), dataHoraAtual) < 2
	THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'HOUVE DUAS REQUISICOES MUITO PERTO UMA DA OUTRA';
    ELSE
		insert into cep.requisicao (endereco_ip, data_hora) values (
			enderecoIp, dataHoraAtual
		);
END CASE;

END$$
DELIMITER ;
