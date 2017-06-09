package br.com.cannoni.cepwebservice.dao;

import java.util.List;

import br.com.cannoni.cepwebservice.dominio.Endereco;
import br.com.cannoni.cepwebservice.dominio.Uf;

/**
 * Interface que define o acesso a base de dados de CEPs.
 * 
 * @author Patrizio
 * @since 18/09/2014
 */
public interface EnderecoDAO {

	/**
	 * Obtém um objeto pelo cep.
	 * 
	 * @param cep
	 *            {@link Long}
	 * @return {@link Object}
	 */
	Object obterEnderecoPorCep(Long cep);

	/**
	 * Obtém uma lista de objetos pelo nome e uf.
	 * 
	 * @param nome
	 *            {@link String}
	 * @param uf
	 *            {@link String}
	 * @return {@link List}
	 */
	List<Endereco> obterEnderecosPorNomeUf(String nome, String uf);

	/**
	 * Obtém a lista de objetos que representam os UFs do Brasil.
	 * 
	 * @return {@link List}
	 */
	List<Uf> obterListaUfs();

}