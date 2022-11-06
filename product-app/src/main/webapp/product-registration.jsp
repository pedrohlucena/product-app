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
	<div class="container-fluid">
		<h1 class="mt-2">Cadastro de produto</h1>

		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>

		<form action="<%=(request.getContextPath() + "/product")%>"
			method="post">
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
			<input type="submit" value="Salvar" class="btn btn-success">
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>