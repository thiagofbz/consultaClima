<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tdata" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>

	    <link href="css/jquery-ui.css" rel="stylesheet">
	    <script src="js/jquery.js"></script>
	    <script src="js/jquery-ui.js"></script>	
	    
	</head>
	<body>
		<h3>Alterar tarefa - ${tarefa.id}</h3>
		<form action="alteraTarefa" method="post">
			
			<input type="hidden" name="id" value="${tarefa.id}"/>
			
			Descrição:<br/>
			<textarea name="descrição" cols="100" rows="5"><%-- 
			--%>${tarefa.descrição}<%--
			--%></textarea>
			<br/>
			
			Finalizado? <input type="checkbox" name="finalizado"
				value="true" ${tarefa.finalizado?'checked':'' }/>
			<br/>
			
			Data de finalização: <br/>
			
			<!-- <input type="both" name="dataFinalizacao" 
				value="<fmt:formatDate
					value="${tarefa.dataFinalizacao.time}"
					dateStyle="full" 
					pattern="dd/MM/yyyy"/>"/> -->
					
			<tdata:campoData id="dataFinalizacao"></tdata:campoData>		
					
			<input type="submit" value="Alterar"/>
			
		</form>
	</body>
</html>