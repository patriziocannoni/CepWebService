DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sel_logradouro_por_cep`(IN paramCep int)
begin
	
	declare cep_log_logradouro int;
	declare cep_log_cpc int;
	
	select count(*) into cep_log_logradouro
	from log_logradouro log_log
	where log_log.CEP = paramCep;
	
	if cep_log_logradouro > 0 then
		select
			log_log.LOG_NOME as logradouro,
			concat(log_log.LOG_TIPO_LOGRADOURO, ' ', log_log.LOG_NO) as endereco,
			log_log.LOG_COMPLEMENTO as complemento,
			log_bai.BAI_NO as bairro,
			log_loc.LOC_NO as localidade,
			log_log.UFE_SG as 'uf.codigo',
			log_log.CEP as cep
		from log_logradouro log_log
		join log_bairro log_bai on log_log.BAI_NU_SEQUENCIAL_INI = log_bai.BAI_NU_SEQUENCIAL
		join log_localidade log_loc on log_log.LOC_NU_SEQUENCIAL = log_loc.LOC_NU_SEQUENCIAL
		where log_log.CEP = paramCep;
	else
		select count(*) into cep_log_cpc
		from log_cpc log_cpc
		where log_cpc.CEP = paramCep;
		
		if cep_log_cpc > 0 then
			select
				log_cpc.CPC_NO as logradouro,
				log_cpc.CPC_ENDERECO as endereco,
				null as complemento,
				null as bairro,
				log_loc.LOC_NO as localidade,
				log_cpc.UFE_SG as 'uf.codigo',
				log_cpc.CEP as cep
			from log_cpc log_cpc
			join log_localidade log_loc on log_cpc.LOC_NU_SEQUENCIAL = log_loc.LOC_NU_SEQUENCIAL
			where log_cpc.cep = paramCep;
		else
			select
				null as logradouro,
				null as endereco,
				null as complemento,
				null as bairro,
				log_loc.LOC_NO as localidade,
				log_loc.UFE_SG as 'uf.codigo',
				log_loc.CEP as cep
			from log_localidade log_loc
			where log_loc.cep = paramCep;
		end if;
	end if;
	
end$$
DELIMITER ;
