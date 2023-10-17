<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="br.com.ifpb.gerenciador.entity.Contato" %>
<%@ page import="br.com.ifpb.gerenciador.entity.BancoContato" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Contato</title>
    <%
    	if (request.getParameter("tipo").contains("atualizar")) {
	        String email = request.getParameter("email");
	        Contato contato = null;
	        for (Contato c : BancoContato.getContatos()) {
	            if (c.getEmail().equals(email)) {
	                contato = c;
	                break;
	            }
	        }
	        pageContext.setAttribute("contato", contato);
    	}
    %>
</head>
<body>
    <h1>Formulário de Contato</h1>
    
    <form action="contatos"
          method="post">
        Email *: <input type="email" name="email" id="email" 
       value="${param.tipo eq 'atualizar' ? contato.email : ''}"
       ${param.tipo eq 'atualizar' ? 'readonly' : ''}>
         Nome: <input type="text" name="nome" id="nome" disable value="${param.tipo eq 'atualizar' ? contato.nome : ''}"><br>
        Telefone: <input type="number" name="telefone" id="telefone" value="${param.tipo eq 'atualizar' ? contato.telefone : ''}"><br>
        <input type="hidden" name="operacao" id="operacao" readonly value="${param.tipo}">
        <c:choose>
            <c:when test="${param.tipo eq 'novo'}">
                <input type="submit" value="Adicionar">
            </c:when>
            <c:when test="${param.tipo eq 'atualizar'}">
               <input type="submit" value="Atualizar">
            </c:when>
        </c:choose>
    </form>
    
    <br>
    <a href="index.jsp">Ver Contatos</a>
</body>
</html>