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

import br.com.fiap.store.bean.Category;
import br.com.fiap.store.bean.Product;
import br.com.fiap.store.dao.CategoryDAO;
import br.com.fiap.store.dao.ProductDAO;
import br.com.fiap.store.exception.DBException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDao;
	private CategoryDAO categoryDao;

	@Override
	public void init() throws ServletException {
		super.init();
		productDao = DAOFactory.getProductDAO();
		categoryDao = DAOFactory.getCategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "list":
			list(request, response);
			break;
		case "open-edition-form":
			openEditionForm(request, response);
			break;
		case "open-registration-form":
			openRegistrationForm(request, response);
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

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double value = Double.parseDouble(request.getParameter("value"));

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar manufacturingDate = Calendar.getInstance();
			manufacturingDate.setTime(formatter.parse(request.getParameter("manufacturing-date")));
			
			int categoryCode = Integer.valueOf(request.getParameter("category"));
			
			Category productCategory = new Category();
			productCategory.setCode(categoryCode);

			Product product = new Product(name, quantity, value, manufacturingDate);
			product.setCategory(productCategory);

			productDao.save(product);
			request.setAttribute("message", "Produto cadastrado!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao cadastrar o produto.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Por favor, valide os dados.");
		}
		openRegistrationForm(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productCode = Integer.parseInt(request.getParameter("code"));
		Product productToBeEdited = productDao.fetchById(productCode);
		try {
			int categoryCode = Integer.valueOf(request.getParameter("category"));
			String productName = request.getParameter("name");
			int productQuantity = Integer.parseInt(request.getParameter("quantity"));
			double productPrice = Double.parseDouble(request.getParameter("value"));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Calendar productManufacturingDate = Calendar.getInstance();
			productManufacturingDate.setTime(formatter.parse(request.getParameter("manufacturing-date")));
			
			Category productCategory = new Category();
			productCategory.setCode(categoryCode);
			
			productToBeEdited = new Product(productCode, productName, productQuantity, productPrice, productManufacturingDate);
			productToBeEdited.setCategory(productCategory);
			productDao.update(productToBeEdited);
					
			request.setAttribute("message", "Produto atualizado!");
			list(request, response);
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao atualizar o produto.");
			request.setAttribute("product", productToBeEdited);
			loadCategoryOptions(request);
			request.getRequestDispatcher("product-edit.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Por favor, valide os dados.");
			request.setAttribute("product", productToBeEdited);
			loadCategoryOptions(request);
			request.getRequestDispatcher("product-edit.jsp").forward(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> productList = productDao.list();
		request.setAttribute("products", productList);
		request.getRequestDispatcher("product-list.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("code"));
		try {
			productDao.remove(id);
			request.setAttribute("message", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao remover o produto.");
		}
		list(request, response);
	}
	
	protected void openRegistrationForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		loadCategoryOptions(request);
		request.getRequestDispatcher("product-registration.jsp").forward(request, response);
	}
	
	protected void openEditionForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("code"));
		Product productToBeUpdated = productDao.fetchById(id);
		request.setAttribute("product", productToBeUpdated);
		loadCategoryOptions(request);
		request.getRequestDispatcher("product-edit.jsp").forward(request, response);
	}

	private void loadCategoryOptions(HttpServletRequest request) {
		List<Category> categoryList = categoryDao.list();
		request.setAttribute("categories", categoryList);
	}
}