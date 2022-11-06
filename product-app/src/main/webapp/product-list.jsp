<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de produtos</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container-fluid ps-0 pe-0">
		<h1 class="mt-2 ms-1">Produtos</h1>
		<table class="table table-striped">
			<tr>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
			</tr>
			<c:forEach items="${products }" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.quantity}</td>
					<td>${product.price}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>