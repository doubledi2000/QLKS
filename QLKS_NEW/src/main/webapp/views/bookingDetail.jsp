<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <form action="" method="post">
    <div class="form-inline col-6 offset-3">
        <div class="form-group mr-3">
          <label for="bookingCode">Booking code</label>
          <input type="text"
            class="form-control" name="bookingCode" id="bookingCode" aria-describedby="helpId" placeholder=""
            value="${bookingForm != null ? bookingForm.bookingCode : '' }" readonly>
        </div>
        <div class="form-group">
            <label for="bookingCode">Customer</label>
            <input type="text"
              class="form-control" name="fullname" id="bookingCode" aria-describedby="helpId" placeholder=""
              value="${bookingForm != null ? bookingForm.fullname : ''}" readonly>
          </div>
    </div>
    <div class="form-inline col-7 offset-3 mt-3" style="border-bottom: 1px solid black;">
      <div class="form-group mr-3">
        <label for="startDate">From</label>
        <input type="datetime-local"
          class="form-control" name="startDate" id="startDate" aria-describedby="helpId" placeholder=""
          value="${startDate != null ? startDate : date }" pattern="dd/MM/yyyy HH:mm">
      </div>
      <div class="form-group">
          <label for="endDate">to</label>
          <input type="datetime-local"
            class="form-control" name="endDate" id="endDate" aria-describedby="helpId" placeholder=""
            value="${endDate != null ? endDate : '2000-01-03 10:00' }" pattern="dd/MM/yyyy HH:mm">
        </div>
        <button class="btn btn-success" formaction="BookingDetailServlet/search">Tìm phòng trống</button>
        <h3 class="text-center ml-3 mb-3">
        	Danh sách phòng
        </h3>
        <c:if test="${roomBookedList != null }">
       	
        	<table class="table table-bordered mt-3 mb-3">
        		<tr>
        			<td>Mã phòng</td>
        			<td>Số phòng</td>
        			<td>Thời gian check in</td>
        			<td>Thời gian check out</td>
        			<td>Giá phòng</td>
        			<td>Thành tiền</td>
        			<td>&nbsp;</td>
        		</tr>
        	
        	<c:forEach var="item" items="${roomBookedList }">
        		<tr>
        			<td>${item.id }</td>
        			<td>${item.room.roomNumber }</td>
        			<td>${item.startDay }</td>
        			<td>${item.endDay }</td>
        			<td>${item.price }</td>
        			<td>${item.amount }</td>
        			<td>
        			  <button class="btn btn-danger" formaction="BookingDetailServlet/delete?id=${item.id }">Delete</button>
        			</td>
        		</tr>
        	</c:forEach>
        	</table>
        </c:if>
  </div>
    <div class="form-group">
     <table class="table table-bordered">
     	<tr>
     		<td>Loại phòng</td>
     		<td>Mã loại phòng</td>
     		<td>Số phòng</td>
     		<td>Gía theo giờ</td>
     		<td>Giá một đêm</td>
     		<td>Ghi chú</td>
     		<td>&nbsp;</td>
     	</tr>
     	<c:if test="${roomList != null }">
     	<c:forEach var="item" items="${roomList }">
     	<tr>
     		<td>${item.roomType.roomTypeName }</td>
     		<td>${item.roomCode }</td>
     		<td>${item.roomNumber }</td>
     		<td>${item.roomType.pricePerHour }</td>
     		<td>${item.roomType.pricePerDay }</td>
     		<td>${item.note }</td>
     		<td>
     			<button class="btn btn-success" formaction="BookingDetailServlet/book?roomCode=${item.roomCode }">Book</button>
     		</td>
     	</tr>
     	</c:forEach>
     	</c:if>
     </table>
		     <div class="d-flex justify-content-center">
     	<button class="btn btn-info" formaction="BookingDetailServlet/return">Quay lại</button>
     </div>
      </div>
    </div>
</form>