<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<section class="row">
	<div class="col mt-4">
		<ul class="row nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation"><a
				class="nav-link active" id="billCheck-tab" data-toggle="tab"
				href="#billCheck" role="tab" aria-controls="billCheck"
				aria-selected="true">Kiểm tra hóa đơn</a></li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="statistic-tab" data-toggle="tab" href="#statistic" role="tab"
				aria-controls="statistic" aria-selected="false">Thống kê</a></li>

		</ul>

		<div class="col tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="billCheck" role="tabpanel"
				aria-labelledby="billCheck-tab">
				<div class="row">
					<form action="" method="post" class="form-inline">
						<div class="col-8 offset-4 mt-3 form-inline">
						<div class="form-group form-inline">
                      <label for="customer"><b>Khách hàng</b></label>
                      <input type="text" name="customerId" id="customer" class="form-control" placeholder="khách hàng" aria-describedby="helpId"
                      value="${customerId }" style="margin-left: 10px; margin-right: 10px">
                      <label for="billType"><b>Loại hóa đơn: </b><select class="form-control mr-3" name="billType" id="billType">
                      		<option value="1">Hóa đơn thanh toán</option>
                      		<option value="2">Hóa đơn hủy phòng</option>
                      		<option value="3">Tất cả</option>
                      </select>
                      </label>
                      <button class="btn btn-success " formaction="ReportManagementServlet/search">Tìm kiếm</button>
                  </div>
          
					</div>
					</form>
				</div>
				<c:if test="${not empty bills }">
				<div class="d-flex justify-content-center mt-5 mb-10">
					<h3 class="text-center">Danh sách hóa đơn</h3>
				</div>
				<form action="" method="post">
				<div class="row">
					<div class="col-10 offset-1 mt-10">
						<table class="table table-bordered">
							<tr>
							<th>Mã hóa đơn</th>
							<th>Mã đặt phòng</th>
							<th>Ngày checkout</th>
							<th>Khách hàng</th>
							<th>Nhân viên</th>
							<th>Tình trạng</th>
							<th>&nbsp;</th>
							</tr>
							<c:forEach var="item" items="${bills }">
								<tr>
									<td>${item.billCode }</td>
									<td>${item.booking.bookingCode }</td>
									<td>${item.checkoutDate }</td>
									<td>${item.booking.customer.fullname }</td>
									<td>${item.booking.employee.fullname }</td>
									<td>${item.billType ? 'Hóa đơn thanh toán' : 'Hóa đơn hủy đặt phòng'}</td>
									<td>
										<a href="ReportManagementServlet/seeDetail?billCode=${item.billCode }" target="_blank"><i class="fa fa-credit-card" aria-hidden="true" ></i>Chi tiết</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				</c:if>
				</form>
			</div>
			<div class="tab-pane fade" id="statistic" role="tabpanel"
				aria-labelledby="statistic-tab">
				<form action="" method="post" target="_blank">
					<div class="col-5 offset-3">
						<div class="card">
							 <div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Thống kê doanh thu</b>
                         	</div>
							<div class="card-body">
								<div class="form-group col-8">
									<label for="from">Từ</label> <input type="date"
										class="form-control" name="startDate" id="from"
										aria-describedby="fromHelpId" placeholder=""> 
								</div>
								<div class="form-group col-8">
									<label for="to">Đến</label> <input type="date"
										class="form-control" name="endDate" id="to"
										aria-describedby="toHelpId" placeholder="">
								</div>
								<div class="form-group form-inline">
									<label for="statisticBy">Thống kê theo</label> <select
										class="form-control ml-5   " name="statisticBy"
										id="statisticBy">
										<option value="day">Ngày</option>
										<option value="month">Tháng</option>
									</select>
								</div>
								<div class="d-flex justify-content-center">
									<button class="btn btn-success" formaction="ReportManagementServlet/statistic" target="_blank">Thống kê</button>
								</div>
							</div>
					
						</div>
					</div>
				</form>

			</div>

		</div>
	</div>
</section>