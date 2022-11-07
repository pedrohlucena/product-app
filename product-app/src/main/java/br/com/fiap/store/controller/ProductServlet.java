package br.com.fiap.store.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Product;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static List<Product> list = new ArrayList<Product>();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("products", list);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double value = Double.parseDouble(request.getParameter("value"));

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date expirationDate = formatter.parse(request.getParameter("expiration-date"));
					
			Product product = new Product(name, quantity, value, expirationDate);
			list.add(product);
			
			request.setAttribute("message", "Produto cadastrado!");
			request.getRequestDispatcher("product-registration.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
