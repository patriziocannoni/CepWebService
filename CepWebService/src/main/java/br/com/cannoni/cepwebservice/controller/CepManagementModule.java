package br.com.cannoni.cepwebservice.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cannoni.cepwebservice.dao.EnderecoDAO;
import br.com.cannoni.cepwebservice.dominio.Endereco;
import br.com.cannoni.cepwebservice.dominio.Uf;

/**
 * Módulo de gerenciamento das requisições de pesquisa na base de CEP. As respostas as consultas são em formato JSON.
 * 
 * @author Patrizio
 * @since 01/11/2013
 */
@Service
@Path("/consulta")
@Produces("application/json; charset=UTF-8")
public final class CepManagementModule {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@GET
	@Path("/enderecoPorCep/{cep}")
	public Response obterEnderecoPorCep(@PathParam("cep") Long cep) {
		Object endereco = enderecoDAO.obterEnderecoPorCep(cep);
		return Response.status(Response.Status.OK).entity(endereco).build();
	}

	@GET
	@Path("/enderecoPorNomeUf/{nome}/{uf}")
	public Response obterEnderecosPorNomeUf(@PathParam("nome") String nome, @PathParam("uf") String uf) {
		final Integer nomeTamanhoMinimo = 3;

		if (nome != null && nome.length() > nomeTamanhoMinimo) {
			List<Endereco> listaEnderecos = enderecoDAO.obterEnderecosPorNomeUf(nome, uf);
			return Response.status(Response.Status.OK).entity(listaEnderecos).build();
		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity("ERRO: nome pelo menos com " + (nomeTamanhoMinimo + 1) + " caracteres").build();
	}

	@GET
	@Path("/listaUfs")
	public Response obterListaUfs() {
		List<Uf> listaUfs = enderecoDAO.obterListaUfs();
		return Response.status(Response.Status.OK).entity(listaUfs).build();
	}

}
