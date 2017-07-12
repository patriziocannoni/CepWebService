package br.com.cannoni.cepwebservice.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.com.cannoni.cepwebservice.dao.RequisicaoDAO;
import br.com.cannoni.springwebutils.servlet.SpringHttpFilter;

/**
 * @author Patrizio
 * @since 13/02/2014
 */
@WebFilter("/*")
public final class WebServiceIpFilter extends SpringHttpFilter {

	@Value("${filtroIpAtivo}")
	private String filtroIpAtivo;

	@Autowired
	private RequisicaoDAO requisicaoDAO;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		// Endereço IP da máquina que executou a requisição.
		String enderecoIp = req.getRemoteAddr();

		try {
			if (Boolean.parseBoolean(filtroIpAtivo)) {
				requisicaoDAO.verificarRequisicao(enderecoIp);
			}
			chain.doFilter(request, response);
		} catch (Exception ex) {
			Logger.getLogger(WebServiceIpFilter.class).info(ex.getMessage());
		}
	}

	@Override
	public void destroy() {
		requisicaoDAO = null;
	}

}
