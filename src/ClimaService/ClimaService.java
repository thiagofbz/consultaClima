package ClimaService;

import java.io.Serializable;
import java.util.List;

import br.com.clima.Cidade;
import br.com.clima.ClimaCidade;
import br.com.clima.rest.client.ClimaClient;
import br.com.clima.rest.exception.ServicoRestException;

public class ClimaService implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -4558246974541059897L;
	
	//	@Inject
    //	private ComunicacaoCicsECIClient bandeiraCicsClient;
	private ClimaClient climaClient;
	
	//chamada JSON mock
	public List<ClimaCidade> consultarClimaCidadeGson(Cidade cidade) throws ServicoRestException {
		climaClient = new ClimaClient();
		List<ClimaCidade> climaCidades = null;
		try {
			climaCidades = climaClient.consultarCidade(cidade);
		} catch (ServicoRestException e) {
			throw e; 
		}
		return climaCidades;
	}

}
