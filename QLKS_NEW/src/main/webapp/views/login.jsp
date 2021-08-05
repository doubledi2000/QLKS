<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

 <main class="container-fluid">
		<div class="offset-4 col-lg-4">
		<form action="" method="POST">
			<div class="card">
			<jsp:include page="/views/common.jsp"></jsp:include>
				<div class="card-header">
					<b>Login to system</b>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="account">username</label> <input type="text"
							class="form-control" name="account" id="account"
							aria-describedby="usernameHelpId" placeholder="account">
						<small id="usernameHelpId" class="form-text text-muted">Username
							is required</small>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" id="password"
							aria-describedby="passwordHelpId" placeholder="password">
						<small id="passwordHelpId" class="form-text text-muted">Password
							is required</small>
					</div>
					<div class="form-check form-check-inline">
						<label><input type="checkbox" class="form-check-input"
							name="remember">Remember me</label>
					</div>
				</div>
				<div class="card-footer text-muted">
					<button class="btn btn-success" formaction="Login">Login</button>
				</div>
			</div>
		</form>
	</div>
		
    </main>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous">
    </script>
    <c:if test="${not empty page.scriptUrl }">
    	<jsp:include page="${page.scriptUrl }"></jsp:include>
    </c:if>
    <script>
        $('#myTab a').on('click', function (event) {
            event.preventDefault()
            $(this).tab('show')
        })
    </script>
</body>

</html>
	