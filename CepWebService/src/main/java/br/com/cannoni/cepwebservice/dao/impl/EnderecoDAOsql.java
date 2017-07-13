package br.com.cannoni.cepwebservice.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.cannoni.cepwebservice.dao.AbstractSqlDAO;
import br.com.cannoni.cepwebservice.dao.EnderecoDAO;
import br.com.cannoni.cepwebservice.dominio.Endereco;
import br.com.cannoni.cepwebservice.dominio.Uf;

/**
 * Classe que gerencia o acesso a base SQL de dados de CEPs.
 * 
 * @author Patrizio
 * @since 01/11/2013
 */
@Repository
public final class EnderecoDAOsql extends AbstractSqlDAO implements EnderecoDAO {

	@Override
	public Endereco obterEnderecoPorCep(final Long cep) {
		Endereco enderecoRetorno = new Endereco();

		List<Map<String, Object>> retorno = jdbcTemplate.queryForList("call cep.sp_sel_logradouro_por_cep (?)", cep);
		if (retorno.size() > 0) {
			Map<String, Object> mapa = (Map<String, Object>) retorno.get(0);

			enderecoRetorno.setBairro((String) mapa.get("bairro"));
			enderecoRetorno.setCep((Long) mapa.get("cep"));
			enderecoRetorno.setComplemento((String) mapa.get("complemento"));
			enderecoRetorno.setEnderecoCompleto((String) mapa.get("endereco"));
			enderecoRetorno.setLocalidade((String) mapa.get("localidade"));
			enderecoRetorno.setLogradouro((String) mapa.get("logradouro"));
			enderecoRetorno.setUf((String) mapa.get("uf.codigo"));
		}

		return enderecoRetorno;
	}

	@Override
	public List<Endereco> obterEnderecosPorNomeUf(final String nome, final String uf) {
		List<Map<String, Object>> retorno = jdbcTemplate.queryForList(
				"call cep.sp_sel_logradouro_por_nome_e_uf (?, ?)", nome, uf);

		List<Endereco> listaEnderecos = new ArrayList<Endereco>();
		for (Map<String, Object> mapa : retorno) {
			Endereco endereco = new Endereco();
			endereco.setBairro((String) mapa.get("bairro"));
			endereco.setCep((Long) mapa.get("cep"));
			endereco.setComplemento((String) mapa.get("complemento"));
			endereco.setEnderecoCompleto((String) mapa.get("endereco"));
			endereco.setLocalidade((String) mapa.get("localidade"));
			endereco.setLogradouro((String) mapa.get("logradouro"));
			endereco.setUf((String) mapa.get("uf"));
			listaEnderecos.add(endereco);
		}

		return listaEnderecos;
	}

	@Override
	public List<Uf> obterListaUfs() {
		List<Uf> lista = new ArrayList<Uf>();

		List<Map<String, Object>> listaObjetos = jdbcTemplate.queryForList("call cep.sp_sel_estados_brasil");
		for (Map<String, Object> mapa : listaObjetos) {
			Uf uf = new Uf();
			uf.setCodigo(mapa.get("codigo").toString());
			uf.setNome(mapa.get("nome").toString());
			lista.add(uf);
		}

		return lista;
	}

}
