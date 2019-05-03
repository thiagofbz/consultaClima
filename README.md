# consultaClima
Serviço Rest de consulta previsão do Tempo

Definições do Projeto:

Desenvolvimento: IDE Eclipse Photon.
Banco de Dados: MySQL
Estrutura: SpringMVC com API Restfull

O projeto pode ser importado via GitHub, compilado e executado na IDE do Eclipse Photon.

A aplicação foi desenvolvida utilizando o framwork SpringMVC com telas em jsp.
Para armazenamento das cidades cadastradas, foi utilizado o banco de dados MySQL.

A aplicação é composta de 3 telas:
- Inclui Cidades.
- Lista cidades.
* Mostra Previsão do Tempo.


DLL Tabela Cidade

create table dev.cidade (
codPais VARCHAR(2),
Cidade VARCHAR(50)
);


Obs: a tela "Mostra Previsão do Tempo" ainda está exibindo apenas o json recebido, pois identifiquei que o JSON gerado pelo Weather API está em conflito com o atributo do script JSON.parse, pois o mesmo espera "atributo":"valor" na resposta, porém a API devolve "atributo":valor sem "" (aspas).
