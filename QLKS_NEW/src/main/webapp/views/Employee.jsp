
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<section class="row">
            <div class="col mt-4">
              <ul class="row nav nav-tabs" id="myTab" role="tablist">
                  <li class="nav-item" role="presentation">
                      <a class="nav-link ${empty showList ?'active':'' }" id="employeeEditing-tab" data-toggle="tab" href="#employeeEditing" role="tab"
                          aria-controls="employeeEditing" aria-selected="true">Thông tin nhân viên</a>
                  </li>
                  <li class="nav-item" role="presentation">
                      <a class="nav-link ${empty showList ?'':'active' }" id="employeeList-tab" data-toggle="tab" href="#employeeList" role="tab"
                          aria-controls="employeeList" aria-selected="false">Danh sách nhân viên</a>
                  </li>
   
              </ul>
             
              <div class="col tab-content" id="myTabContent">
                  <div class="tab-pane fade ${empty showList ?'show active':'' }" id="employeeEditing" role="tabpanel"
                      aria-labelledby="employeeEditing-tab">
                  <form action="#" method="post">
                    <div class="card">
                                   	<div class="card-header" style="text-align: center">
                         		<b style="font-size: 40px">Quản lý nhân viên</b>
                         	</div>
                       <div class="card-body">
                           <div class="row">
                               <div class="col-4 offset-1">
                                   <div class="form-group">
                                     <label for="employeeCode">Mã nhân viên</label>
                                     <input type="text" class="form-control" name="employeeCode" id="employeeCode" aria-describedby="employeeCodeHelpId" placeholder="employee code"
                                     value="${employee.employeeCode }" ${employee.employeeCode != null ? 'readonly' :'' } >
                                     <small id="employeeCodeHelpId" class="form-text text-muted">Mã nhân viên không quá 5 kí tự</small>
                                   </div>
                                   <div class="form-group">
                                     <label for="fullname">Fullname</label>
                                     <input type="text" class="form-control" name="fullname" id="fullname" aria-describedby="fullnameHelpId" placeholder="fullname"
                                     value="${employee.fullname }" }>
                                     <small id="fullnameHelpId" class="form-text text-muted">Họ tên nhân viên không được để trống</small>
                                   </div>
                                   <div class="form-group">
                                     <label for="identityCard">CMND/CCCD</label>
                                     <input type="text" class="form-control" name="identityCard" id="identityCard" aria-describedby="identityCardHelpId" placeholder="Identity card"
                                     value="${employee.identityCard }">
                                     <small id="identityCardHelpId" class="form-text text-muted">CMND/CCCD không được để trống</small>
                                   </div>
                                   <div class="form-group form-inline">
                                     <label for="gender" class="mr-3">Giới tính</label>
                                     <select class="form-control" name="gender" id="gender">
                                       <option value = "true" ${employee.gender ? 'selected' :'' }>Nam</option>
                                       <option value = "false" ${!employee.gender ? 'selected' :'' }>Nữ</option>
                                     </select>
                                   </div>
                                   <div class="form-group">
                                     <label for="phoneNumber">Số điện thoại</label>
                                     <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" aria-describedby="phoneNumberHelpId" placeholder="Phone number"
                                     value="${employee.phoneNumber }" }>
                                     <small id="phoneNumberHelpId" class="form-text text-muted">Số điện thoại không được để trống</small>
                                   </div>

                               </div>
                               <div class="col-4 offset-1">
                                   <div class="form-group">
                                     <label for="dateOfBirth" >Ngày sinh</label>
                                     <input type="date"
                                       class="form-control" name="dateOfBirth" id="dateOfBirth" aria-describedby="helpId" placeholder="Date of birth"
                                       value="${dateOfBirth	 }">
                                   </div>
                                   <div class="form-group">
                                     <label for="addr">Address</label>
                                     <input type="text" class="form-control" name="addr" id="addr" aria-describedby="addressHelpId" placeholder="address"
                                     value="${employee.addr }">
                                   </div>
                                   <div class="form-group form-inline">
                                     <label for="role" class="mr-3">Vai trò</label>
                                     <select class="form-control" name="role" id="role" >
                                       <option value = "true" ${employee.role ? 'selected' :'' }>Quản lý</option>
                                       <option value = "false"  ${employee.role ?'' : 'selected' } >Nhân viên</option>
                                     </select>
                                   </div>
                                   <div class="form-group">
                                     <label for="username">tên tài khoản </label>
                                     <input type="text" class="form-control" name="account" id="username" aria-describedby="usernameHelpId" placeholder="Username"
                        			value="${employee.account }">
                                     <small id="usernameHelpId" class="form-text text-muted">Tài khoản không được để trống</small>
                                   </div>
                                   <div class="form-group">
                                     <label for="password">Mật khẩu</label>
                                     <input type="password"
                                       class="form-control" name="password" id="password" aria-describedby="passwordHelpId" placeholder="Password"
                                       value="${employee.password }">
                                     <small id="passwordHelpId" class="form-text text-muted">Mật khẩu không được để trống</small>
                                   </div>
                               </div>
                               <div class="row offset-2">
                                <button class="btn btn-primary ml-3" formaction="EmployeeManagementServlet/create">Create</button>
                                <c:if test="${not empty employee.employeeCode }">
                                <button class="btn btn-warning ml-3" formaction="EmployeeManagementServlet/update">Update</button>
                                <button class="btn btn-danger ml-3" formaction="EmployeeManagementServlet/delete">Delete</button>
                                </c:if>
                                <button class="btn btn-info ml-3" formaction="EmployeeManagementServlet/reset">Reset</button>
                            </div>
                           </div>
                        </div>
                    </div>
                  </form>
                  </div>
                  <div class="tab-pane fade ${empty showList ? '' :'show active' }" id="employeeList" role="tabpanel" aria-labelledby="employeeList-tab">
                   <h3 class="text-center">Danh sách nhân viên</h3>
                     <table class="table table-bordered">
                         <tr>
                             <th>Mã nhân viên</th>
                             <th>Họ tên</th>
                             <th>CMND</th>
                             <th>SDT</th>
                             <th>Địa chỉ</th>
                             <th>Ngày sinh</th>
                             <th>Giới tính</th>
                             <th>Chức vụ</th>
                             <th>Tài khoản</th>
                             <th>&nbsp;</th>
                         </tr>
                         <c:if test="${employees != null }">
                         	<c:forEach var="item" items="${employees }">
                         	<tr>
                             <td>${item.employeeCode }</td>
                             <td>${item.fullname }</td>
                             <td>${item.identityCard }</td>
                             <td>${item.phoneNumber }</td>
                             <td>${item.addr }</td>
                             <td>${item.dateOfBirth }</td>
                             <td>${item.gender == true ? 'Nam' : 'Nữ'}</td>
                             <td>${item.role == true ? 'admin' : 'user' }</td>
                             <td>${item.account }</td>
                             <td>
                                <a href="EmployeeManagementServlet/edit?employeeCode=${item.employeeCode }"><i class="fa fa-credit-card" aria-hidden="true"></i>Edit</a>
                                <a href="EmployeeManagementServlet/delete?employeeCode=${item.employeeCode }"><i class="fa fa-trash" aria-hidden="true"></i> Delete</a>
                             </td>
                         </tr>
                         	</c:forEach>
                         </c:if>
                         
                     </table>
                     <div class="d-flex justify-content-center">
					
                      <nav aria-label="...">
						<ul class="pagination">
						
							<c:forEach begin="1" end="${numberOfPage }" var = "i">
							
							<li class="page-item ${i==currentPage ?'active' :'' }"><a class="page-link" href="EmployeeManagementServlet?page=${i }&showList=true">${i }</a></li>
							</c:forEach>	
						
						</ul>
					</nav>
					</div>
                  </div>
  
              </div>
            </div>
          </section>