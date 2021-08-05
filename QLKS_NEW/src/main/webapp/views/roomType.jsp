<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <section class="row">
            <div class="col mt-4">
              <ul class="row nav nav-tabs" id="myTab" role="tablist">
                  <li class="nav-item" role="presentation">
                      <a class="nav-link ${empty showList ?'active':'' }" id="roomTypeEditing-tab" data-toggle="tab" href="#roomTypeEditing" role="tab"
                          aria-controls="roomTypeEditing" aria-selected="true">Loại phòng</a>
                  </li>
                  <li class="nav-item" role="presentation">
                      <a class="nav-link ${empty showList ?'':'active' }" id="roomTypeList-tab" data-toggle="tab" href="#roomTypeList" role="tab"
                          aria-controls="roomTypeList" aria-selected="false">Danh sách loại phòng</a>
                  </li>
   
              </ul>
             
              <div class="col tab-content" id="myTabContent">
                  <div class="tab-pane fade ${empty showList ?'show active':'' }" id="roomTypeEditing" role="tabpanel"
                      aria-labelledby="roomTypeEditing-tab">

                      <div class="row">
                          <div class="col-6 offset-3">
                            <form action="" method="post">
                                <div class="card">
                                  	<div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Quản lý thông tin loại phòng</b>
                         	</div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4 offset-2">
                                                <div class="form-group">
                                                    <label for="roomTypeCode">Mã loại phòng</label>
                                                    <input type="text" class="form-control" name="roomTypeCode" id="roomTypeCode" aria-describedby="roomTypeCodeHelpId" placeholder="Room type code" 
                                                    value="${roomType.roomTypeCode }" ${roomType.roomTypeCode != null?'readonly':'' }>
                                                  </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-4 offset-2">
                                                <div class="form-group">
                                                  <label for="roomTypeName">Tên loại phòng</label>
                                                  <input type="text" class="form-control" name="roomTypeName" id="roomTypeName" aria-describedby="helpId" placeholder="roomTypeName"
                                                  	value="${roomType.roomTypeName }"
                                                  > </div>
                                            </div>

                                        </div>
                                        <div class="row">
                                        <div class="col-4 offset-2">
                                            <div class="form-group">
                                              <label for="pricePerHour">Giá mỗi giờ</label>
                                              <input type="text" class="form-control" name="pricePerHour" id="pricePerHour" aria-describedby="helpId" placeholder="Price per hour"
                                              value="${roomType.pricePerHour }"> </div>
                                        </div>

                                        </div>

                                        <div class="row">
                                        <div class="col-4 offset-2">
                                            <div class="form-group">
                                              <label for="pricePerDay">Giá 1 ngày đêm</label>
                                              <input type="text" class="form-control" name="pricePerDay" id="pricePerDay" aria-describedby="helpId" placeholder="pricePerDay"
                                              value="${roomType.pricePerDay }"> </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-8 offset-2">
                                            <div class="form-group">
                                              <label for="note">Ghi chú</label>
                                              <textarea class="form-control" name="note" id="note" rows="4"
                                              >${roomType.note }</textarea>
                                            </div>    
                                        </div>
                                        </div>
                                        <div class="row mt-15">
                                            <button class="btn btn-success ml-5" formaction="RoomTypeManagementServlet/create">Create</button>
                                            <c:if test="${not empty roomType.roomTypeCode }">
                                            <button class="btn btn-warning ml-5" formaction="RoomTypeManagementServlet/update">Update</button>
                                            <button class="btn btn-danger ml-5" formaction="RoomTypeManagementServlet/delete">Delete</button>
                                            </c:if>
                                            <button class="btn btn-info ml-5" formaction="RoomTypeManagementServlet/reset">Reset</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                          </div>
                      </div>
                  </div>
                  <div class="tab-pane fade ${empty showList ?'':'show active' }" id="roomTypeList" role="tabpanel" aria-labelledby="roomTypeList-tab">
                    <table class="table table-bordered">
                    <h3 class="text-center">Danh sách loại phòng</h3>
                        <tr>
                            <th>Mã loại phòng</th>
                            <th>Tên loại phòng</th>
                            <th>Giá mỗi giờ</th>
                            <th>Giá một đêm</th>
                            <th>Ghi chú</th>
                            <th>&nbsp;</th>
                        </tr>
						<c:forEach var="item" items="${roomTypes }">
                       <tr>
                            <td>${item.roomTypeCode }</td>
                            <td>${item.roomTypeName }</td>
                            <td>${item.pricePerHour }</td>
                            <td>${item.pricePerDay }</td>
                            <td>${item.note }</td>
                            <td>
                                <a href="RoomTypeManagementServlet/edit?roomTypeCode=${item.roomTypeCode }"> <i class="fa fa-id-card" aria-hidden="true"></i>Edit</a>
                                <a href="RoomTypeManagementServlet/delete?roomTypeCode=${item.roomTypeCode }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
                            </td>
                        </tr>
						</c:forEach>
                    </table>
                     <div class="d-flex justify-content-center">
					
                      <nav aria-label="...">
						<ul class="pagination">
						
							<c:forEach begin="1" end="${numberOfPage }" var = "i">
							
							<li class="page-item ${i==currentPage ?'active' :'' }"><a class="page-link" href="RoomTypeManagementServlet?page=${i }&showList=true">${i }</a></li>
							</c:forEach>	
						
						</ul>
					</nav>
					</div>
                  </div>
  
              </div>
            </div>
          </section>
   