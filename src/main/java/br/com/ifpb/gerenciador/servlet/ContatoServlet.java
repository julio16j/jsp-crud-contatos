package br.com.ifpb.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ifpb.gerenciador.entity.BancoContato;
import br.com.ifpb.gerenciador.entity.Contato;

@WebServlet("/contatos")
public class ContatoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String operacao = request.getParameter("operacao");
    	if (operacao.equals("deletar")) {
    		deletarContato(request);
    	} RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	try {
            String operacao = request.getParameter("operacao");

            if (operacao.equals("novo")) {
                criarContato(request);
            } else if (operacao.equals("atualizar")) {
                atualizarContato(request);
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            // Captura a exceção e adiciona uma mensagem de erro à requisição
            request.setAttribute("erro", e.getMessage());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        }
        
    }
    
    private void criarContato(HttpServletRequest request) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        // Verifica se o email é nulo ou vazio
        if (email == null || email.isEmpty()) {
            throw new ServletException("O email não pode ser nulo ou vazio");
        }

        // Verifica se o email já existe na lista de contatos
        for (Contato contato : BancoContato.getContatos()) {
            if (contato.getEmail().equals(email)) {
                throw new ServletException("Já existe um contato com o mesmo email");
            }
        }

        Contato contato = new Contato(nome, email, telefone);
        BancoContato.adiciona(contato);
    }
    private void deletarContato(HttpServletRequest request) {
    	String email = request.getParameter("email");
    	BancoContato.removerContato(email);
		
	}

	private void atualizarContato(HttpServletRequest request) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        Contato novoContato = new Contato (nome, email, telefone);
        BancoContato.atualizaContato(novoContato);
    }
    
}