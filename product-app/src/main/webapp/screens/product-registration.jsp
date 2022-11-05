<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produto</title>	
<jsp:include page="/snippets/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/snippets/navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<h1 class="mt-2">Cadastro de produto</h1>
		
		<form action="product" method="post">
        <div class="form-group">
          <label for="id-nome">Nome</label>
          <input type="text" name="nome" id="id-nome" class="form-control mb-3">
        </div>
        <div class="form-group">
          <label for="id-qtd">Quantidade</label>
          <input type="text" name="quantidade" id="id-qtd" class="form-control mb-3">
        </div>
        <div class="form-group">
          <label for="id-valor">Valor</label>
          <input type="text" name="valor" id="id-valor" class="form-control mb-3">
        </div>
        <input type="submit" value="Salvar" class="btn btn-success">
      </form>
	</div>
	<jsp:include page="/snippets/footer.jsp"></jsp:include>
</body>
</html>