package br.com.clima;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Cidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484366364112885688L;

	//public static Cidade valueOf(String json) {
	//	return new Gson().fromJson(json, Cidade.class);
	//}
	
	@Size(min=2)
	private String codPais;
	
	@Size(min=3)
	private String cidade;

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Cidade [codPais=" + codPais + ", cidade=" + cidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (codPais == null) {
			if (other.codPais != null)
				return false;
		} else if (!codPais.equals(other.codPais))
			return false;
		return true;
	}
	
	
}
