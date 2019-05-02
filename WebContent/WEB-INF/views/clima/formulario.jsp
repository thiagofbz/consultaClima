<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<body>
		<h3>Adicionar Cidades</h3>
		
		<a href="listaCidades">Lista Cidades</a>
		
		<p>
		
		<form action="adicionaCidade" method="post">
		    Código do País:
		    <br />
		    <input type="text" name="codPais" maxlength="2" size="2" class="form-control"/> <br />
		    <br />
		    <form:errors path="cidade.codPais" cssClass="error" cssStyle="color:red"/>
		    <br />
			Cidade: 
			<br />
			<input type="text" name="cidade" maxlength="50" size="50" class="form-control"/> <br />
			<br />
			<form:errors path="cidade.cidade" cssClass="error" cssStyle="color:red"/>
			<br />
			<input type="submit" value="Adicionar">
		</form>
		
		<output name="msgErro">"${msgErro}"</output>
		
	</body>
</html>