package br.com.clima.consultaClima.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ClimaService.ClimaService;
import br.com.clima.Cidade;
import br.com.clima.ClimaCidade;
import br.com.clima.dao.CidadeDao;
import br.com.clima.rest.exception.ServicoRestException;

@Controller
public class ClimaController{

	//private ClimaService climaService;	
	
	@RequestMapping("novaCidade")
	public String form() {
		return "clima/formulario";
	}
	
	@RequestMapping("adicionaCidade")
	public String adiciona (@Valid Cidade cidade, Model model,BindingResult result) throws SQLException, ServletException, IOException{
		
		if(result.hasFieldErrors("codPais")) {
			return "clima/formulario";
		}
		
		if(result.hasFieldErrors("cidade")) {
			return "clima/formulario";
		}
		
		//Verificar se a cidade a ser incluida existe na Consulta de Clima

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "http://api.openweathermap.org/data/2.5/forecast?q="+cidade.getCidade()+","+cidade.getCodPais()+"&APPID=eb8b1a9405e659b2ffc78f0a520b1a46";
		HttpEntity<String>entity = new HttpEntity<String>(headers);
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
			CidadeDao dao = new CidadeDao();
			dao.adiciona(cidade);
		}
		catch (final HttpClientErrorException e) {
	        System.out.println(e.getStatusCode());
	        System.out.println(e.getResponseBodyAsString());
	        if (e.getStatusCode() == HttpStatus.NOT_FOUND){
			   model.addAttribute("msgErro", "Cod País / Cidade Inválida!!!");
	        }else {
	           model.addAttribute("msgErro",e.getResponseBodyAsString());
	        }
			return "clima/formulario";
	    }
		
		/*
		if (response.getStatusCode() == HttpStatus.OK) {
			//System.out.println(response);
			CidadeDao dao = new CidadeDao();
			dao.adiciona(cidade);
		}else {
			model.addAttribute("msgErro", "Cod País / Cidade Inválida!!!");
			return "clima/formulario";
		}
		*/
		return "clima/adicionada";
	}

	/*
	@GetMapping()
	public ResponseEntity<List<Cidade>> listar(){
		List<Cidade> cidades = cidadeService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(cidades);
		
	}
	*/
	
	@RequestMapping("listaCidades")
	public ModelAndView lista() throws Exception{
		
		CidadeDao dao = new CidadeDao();
		List<Cidade> cidades = dao.getLista();
		
		ModelAndView mv = new ModelAndView("clima/lista");
		mv.addObject("cidades",cidades);
		return mv;
	}
	
	@RequestMapping("removeTarefa")
	public String removeTarefa(ClimaCidade tarefa) throws SQLException{
		CidadeDao dao = new CidadeDao();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	//@ResponseBody
	@RequestMapping("mostraClima")
	public String mostra (String codPais, String cidade, Model model) throws ServletException, IOException{		
		
		//Verificar se a cidade a ser incluida existe na Consulta de Clima

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceURL = "http://api.openweathermap.org/data/2.5/forecast?q="+cidade+","+codPais+"&APPID=eb8b1a9405e659b2ffc78f0a520b1a46";
		HttpEntity<String>entity = new HttpEntity<String>(headers);
		
		//Teste json objeto
		//ClimaCidade[] climaCidade = restTemplate.getForObject(resourceURL, ClimaCidade[].class);
		//System.out.println(climaCidade);
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(resourceURL, HttpMethod.GET, entity, String.class);
			System.out.println(response);
			model.addAttribute("msgErro",response);
			
		}
		catch (final HttpClientErrorException e) {
			
	        System.out.println(e.getStatusCode());
	        System.out.println(e.getResponseBodyAsString());
	        if (e.getStatusCode() == HttpStatus.NOT_FOUND){
			   model.addAttribute("msgErro", "Cod País / Cidade Inválida!!!");
	        }else {
	           model.addAttribute("msgErro",e.getResponseBodyAsString());
	        }
	        model.addAttribute(e.getResponseBodyAsString());
			return "clima/lista";
	    }
		
		return "clima/lista";
		
	}
	
	@ResponseBody
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) throws SQLException {
		CidadeDao dao = new CidadeDao();
		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "clima/dataFinalizada";
				
	}
}
