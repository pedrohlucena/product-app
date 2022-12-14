<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produto</title>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-2">Cadastro de produto</h1>

		<jsp:include page="callback-message.jsp"></jsp:include>

		<form action="product" method="post">
			<input type="hidden" name="action" value="save" > 
			<div class="form-group">
				<label for="id-nome">Nome</label> <input type="text" name="name"
					id="id-nome" class="form-control mb-3">
			</div>
			<div class="form-group">
				<label for="id-qtd">Quantidade</label> <input type="text"
					name="quantity" id="id-qtd" class="form-control mb-3">
			</div>
			<div class="form-group">
				<label for="id-valor">Valor</label> <input type="text" name="value"
					id="id-valor" class="form-control mb-3">
			</div>
			<div class="form-group">
				<label for="id-data-fabricacao">Data de fabricação</label> <input type="date" name="manufacturing-date"
					id="id-data-fabricacao" class="form-control mb-3">
			</div>
			
			<div class="form-group">
				<label for="id-categoria">Categoria</label>
				<select class="form-select mb-3 form-control" aria-label="Default select example" id="id-categoria" name="category">
				  <option selected value="0">Selecione uma categoria para seu produto</option>
				  
				  <c:forEach items="${categories}" var="category">
				  		<option value="${category.code}">${category.name}</option>
				  </c:forEach>
				</select>			
			</div>
			<input type="submit" value="Salvar" class="btn btn-primary">
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>



