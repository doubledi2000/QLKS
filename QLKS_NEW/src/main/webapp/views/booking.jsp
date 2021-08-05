<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Date"%>

<section class="row">
	<div class="col mt-4">
		<ul class="row nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation"><a
				class="nav-link active" id="userEditing-tab" data-toggle="tab"
				href="#userEditing" role="tab" aria-controls="userEditing"
				aria-selected="true">Đặt phòng</a></li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="userList-tab" data-toggle="tab" href="#userList" role="tab"
				aria-controls="userList" aria-selected="false">Danh sách đặt phòng</a></li>

		</ul>

		<div class="col tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="userEditing"
				role="tabpanel" aria-labelledby="userEditing-tab">

				<div class="row">

					<div class="col-9 bl-2 offset-1">
					<div class="card">
                         	<div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Quản lý đặt phòng</b>
                         	</div>
					 <div class="card-body">
						<form action="" method="post">
							<div class="row mt-3 mb-3" >

								<div class="col-6">
									<div class="form-group">
										<label for="bookingCode">Mã đặt phòng</label> <input
											type="text" class="form-control" name="bookingCode"
											id="bookingCode" aria-describedby="bookingCodeHelpId"
											placeholder="booking code" value="${booking.bookingCode }"
											readonly> 
									</div>

									<div class="form-group">
										<label for="phoneNumber">Số điện thoại khách hàng</label> <input
											type="text" class="form-control" name="phoneNumber"
											id="phoneNumber" aria-describedby="phoneNumber"
											placeholder="Phonenumber"
											value="${booking.customer.phoneNumber }"> <small
											id="customerHelpId" class="form-text text-muted">Phone
											number is required</small>
									</div>
									<div class="form-group">
										<label for="employee">Nhân viên</label> <input type="text"
											class="form-control" name="employeeCode" id="employee"
											aria-describedby="employeeHelpId" placeholder="Employee"
											value="${employeeLogin.fullname }" readonly> <small
											id="employeeHelpId" class="form-text text-muted">Help
											text</small>
									</div>
									<div class="form-group">
										<label for="checkinDate">Thời gian checkin</label> <input
											type="datetime-local" class="form-control" name="checkinDate"
											id="checkinDate" aria-describedby="helpId"
											placeholder="Checkin Dated" value="${checkinDate }"
											${booking.stt == 2 ?'readonly':'' }> <small
											id="checkinDateHelpId" class="form-text text-muted">Checkin
											Date is required</small>
									</div>
									<div class="form-inline">
										<div class="form-group">
											<label for="stt">Tình trạng</label> <select
												class="form-control ml-3" name="stt" id="stt">
												<c:choose>
												<c:when test="${booking.stt == 2 }">
													<option value="2" ${booking.stt == 1 ?'selected' :'' }>Sử dụng
														</option>
												</c:when>
												<c:otherwise>
												<option value="1" seleted>Chờ checkin</option>
												<option value="2">Sử
													dụng</option>
													</c:otherwise>
													</c:choose>
											</select>
										</div>
									</div>


								</div>
								<div class="col-6">
									<div class="form-group">
										<label for="deposit">Tiền cọc</label> <input type="number"
											class="form-control" name="deposit" id="deposit"
											aria-describedby="helpId" placeholder="Tiền cọc"
											value="${not empty booking.deposit ? booking.deposit :'0' }">
									</div>

									<div class="form-group">
										<label for="extraPerson">Số người quá quy định</label> <input
											type="number" class="form-control" name="extraPerson"
											id="extraPerson" aria-describedby="helpId"
											placeholder="Extra person"
											value="${not empty booking.extraPerson ? booking.extraPerson : '0' }">
									</div>

									<div class="form-group">
										<label for="pricePerOne">Tiền phạt trên mỗi người quá quy định</label> <input
											type="number" class="form-control" name="pricePerOne"
											id="pricePerOne" aria-describedby="helpId" placeholder=""
											value="${not empty booking.pricePerOne ? booking.pricePerOne : '0' }"
											step=0.1>
									</div>
									<div class="form-group">
										<label for="fines">Phạt hủy phòng</label> <input type="number"
											class="form-control" name="fines" id="fines"
											aria-describedby="helpId" placeholder="Phạt"
											value="${not empty booking.fines ? booking.fines : '0' }">
									</div>
									<div class="form-group">
										<label for="refund">Hoàn cọc(%)</label> <input type="number"
											class="form-control" name="refund" id="refund"
											aria-describedby="helpId" placeholder="Hoàn tiền"
											value="${booking.refund > 0 && booking.refund <=100 ? booking.refund : '0' }">
									</div>
									<div class="form-group">
										<label for="note">Ghi chú</label>
										<textarea class="form-control" name="note" id="note" rows="4">${booking.note }</textarea>
									</div>
								</div>
								
								<div class="col-10 offset-2 mt-5 mb-5">
								<c:if test="${empty booking.bookingCode }">
									<button type="submit" class="btn btn-primary ml-3"
										formaction="BookingServlet/create">Tạo mới</button>
								</c:if>	
									<button type="submit" class="btn btn-warning ml-3 "
										formaction="BookingServlet/update">Cập nhật</button>
									<button type="submit" class="btn btn-danger ml-3"
										formaction="BookingServlet/delete">Xóa </button>
									<button type="submit" class="btn btn-info ml-3"
										formaction="BookingServlet/reset">Làm mới</button>

									<c:if test="${booking.stt == 1 }">
										<button type="submit" class="btn btn-success ml-3"
											formaction="BookingServlet/bookEditing">Đặt phòng</button>
										<button type="submit" class="btn btn-success ml-3"
											formaction="BookingServlet/confirmation">Gửi email</button>
										<button type="submit" class="btn btn-success ml-3"
											formaction="BookingServlet/cancel">Hủy đặt phòng</button>
									</c:if>
									<c:if test="${booking.stt == 2 }">
										<button type="submit" class="btn btn-success ml-3"
											formaction="BookingServlet/bookEditing">Đặt phòng</button>
										<button type="submit" class="btn btn-success ml-3"
											formaction="BookingServlet/checkout" target="_blank">Checkout</button>


									</c:if>

								</div>
								
						</form>
						</div>
						</div>
						 <h3 class="text-center">Danh sách phòng</h3>
						<table class="table table-bordered">
						
							<tr>
								<th>Loại phòng</th>
								<th>Phòng</th>
								<th>Thời gian bắt đầu</th>
								<th>Thời gian kết thúc</th>
								<th>Giá</th>
								<th>Thành tiền</th>
							</tr>
							
							<c:forEach var="item" items="${bookingDetails }">
								<tr  >
									<td>${item.room.roomType.roomTypeName }</td>
									<td>${item.room.roomNumber }</td>
									<td>${item.startDay }</td>
									<td>${item.endDay }</td>
									<td>${item.price }</td>
									<td>${item.amount }
								</tr>
								</tr>
							</c:forEach>
							 
						</table>
						<div class="col-6 offset-6">
							<c:if test="${not empty booking.totalRoomCharge }">
								<form class="form-inline">
									<div class="form-group">
										<span><b>Số tiền còn lại cần thanh toán:</b></span> <span
											class="ml-4"><b>${booking.totalRoomCharge + booking.fines + booking.pricePerOne*booking.extraPerson - booking.deposit }
												đồng</b></span>

									</div>
								</form>
							</c:if>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="tab-pane fade" id="userList" role="tabpanel"
			aria-labelledby="userList-tab">
			<h3 class="text-center">Danh sách đặt phòng</h3>
			<table class="table table-bordered">
				<tr>
					<th>Mã đặt phòng</th>
					<th>Khách hàng</th>
					<th>Ngày checkin</th>
					<th>Đặt cọc</th>
					<th>Ghi chú</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="item" items="${bookings }">
					<c:choose>
						<c:when test="${item.stt == 2 }">
						<tr class="table-info">
						</c:when>
						<c:when test="${ item.checkinDate < currentDate}">
						<tr class="table-danger">
						</c:when>
						<c:otherwise>
						<tr>		
						</c:otherwise>
					</c:choose>
					
						<td>${item.bookingCode }</td>
						<td><abbr title="${item.customer.phoneNumber }">${item.customer.fullname }</abbr></td>
						<td>${item.checkinDate }</td>
						<td>${item.deposit }</td>
						<td>${item.note }</td>
						<td><a
							href="BookingServlet/edit?bookingCode=${item.bookingCode}">Edit</a>
						
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</section>