package br.com.cannoni.cepwebservice.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Classe pai de todos os DAO que utilizam Spring para camada de persistência.
 * 
 * @author Patrizio
 * @since 19/02/2014
 */
public abstract class AbstractSqlDAO {

	@Autowired
	private DataSource jdbcDataSource;

	protected JdbcTemplate jdbcTemplate;

	/**
	 * Construtor.
	 */
	public AbstractSqlDAO() {
		super();
	}

	@PostConstruct
	private void init() {
		jdbcTemplate = new JdbcTemplate(jdbcDataSource);
	}

}
