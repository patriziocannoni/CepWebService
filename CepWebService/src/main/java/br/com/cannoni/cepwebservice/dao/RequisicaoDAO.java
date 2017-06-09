package br.com.cannoni.cepwebservice.dao;

/**
 * Interface que define o acesso a base de requisições ao Web Service.
 * 
 * @author Patrizio
 * @since 18/09/2014
 */
public interface RequisicaoDAO {

	/**
	 * Verifica a requisição frenta a base.
	 * 
	 * @param enderecoIp
	 *            String
	 */
	void verificarRequisicao(String enderecoIp);

}