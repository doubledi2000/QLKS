<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <section class="row">
        <div class="col mt-4">
          <ul class="row nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item" role="presentation">
                  <a class="nav-link ${empty showList ? 'active':'' }" id="roomEditing-tab" data-toggle="tab" href="#roomEditing" role="tab"
                      aria-controls="roomEditing" aria-selected="true">Phòng</a>
              </li>
              <li class="nav-item" role="presentation">
                  <a class="nav-link ${empty showList ? '':'active' }" id="roomList-tab" data-toggle="tab" href="#roomList" role="tab"
                      aria-controls="roomList" aria-selected="false">Danh sách phòng</a>
              </li>

          </ul>
         
          <div class="col tab-content" id="myTabContent">
              <div class="tab-pane fade ${empty showList ? 'show active':'' }" id="roomEditing" role="tabpanel"
                  aria-labelledby="roomEditing-tab">
                    <div class="row">
                        <div class="col-6 offset-3">
                            <form action="" method="post">
                                <div class="card">
                         	<div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Quản lý thông tin phòng</b>
                         	</div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4 offset-2">
                                                <div class="form-group">
                                                  <label for="roomCode">Mã phòng</label>
                                                  <input type="text" class="form-control" name="roomCode" id="roomCode" aria-describedby="helpId" placeholder="Mã phòng"
                                                  value="${room.roomCode }" ${room.roomCode != null?'readonly':'' }></div>
                                                  <small id="roomCodeHelpID"
											class="form-text text-muted">Mã đặt phòng tối đa 5 ký tự.</small>
                                            </div>
                                        </div>
                                            <div class="row">
                                                <div class="col-4 offset-2">
                                                  <div class="form-group">
                                                    <label for="roomNumber">Số phòng</label>
                                                    <input type="text" class="form-control" name="roomNumber" id="roomNumber" aria-describedby="helpId" placeholder="Số phòng"
                                                    value="${room.roomNumber }"> </div>  
                                                    <small id="roomCodeHelpID"
											class="form-text text-muted">Số phòng bao gồm 4 chữ số</small>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-4 offset-2">
                                                    <div class="form-group form-inline">
                                                      <label for="active">Tình trạng</label>
                                                      <select class="form-control ml-3" name="active" id="active">
                                                        <option ${room.active ? 'selected' : '' }>Sử dụng</option>
                                                        <option ${room.active ? '' : 'selected'} >Bảo trì</option>
                                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-8 offset-2">
                                                    <div class="form-group form-inline">
                                                      <label for="roomType">Loại phòng</label>
                                                      <select class="form-control ml-3" name="roomTypeCode" id="roomType">
                                                        <c:forEach var="item" items="${roomTypes }">
                                                        <c:choose>
                                                        	<c:when test="${item.roomTypeCode.equals(room.roomType.roomTypeCode)}">
                                                        		<option value="${item.roomTypeCode }" selected> ${item.roomTypeName }</option>
                                                        	</c:when>
                                                        	<c:otherwise>
                                                        	
                                                        		<option value="${item.roomTypeCode }" > ${item.roomTypeName }</option>
                                                        	</c:otherwise>
                                                        </c:choose>
                                                        </c:forEach>
                                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                            <div class="row">
                                                <div class="col-8 offset-2">
                                                    <div class="form-group">
                                                      <label for="note">Ghi chú</label>
                                                      <textarea class="form-control" name="note" id="note" rows="5">${room.note }</textarea>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row mt-15">
                                                <button class="btn btn-success ml-5" formaction="RoomManagementServlet/create">Create</button>
                                                <c:if test="${ not empty room.roomCode }">
                                                <button class="btn btn-warning ml-5" formaction="RoomManagementServlet/update">Update</button>
                                                <button class="btn btn-danger ml-5" formaction="RoomManagementServlet/delete">Delete</button>
                                                </c:if>
                                                <button class="btn btn-info ml-5" formaction="RoomManagementServlet/reset">Reset</button>
                                            </div>
                                    </div>
                        
                                </div>
                            </form>
                        </div>
                    </div>
                  </div>
              <div class="tab-pane fade ${empty showList ? '':'show active' }" id="roomList" role="tabpanel" aria-labelledby="roomList-tab">
              <h3 class="text-center">Danh sách phòng</h3>
                    <table class="table table-bordered">
                        <tr>
                            <th>Mã phòng</th>
                            <th>Số phòng</th>
                            <th>Loại phòng</th>
                            <th>Tình trạng</th>
                            <th>Note</th>
                            <th>&nbsp;</th>
                        </tr>
                        <c:forEach var="item" items="${rooms }">
                        
                        <tr>
                            <td>${item.roomCode }</td>
                            <td>${item.roomNumber }</td>
                            <td>${item.roomType.roomTypeName }</td>
                            <td>${item.active ? 'Active' : 'Unactive' }</td>
                            <td>${item.note }</td>
                            <td>
                                <a href="RoomManagementServlet/edit?roomCode=${item.roomCode }"> <i class="fa fa-id-card" aria-hidden="true"></i>Edit</a>
                                <a href="RoomManagementServlet/delete?roomCode=${item.roomCode }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                                         <div class="d-flex justify-content-center">
					
                      <nav aria-label="...">
						<ul class="pagination">
						
							<c:forEach begin="1" end="${numberOfPage }" var = "i">
							
							<li class="page-item ${i==currentPage ?'active' :'' }"><a class="page-link" href="RoomManagementServlet?page=${i }&showList=true">${i }</a></li>
							</c:forEach>	
						
						</ul>
					</nav>
					</div>
              </div>

          </div>
        </div>
      </section>
