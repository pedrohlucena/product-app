<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de produto</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container">
		<h1 class="mt-2">Atualização de produto</h1>
		
		<jsp:include page="callback-message.jsp"></jsp:include>
		
		<form action="product" method="post">
			<input type="hidden" name="action" value="edit" > 
			<input type="hidden" name="code" value="${product.code}">
			
			<div class="form-group">
				<label for="id-nome">Nome</label> <input type="text" name="name"
					id="id-nome" class="form-control mb-3" value="${product.name}">
			</div>
			<div class="form-group">
				<label for="id-valor">Valor</label> <input type="text" name="value"
					id="id-valor" class="form-control mb-3" value="${product.price}">
			</div>
			<div class="form-group">
				<label for="id-quantidade">Quantidade</label> <input type="text"
					name="quantity" id="id-quantidade" class="form-control mb-3"
					value="${product.quantity}">
			</div>
			<div class="form-group">
				<label for="id-fabricacao">Data de Fabricação</label> <input
					type="text" name="manufacturing-date" id="id-fabricacao"
					class="form-control mb-3"
					value='<fmt:formatDate value="${product.manufacturingDate.time}" pattern="dd/MM/yyyy"/>'>
			</div>
			<input type="submit" value="Salvar" class="btn btn-primary">
			<a href="product?action=list" class="btn btn-danger">Cancelar</a>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>