#Consulta Clima

Definição Projeto

Desenvolvimento: IDE Eclipse Photon
Servidor: Apache Tomcat 8,5
Banco de Dados: MySQL

Import:
Importar a aplicação via github através do link https://github.com/thiagofbz/consultaClima

Visão Geral: 

A aplicação foi desenvolvida utilizando frameword Spring MVC com API Restfull e telas em jsp.

Foram desenvolvidas 3 telas:
- Inclui Cidades
- Lista Cidades
* Mostra Previsão do Tempo.

Obs: A tela "Mostra Previsão do Tempo" ainda não está exibindo formatados os dados recebidos via JSON, pois identifiquei que o método "JSON.parse" prevê um JSON formatado como {"nome":"valor"}, porém o JSON recebido da Weather API possui um JOSN definido como {"nome":valor} sem " " (aspas) no atributo valor. 
Sendo assim, seria necessário um tratamento via regex para formatação do JSON antes do envio do Java para o JSP.


DLL Tabela Cidade

create table dev.cidade (
codPais VARCHAR(2),
Cidade VARCHAR(50)
);