DELIMITER $$

DROP PROCEDURE IF EXISTS sp_sel_estados_brasil;

CREATE DEFINER=root@localhost PROCEDURE sp_sel_estados_brasil()
begin
	
	select
		UFE_SG as codigo,
		UFE_NO as nome
	from cep.log_faixa_uf log_faixa_uf
	order by UFE_SG;
	
end$$
DELIMITER ;
