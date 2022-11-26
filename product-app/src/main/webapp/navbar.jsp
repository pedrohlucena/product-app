<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg"> 
  <div class="container-fluid">
    <a class="navbar-brand" href="home.jsp">FIAPStore</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="product?action=open-registration-form">Cadastro</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="product?action=list">Produtos</a>
        </li>
      </ul>
      <c:if test="${empty user}">
	   	  <c:if test="${not empty error}">
		      <span class="navbar-text text-danger" style="margin-right:10px">
		     	${error}
		      </span>
	   	  </c:if>
	      <form class="form-inline my-2 my-lg-0" action="login" method="post">
	      	  <div class="input-group">
			  	<input type="email" class="form-control" placeholder="E-mail" aria-label="Recipient's username" aria-describedby="basic-addon2" name="email">
			  	<input type="text" class="form-control" placeholder="Senha" aria-label="Recipient's username" aria-describedby="basic-addon2" name="password">
			  	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
			  </div>      
		  </form>
      </c:if>
      
      <c:if test="${not empty user }">
   		<span class="navbar-text">
    		${user}
    		<a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
	  	</span>	
   	  </c:if>
    </div>
  </div>
</nav>