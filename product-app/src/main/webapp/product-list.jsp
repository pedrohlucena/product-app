<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de produtos</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<fmt:setLocale value="pt-BR"/>
	
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">
		<h1 class="mt-2">Produtos</h1>
		
		<jsp:include page="callback-message.jsp"></jsp:include>
		
		<table class="table table-striped">
			<tr>
				<th>Nome</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Data de fabricação</th>
				<th></th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.quantity} ${(product.quantity == 1) ? "unidade" : "unidades"}</td>
					<td>
						<fmt:formatNumber value="${product.price}" type="currency" minFractionDigits="2" maxFractionDigits="2"/>
					</td>
					<td>
						<fmt:formatDate pattern="dd/MM/yyyy" value="${product.manufacturingDate.time}" />
					</td>
					<td>
						<c:url value="product" var="link">
							<c:param name="code" value="${product.code}"/>
							<c:param name="action" value="open-edition-form"/>
						</c:url>
						<a href="${link}">Editar</a>
					</td>		
				</tr>
			</c:forEach>
		</table>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>