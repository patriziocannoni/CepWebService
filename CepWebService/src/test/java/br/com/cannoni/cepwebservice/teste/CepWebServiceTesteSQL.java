package br.com.cannoni.cepwebservice.teste;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cannoni.cepwebservice.dao.EnderecoDAO;
import br.com.cannoni.cepwebservice.dominio.Endereco;
import br.com.cannoni.cepwebservice.dominio.Uf;

/**
 * Classe para br.com.cannoni.cepwebservice.teste unitário das procedures da base de CEP.
 * 
 * @author Patrizio
 * @since 16/11/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class CepWebServiceTesteSQL {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Test
	public void obterListaUfs() {
		List<Uf> listaRecebida = enderecoDAO.obterListaUfs();
		Assert.assertTrue(listaRecebida != null && !listaRecebida.isEmpty());
	}

	@Test
	public void obterEnderecoPorCep() {
		Object objetoRecebido = enderecoDAO.obterEnderecoPorCep(6352080L);
		Assert.assertTrue(objetoRecebido != null);
	}

	@Test
	public void obterEnderecosPorNomeUf() {
		List<Endereco> listaRecebida = enderecoDAO.obterEnderecosPorNomeUf("Aroeiras", "SP");
		Assert.assertTrue(listaRecebida != null && !listaRecebida.isEmpty());
	}

}
