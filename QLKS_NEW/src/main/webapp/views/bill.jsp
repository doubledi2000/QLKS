<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
  <head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
        <section class="row" style="margin-top: 10px;">
          <div class="col-6 offset-3">
              <div class="card">
                  
                  <div class="card-body">
                      <div class="row" style=" padding-bottom: 40px; border-bottom: 1px solid black;">
                          <div class="col-3" style="height: 80px; font-size: 30px;"><b>NUCE</b></div>
                          <div class="col-9" style="font-size: 12px;">
                              <div class="bold" style="color: RED;"><b>KHÁCH SẠN HỒ TÂY</b></div>
                              <div class="row">
                                  <div class="col-4">
                                      <div><b>MÃ HÓA ĐƠN:</b></div>
                                  </div>
                                  <div class="col-8">
                                  	${bill.billCode }
                                  </div>
                              </div>
                              <div class="row">
                                  <div class="col-4">
                                      <div>Địa chỉ:</div>
                                  </div>
                                  <div class="col-8">
                                    	${hotel.addr }
                                  </div>
                              </div>
                              <div class="row">
                                  <div class="col-4">
                                      Số điện thoại:
                                     
                                  </div>
                                  <div class="col-8">
                                     ${hotel.phoneNumber }
                                  </div>
                              </div>
                              <div class="row">
                                  <div class="col-4">
                                      Email:
                                  </div>
                                  <div class="col-8">
                                      ${hotel.email }
                                  </div>
                              </div>
                              <div class="row" > 
                                  <div class="col-4">
                                      Số tài khoản: 
                                  </div>
                                  <div class="col-8">
                                      123414812847
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row mt-3" style="border-bottom: 1px solid black;">
                          <div class="col-5 offset-3 mb-3" style="text-align: center;">
                              <div style="color: red;">
                                  <b>${bill.billType ? 'HÓA ĐƠN THANH TOÁN TIỀN PHÒNG' :'HÓA ĐƠN HOÀN CỌC' }</b>
                              </div>
                              <div style="font-style: italic;">
                              Ngày:   ${checkoutDate }
                              </div>
                          </div>
                      </div>
                      <div class="row mt-3 mb-3">
                          <div class="col-12">
                              <div class="row">
                                  <span class="col-4">Họ tên khách hàng:  </span>
                                  <span class="col-8"><b>${bill.booking.customer.fullname }</b></span>
                              </div>
                          </div>
                          <div class="col-12">
                              <div class="row">
                                  <span class="col-4">Số điện thoại:  </span>
                                  <span class="col-8"><b>${bill.booking.customer.phoneNumber }</b></span>
                              </div>
                          </div>
                          <div class="col-12">
                              <div class="row">
                                  <span class="col-4">Địa chỉ:</span>
                                  <span class="col-8"><b>${bill.booking.customer.addr }</b></span>
                              </div>
                          </div>
                      </div>
                      <c:choose>
						<c:when test="${bill.billType }">         
                      <table class="table table-bordered">
                          <tr>
                              <th>Số phòng</th>
                              <th>Ngày bắt đầu</th>
                              <th>Ngày kết thúc</th>
                              <th>Giá</th>
                              <th>Thành tiền</th>
                          </tr>
                          <c:forEach var="item" items="${bookingDetails}"> 
                          <tr>
                                 <td>${item.room.roomNumber }</td>
                                <td>${item.startDay }</td>
                                <td>${item.endDay }</td>
                                <td>${item.price }</td>
                                <td>${item.amount }</td>
                          </tr>
                          </c:forEach>
                          <tr>
                              <td colspan ="5" style="text-align: right;"> 
                                  <span>Tổng thanh toán:</span>
                                  <span class="mr-3 ml-3">${bill.booking.totalRoomCharge } vnđ</span>
                              </td>
                          </tr>
                          <tr>
                              <td colspan="5" style="text-align: right;">
                                  <span>Đặt cọc:</span>
                                  <span class="mr-3 ml-3">${bill.booking.deposit } vnđ</span>
                              </td>
                          </tr>
                          <tr>
                              <td colspan="5" style="text-align: right;">
                                  <span>Phạt phòng: </span>
                                  <span class="mr-3 ml-3">${bill.booking.fines } vnđ</span>
                              </td>
                          </tr>

                          <tr>
                              <td colspan="5" style="text-align: right;">
                                  <span>Phạt người: </span>
                                 <span class="mr-3 ml-3">${bill.booking.extraPerson * bill.booking.pricePerOne } vnđ</span>
                              </td>
                          </tr>

                          <tr>
                              <td colspan="5" style="text-align: right;">
                                  <span>Số tiền còn lại cần thanh toán</span>
                                  <span class="mr-3 ml-3">${bill.booking.totalRoomCharge - bill.booking.deposit + bill.booking.fines + bill.booking.extraPerson * bill.booking.pricePerOne } vnđ</span>
                              </td>
                          </tr>
                      </table>

                      <div class="row mt-3 mb-3">
                          <div class="col-6 offset-3" style="font:inherit;">
                              (Giá đã bao gồm thuế GTGT)
                          </div>
                      </div>
                      </c:when> 
                      <c:otherwise>
                      	    
                              <div class="row">
                                  <span class="col-4">Số tiền đã đặt cọc:</span>
                                  <span class="col-8"><b>${bill.booking.deposit }</b> vnđ</span>
                              </div>
                         
                          
                      	   
                              <div class="row">
                                  <span class="col-4">Tiền phạt: :</span>
                                  <span class="col-8"><b>${bill.booking.fines }</b> vnđ</span>
                              </div>
                      
                         
                              <div class="row">
                                  <span class="col-4">Số tiền nhận lại <span></span>:</span>
                                  <span class="col-8"><b>${(bill.booking.deposit - bill.booking.fines) * bill.booking.refund / 100 }</b> vnđ (${bill.booking.refund}%)</span>
                              </div>
                  
                      </c:otherwise>  
                      </c:choose>       
                  </div>
              </div>
          </div>
         
       
        </section>
  </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>