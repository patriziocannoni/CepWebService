package br.com.cannoni.cepwebservice.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.cannoni.cepwebservice.dao.AbstractSqlDAO;
import br.com.cannoni.cepwebservice.dao.RequisicaoDAO;

/**
 * Classe que gerencia o acesso a tabela de requisições ao Web Service.
 * 
 * @author Patrizio
 * @since 14/02/2014
 */
@Repository
public final class RequisicaoDAOsql extends AbstractSqlDAO implements RequisicaoDAO {

	/**
	 * Construtor.
	 */
	private RequisicaoDAOsql() {
		super();
	}

	@Override
	public void verificarRequisicao(final String enderecoIp) {
		jdbcTemplate.update("call cep.sp_verifica_requisicao (?)", enderecoIp);
	}

}
