package br.com.fiap.store.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Product;
import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getProdutoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> productList = dao.list();
		request.setAttribute("products", productList);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double value = Double.parseDouble(request.getParameter("value"));
			
			System.out.println(request.getParameter("manufacturing-date"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar manufacturingDate = Calendar.getInstance();
			manufacturingDate.setTime(formatter.parse(request.getParameter("manufacturing-date"))); 
					
			Product product = new Product(name, quantity, value, manufacturingDate);
			
			dao.save(product);
			
			request.setAttribute("message", "Produto cadastrado!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao cadastrar o produto.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Por favor, valide os dados.");
		} 
		request.getRequestDispatcher("product-registration.jsp").forward(request, response);
	}
}
