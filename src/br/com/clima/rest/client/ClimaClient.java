package br.com.clima.rest.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.clima.Cidade;
import br.com.clima.ClimaCidade;
import br.com.clima.rest.exception.ServicoRestException;


public class ClimaClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8722664478815407390L;
	
	private static final String CIDADE = "cidade";
	private static final String PAIS = "codPais";
	    
	private static final String URL= "http://api.openweathermap.org/data/2.5/forecast?q={cidade},{codPais}&APPID=eb8b1a9405e659b2ffc78f0a520b1a46";

	    
	    public List<ClimaCidade> consultarCidade(Cidade cidade) throws ServicoRestException {
	        GenericType<List<ClimaCidade>> listaClimaCidade = null;
	        Response response = null;
	        try {
	        	listaClimaCidade = new GenericType<List<ClimaCidade>>() {};
	            
	            		response = ClientBuilder.newClient().target(URL)
	            				.resolveTemplate(CIDADE, cidade.getCidade())
	            				.resolveTemplate(PAIS, cidade.getCodPais())
	                            .request(MediaType.APPLICATION_JSON).get();
	            
	            if (response != null) {
	                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	                    return response.readEntity(listaClimaCidade);
	                }
	                if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
	                    return Collections.emptyList();
	                }
	            }
	            throw new ServicoRestException("rest_erroServidor");
	            
	        } catch (ServicoRestException e) {
	            throw e;
	        } finally {
	            if (response != null) {
	                response.close();
	            }
	        }
	    }

}
