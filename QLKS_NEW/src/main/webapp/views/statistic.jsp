<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
    <title>Thống kê</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <base href="/QLKS_NEW/" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<main class="container">
	  <section class="row">

        <div class="col mt-10">
            <div class="col-10 offset-1">
                <table class="table table-bordered">
                    <tr>
                    <c:choose>
                    	<c:when test="${type == 2 }">
                    		<th>Tháng</th>
                        	<th>Năm</th>
                    	</c:when>
                    	<c:otherwise>
                    		<th>Ngày</th>
                    	</c:otherwise>
                    </c:choose>
                        <th>Tiền đặt cọc</th>
                        <th>Tiền phạt</th>
                        <th>Doanh thu phòng</th>
                        <th>Hoàn cọc</th>
                        <th>Tổng doanh thu</th>
                    </tr>
					<c:forEach var="item" items="${statistic }">
					 <tr>
						<c:choose>
							<c:when test="${type ==2 }">
							  <td>${item.month }</td>
                        		<td>${item.year }</td>
							</c:when>
							<c:otherwise>
								<td>${item.day}-${item.month }-${item.year }</td>
							</c:otherwise>
						</c:choose>
                        <td>${item.deposit }</td>
                        <td>${item.roomFines +item.personFines }</td>
                        <td>${item.rest}</td>
                        <td>${item.repay }</td>
                        <td>${item.revenue }</td>
                    </tr>
					
					</c:forEach>
                </table>
            </div>
        </div>
    </section>
</main>