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
		String action = request.getParameter("action");

		switch (action) {
		case "list":
			list(request, response);
			break;
		case "open-edition-form":
			int id = Integer.parseInt(request.getParameter("code"));
			Product productToBeUpdated = dao.fetchById(id);
			request.setAttribute("product", productToBeUpdated);
			request.getRequestDispatcher("product-edit.jsp").forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "save":
			save(request, response);
			break;
		case "edit":
			update(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		}
	}
	
	private void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double value = Double.parseDouble(request.getParameter("value"));

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

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("entrei no if");
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double value = Double.parseDouble(request.getParameter("value"));

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar manufacturingDate = Calendar.getInstance();
			manufacturingDate.setTime(formatter.parse(request.getParameter("manufacturing-date")));

			int code = Integer.parseInt(request.getParameter("code"));
			
			Product productToBeEdited = dao.fetchById(code);

			productToBeEdited.setName(name);
			productToBeEdited.setQuantity(quantity);
			productToBeEdited.setPrice(value);
			productToBeEdited.setManufacturingDate(manufacturingDate);

			dao.update(productToBeEdited);
			request.setAttribute("message", "Produto atualizado!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao atualizar o produto.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Por favor, valide os dados.");
		}
		
		list(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> productList = dao.list();
		request.setAttribute("products", productList);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("code"));
		try {
			dao.remove(id);
			request.setAttribute("message", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao remover o produto.");
		}
		list(request, response);
	}
}