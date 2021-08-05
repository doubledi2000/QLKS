<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <section class="row">
            <div class="col mt-4">
              <ul class="row nav nav-tabs" id="myTab" role="tablist">
                  <li class="nav-item" role="presentation">
                      <a class="nav-link  ${empty showList ?'':'active' }" id="userEditing-tab" data-toggle="tab" href="#userEditing" role="tab"
                          aria-controls="userEditing" aria-selected="true">Khách hàng</a>
                  </li>
                  <li class="nav-item" role="presentation">
                      <a class="nav-link  ${empty showList ?'active':'' }" id="userList-tab" data-toggle="tab" href="#userList" role="tab"
                          aria-controls="userList" aria-selected="false">Danh sách khách hàng</a>
                  </li>
   
              </ul>
             
              <div class="col tab-content" id="myTabContent">
                  <div class="tab-pane fade ${empty showList ?'':'show active' }" id="userEditing" role="tabpanel"
                      aria-labelledby="userEditing-tab">
                     <form action="" method="post">
                         <div class="card">
                         	<div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Thông tin khách hàng</b>
                         	</div>
                             <div class="card-body">
                                 <div class="row">
                                     <div class="col-4 offset-1">
                                        <div class="form-group">
                                          <label for="customer">Số điện thoại</label>
                                          <input type="text" class="form-control" name="phoneNumber" id="customer" aria-describedby="customerHelpId" placeholder="Phone number"
                                          value="${customer.phoneNumber }" ${customer.phoneNumber == null ?'':'readonly'  }>
                                          <small id="customerHelpId" class="form-text text-muted">Phone number is required</small>
                                        </div>
                                        <div class="form-group">
                                            <label for="fullname">Họ tên khách hàng</label>
                                            <input type="text" class="form-control" name="fullname" id="fullname" aria-describedby="fullnameHelpId" placeholder="Fullname"
                                            value="${customer.fullname }">
                                            <small id="fullnameHelpId" class="form-text text-muted">Fullname is required</small>
                                        </div>
                                        <div class="form-group">
                                          <label for="identityCard">CCCD/CMT/Hộ chiếu</label>
                                          <input type="text" class="form-control" name="identityCard" id="identityCard" aria-describedby="identityCardHelpId" placeholder="Identity card"
                                          value="${customer.identityCard }">
                                          <small id="identityCardHelpId" class="form-text text-muted">Identity card is required</small>
                                        </div>
                                        <div class="form-group form-inline">
                                          <label for="gender">Giới tính</label>
                                          <select class="form-control ml-3" name="gender" id="gender">
                                            <option value="true" ${customer.gender ? 'selected' : '' }>Nam</option>
                                            <option value="false" ${customer.gender==false ? 'selected' : '' }>Nữ</option>
                                          </select>
                                        </div> 
 
                                        
                                     </div>
                                
                                     <div class="col-4 offset-2">
                                        <div class="form-group">
                                          <label for="dateOfBirth">Ngày sinh</label>
                                          <input type="date" class="form-control" name="dateOfBirth" id="dateOfBirth" aria-describedby="Dater" placeholder="Date of birth"
                                          value="${date}">
                                        </div>
                                        <div class="form-group">
                                            <label for="address">Địa chỉ</label>
                                            <input type="text" class="form-control" name="addr" id="address" aria-describedby="addressHelpId" placeholder="Address"
                                            value="${customer.addr}">
                                          </div> 
                                          <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="text" class="form-control" name="email" id="email" aria-describedby="helpId" placeholder="Email"
                                            value="${customer.email}">
                                          </div>
                                          <div class="form-group form-inline">
                                            <label for="custoemerType">Khách hàng</label>
                                            <select class="form-control ml-3" name="customerType" id="customerType">
                                              <option value="true" ${customer.customerType ? 'selected' :''}>Doanh nghiệp</option>
                                              <option value="false" ${!customer.customerType ? 'selected' :''}>Cá nhân</option>
                                            </select>
                                          </div> 
                                     </div>
                                 </div>
                             </div>
                             <div class="row col-8 offset-4">
                                 <button class="btn btn-primary ml-3" formaction="CustomerManagementServlet/create">Tạo mới</button>
								<c:if test="${ not empty customer.fullname }">
								   <button class="btn btn-warning ml-3" formaction="CustomerManagementServlet/update">Cập nhật</button>
                                 	<button class="btn btn-danger ml-3 " formaction="CustomerManagementServlet/delete">Xóa</button>
								</c:if>
                                 <button class="btn btn-info ml-3 " formaction="CustomerManagementServlet/reset">Làm mới</button>
                           </div>
                         </div>
                     </form>
                  </div>
                  <div class="tab-pane fade ${empty showList ?'show active':'' }" id="userList" role="tabpanel" aria-labelledby="userList-tab">
					<div class="d-flex justify-content-center mt-5 mb-5">
					<form method="post" action="" class="form-inline">
                        	<div class="form-group form-inline" style="margin-top:10px">
                          <label for="id"><b>Khách hàng</b></label>
                          <input type="text" name="id" id="id" class="form-control ml-3" placeholder="" aria-describedby="helpId" value="${str }">
                          <button class="ml-3 btn btn-success" formaction="CustomerManagementServlet/search">Search</button>
                        </div>
                      </form>  
                       </div>
                  		<h3 class="text-center">Danh sách khách hàng</h3>
                      <table class="table table-bordered">
                          <tr>
                              <th>Số điện thoại</th>
                              <th>Họ và tên</th>
                              <th>CMND/CCCD</th>
                              <th>Giới tính</th>
                              <th>Ngày sinh</th>
                              <th>Địa chỉ</th>
                              <th>Email</th>
                              <th>Khách hàng</th>
                              <td>&nbsp;</th>
                          </tr>
                          <tr>
						<c:forEach var="item" items="${customers }">
							<td>${item.phoneNumber }</td>
							<td>${item.fullname }</td>
							<td>${item.identityCard }</td>
							<td>${item.gender ? 'Male' : 'Female' }</td>
							<td>${item.dateOfBirth }</td>
							<td>${item.addr}</td>
							<td>${item.email }</td>
							<td>${item.customerType ? 'Bussiness' : 'Personal' }</td>

							<td><a
								href="CustomerManagementServlet/edit?phoneNumber=${item.phoneNumber }"><i
									class="fa fa-credit-card" aria-hidden="true"></i>Edit</a> <a
								href="CustomerManagementServlet/edit?phoneNumber=${item.phoneNumber }"><i
									class="fa fa-trash" aria-hidden="true"></i> Delete</a></td>
					</tr>
                           </c:forEach>

				</table>
					<div class="d-flex justify-content-center">
					
                      <nav aria-label="...">
						<ul class="pagination">
							
							<li class="page-item disabled"><span class="page-link">Previous</span>
							</li>
							<c:forEach begin="1" end="${numberOfPage }" var = "i">
							
							<li class="page-item ${i==currentPage ?'active' :'' }"><a class="page-link" href="CustomerManagementServlet?page=${i }&id=${str}&showList=true">${i }</a></li>
							</c:forEach>	
							<li class="page-item"><a class="page-link" href="#">Next</a>
							</li>
						</ul>
					</nav>
					</div>
                  </div>
  
              </div>
            </div>
          </section>