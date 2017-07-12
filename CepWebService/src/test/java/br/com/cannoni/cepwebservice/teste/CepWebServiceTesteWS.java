package br.com.cannoni.cepwebservice.teste;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Classe para br.com.cannoni.cepwebservice.teste unitário do Web Service de CEP.
 * 
 * @author patrizio.cannoni
 * @since 13/11/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:applicationContext-test.xml" })
@Ignore
public class CepWebServiceTesteWS {

	@Value("${cepWebServiceUrl}")
	private String urlCepWebService;

	@Test
	public void wsTestarObterListaUfs() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlCepWebService + "/listaUfs");

		try {
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String jsonString = new String(IOUtils.toByteArray(is), "UTF-8");
			is.close();

			Assert.assertTrue(jsonString != null && jsonString.length() > 0);
			Logger.getLogger(getClass()).info(jsonString);

		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void wsTestarObterEnderecoPorCep() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlCepWebService + "/enderecoPorCep/06352080");

		try {
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String jsonString = new String(IOUtils.toByteArray(is), "UTF-8");
			is.close();

			Assert.assertTrue(jsonString != null && jsonString.length() > 0);
			Logger.getLogger(getClass()).info(jsonString);

		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void wsTestarObterEnderecosPorNomeUf() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlCepWebService + "/enderecoPorNomeUf/Alameda%20das%20Aroeiras/SP");

		try {
			HttpResponse response = httpclient.execute(httpget);
			InputStream is = response.getEntity().getContent();
			String jsonString = new String(IOUtils.toByteArray(is), "UTF-8");
			is.close();

			Assert.assertTrue(jsonString != null && jsonString.length() > 0);
			Logger.getLogger(getClass()).info(jsonString);

		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
