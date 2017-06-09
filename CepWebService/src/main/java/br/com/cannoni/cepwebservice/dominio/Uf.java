package br.com.cannoni.cepwebservice.dominio;

/**
 * @author Patrizio
 * @since 21/02/2014
 */
public class Uf {

	private String codigo;

	private String nome;

	/**
	 * Construtor.
	 */
	public Uf() {
		super();
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
