<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tdata" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	    
	</head>
	<body>
		<h3>Previsão do Tempo - "${cidade}"</h3>

		<output id="msgErro" name="msgErro">"${msgErro}"</output>


		<p>
		<p>
		<p id="demo"></p>

		<script>
		
			var msgErro = document.getElementById('msgErro').value;
			//var msgErro = '{"temp":"294","temp_min":"292","temp_max":"294"}';
			
			//alert(msgErro);
			
			msg(msgErro);
			
			function msg(msgErro){
			  //var obj = JSON.parse('{"temp":"John", "lastName":"Doe"}');
			  
			  //Erro no JSON.parse (JSON diferente do esperado)
			  //var obj = JSON.parse(msgErro);
			  
			  //document.getElementById("demo").innerHTML = obj.temp;
			}
		
		</script>
		<p>
		<p>



		
	</body>
</html>