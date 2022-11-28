package br.com.fiap.store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.store.bean.User;
import br.com.fiap.store.bo.EmailBO;
import br.com.fiap.store.dao.UserDAO;
import br.com.fiap.store.exception.EmailException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDao;
	private EmailBO emailBo;
	
	@Override
	public void init() throws ServletException {
		userDao = DAOFactory.getUserDAO();
		emailBo = new EmailBO();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		
		User user = new User(email, password);
			
		if(userDao.validateUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			try {
				emailBo.sendEmail(email, "Login Realizado", "Um login foi realizado");
			} catch (EmailException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		} else {
			request.setAttribute("error", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}