 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
        <form action="HotelInfoServlet" method="post">
            <section class="row">
      <div class="col-4 offset-4">
          <div class="card">
              <div class="card-header" style="text-align: center;">
                  <b style="font-size: 20px;">Thông tin khách sạn</b>
              </div>
              <div class="card-body">
                  <div class="form-group">
                    <label for="id">ID</label>
                    <input type="text"
                      class="form-control" name="id" id="id" aria-describedby="helpId" placeholder="id" readonly style="background: none;"
                      value="${hotel.id }">
                  </div>
                  <div class="form-group">
                    <label for="fullname">Tên khách sạn</label>
                    <input type="text" class="form-control" name="fullname" id="fullname" aria-describedby="helpId" placeholder="Tên khách sạn"
                    value="${hotel.fullname }">
                  </div>
                      <div class="form-group">
                    <label for="addr">Địa chỉ</label>
                    <input type="text"
                      class="form-control" name="addr" id="addr" aria-describedby="helpId" placeholder="Địa chỉ"
                      value="${hotel.addr }">
                  </div>
                  <div class="form-group">
                    <label for="phoneNumber">Số điện thoại</label>
                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" aria-describedby="phoneNumberHelpId" placeholder="Số điện thoại"
                    value="${hotel.phoneNumber }">
                    
                  </div>
                  <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email"
                      class="form-control" name="email" id="email" aria-describedby="emailHelpId" placeholder="Email"
                      value="${hotel.email }"> 
                  </div>
     
                  <div class="form-group">
                    <label for="emailPassword">Mật khẩu email</label>
                    <input type="password"
                      class="form-control" name="emailPassword" id="emailPassword" aria-describedby="helpId" placeholder="Mật khẩu"
                      value="${hotel.emailPassword }">
                  </div>
    
   
                  <button class="btn btn-warning ml-20">Cập nhật</button>
                  </div>
				
              </div>
          </div>
        </div>
    </section>
    </form>