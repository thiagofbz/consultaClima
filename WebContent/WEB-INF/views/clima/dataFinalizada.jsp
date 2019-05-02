<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

	<body>
		<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />

		<script type="text/javascript">
			function finalizaAgora(id){
				$.post("finalizaTarefa", {'id':id}, function(resposta){
					$("#tarefa_"+id).html("Finalizado");
					alert(resposta);
				});
			}
		
		</script>

	</body>

</html>
