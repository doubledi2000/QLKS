<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">

<head>
    <title>${page.title }</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <base href="/QLKS_NEW/" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <main class="container-fluid">
        <header class="row pt-5 pb-4"  >
            <div class="col-lg-9">
               
            </div>
            <div class="col-lg-3 text-right">
                <img src="uploads/TKS1.jpg" alt="" class="mr-4 img-fluid" style="width: 60px;">
            </div>
        </header>
        <!-- nav -->
        <nav class="row">
          <nav class="col navbar navbar-expand-sm navbar-light bg-light">
            <a class="navbar-brand" href="HotelInfoServlet">NUCE</a>
            <button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
                aria-expanded="f
                alse" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavId">
              <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                  <a class="nav-link" href=HotellInfoServlet><i class="fa fa-home" aria-hidden="true"></i>Home <span class="sr-only">(current)</span></a>
                </li>
               <li class="nav-item">
                 <a class="nav-link" href="ProfileServlet"><i class="fa fa-comment" aria-hidden="true"></i> Tài khoản</a>
               </li>
               
                <li class="nav-item dropdown ">
                  <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   <i class="fa fa-user" aria-hidden="true"></i> 
                   Dịch vụ</a>
                  <div class="dropdown-menu" aria-labelledby="dropdownId">
                    <a class="dropdown-item" href="BookingServlet">Quản lý đặt phòng</a>
                    <a class="dropdown-item" href="CustomerManagementServlet">Quản lý khách hàng</a>
                    
                    <a class="dropdown-item" href="RoomManagementServlet">Quản lý phòng</a>
                    
                    <a class="dropdown-item" href="RoomTypeManagementServlet">Quản lý loại phòng</a>
                    
                    <a class="dropdown-item" href="EmployeeManagementServlet">Quản lý nhân viên</a>
                    
                    <a class="dropdown-item" href="ReportManagementServlet">Thống kê</a>
                  </div>
                </li>
              </ul>
            </div>
          </nav>
        </nav>
			<!-- content -->
		<jsp:include page="/views/common.jsp"></jsp:include>
		<jsp:include page="${page.contentUrl}"></jsp:include>
		
      <footer class="row">
			<div class="col text-center border-top" style="padding-top: 20px; margin-bottom: 20px >
				<strong ">NUCE &copy;2021.</strong>
			</div>
		</footer>
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