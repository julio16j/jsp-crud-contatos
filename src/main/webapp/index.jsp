<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.com.ifpb.gerenciador.entity.Contato" %>
<%@ page import="br.com.ifpb.gerenciador.entity.BancoContato" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Contatos</title>
</head>
<body>
    <h1>Lista de Contatos</h1>
    <%-- Verifica se há mensagem de erro na requisição --%>
    <c:if test="${not empty requestScope.erro}">
        <p style="color: red;">${requestScope.erro}</p>
    </c:if>
    <table border="1">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Telefone</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contato" items="${BancoContato.getContatos()}">
                <tr>
                    <td>${contato.nome}</td>
                    <td>${contato.email}</td>
                    <td>${contato.telefone}</td>
                    <td>
                        <a href="formContato.jsp?tipo=atualizar&email=${contato.email}">Editar</a>
                        <a href="javascript:void(0);" onclick="onDelete('${contato.email}')">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="formContato.jsp?tipo=novo"><button>Adicionar Contato</button></a>
    <script>
    	function onDelete (email) {
    		if (confirm("Deseja deletar ?")) {
    			window.location.href = 'contatos?operacao=deletar&email=' + email;
    		}
    	}
    </script>
</body>
</html>
