DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_logradouro_por_nome_e_uf`(IN paramNome varchar(70), IN paramUf varchar(2))
BEGIN
	select
		log_log.LOG_NOME as logradouro,
		concat(log_log.LOG_TIPO_LOGRADOURO, ' ', log_log.LOG_NO) as endereco,
		log_log.LOG_COMPLEMENTO as complemento,
		log_bai.BAI_NO as bairro,
		log_loc.LOC_NO as localidade,
		log_log.UFE_SG as uf,
		log_log.CEP as cep
	from log_logradouro log_log
	join log_bairro log_bai on log_log.BAI_NU_SEQUENCIAL_INI = log_bai.BAI_NU_SEQUENCIAL
	join log_localidade log_loc on log_log.LOC_NU_SEQUENCIAL = log_loc.LOC_NU_SEQUENCIAL
	where log_log.LOG_NOME like concat('%', paramNome, '%')
	and log_log.UFE_SG = paramUf
	order by log_log.LOG_NOME;
END$$
DELIMITER ;
