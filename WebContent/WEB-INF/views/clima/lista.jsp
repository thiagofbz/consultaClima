<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tdata" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<script type="text/javascript" src="resources/js/jquery.js"></script>
		<title>Lista Cidades</title>
	</head>
	<body>

		<a href="novaCidade">Inclui Nova Cidade</a>
		
		<br /> <br />
		
		<table>
			<tr>
				<th>Cód País</th>
				<th>Cidade</th>
			</tr>
			
			<c:forEach items="${cidades}" var="cidade">
				<tr>
					<td>${cidade.codPais}</td>
					
					<td>${cidade.cidade}</td>
					
					<td>
						<a href="mostraClima?codPais=${cidade.codPais}&cidade=${cidade.cidade}" >Previsão</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<p>
		
		<p>The value of "temp" is:</p>
		<p id="demo"></p>
		
		<p>Valor deve estar acima deste comentario:</p>
		<p>
		<p>
		
		
		<output id="msgErro" name="msgErro">"${msgErro}"</output>


		<script>
		
		//var msgErro = document.getElementById('msgErro').value;
		//var msgErro = '"${msgErro}"'
		var msgErro = '{"temp":"294","temp_min":"292","temp_max":"294"}';
		
		msg('{"temp":"John", "lastName":"Doe"}');
		msg(msgErro);
		
		function msg(msgErro){
		  //var obj = JSON.parse('{"temp":"John", "lastName":"Doe"}');
		  
		  alert('Entrou na function ' + msgErro);
		  
		  var obj = JSON.parse(msgErro);
		  //var obj = JSON.parse(response);
		  
		  alert(msgErro);
		  
		  document.getElementById("demo").innerHTML = obj.temp;
		}
		
		//msg(msgErro);
		
		</script>		
		




	</body>
</html>