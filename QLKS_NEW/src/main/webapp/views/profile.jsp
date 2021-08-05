<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <section class="container">
      <h1>Thông tin cá nhân</h1>
      <form action="#" method="post">
        <div class="card">
           <div class="card-body">
               <div class="row">
                   <div class="col-4 offset-1">
                       <div class="form-group">
                         <label for="employeeCode">Employee code</label>
                         <input type="text" class="form-control" name="employeeCode" id="employeeCode" aria-describedby="employeeCodeHelpId" placeholder="employee code"
                         value="${employeeLogin.employeeCode }"  >
                          </div>
                       <div class="form-group">
                         <label for="fullname">Fullname</label>
                         <input type="text" class="form-control" name="fullname" id="fullname" aria-describedby="fullnameHelpId" placeholder="fullname"
                         value="${employeeLogin.fullname }">
                         
                       </div>
                       <div class="form-group">
                         <label for="identityCard">Identity card</label>
                         <input type="text" class="form-control" name="identityCard" id="identityCard" aria-describedby="identityCardHelpId" placeholder="Identity card"
                         value="${employeeLogin.identityCard }">
                       
                       </div>
                       <div class="form-group form-inline">
                         <label for="gender" class="mr-3">Gender</label>
                         <select class="form-control" name="gender" id="gender">
                           <option value = "true" ${employeeLogin.gender ? 'selected' :'' }>Male</option>
                           <option value = "false" ${employeeLogin.gender ? '' : 'selected' }>Female</option>
                         </select>
                       </div>
                       <div class="form-group">
                         <label for="phoneNumber">Phone number</label>
                         <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" aria-describedby="phoneNumberHelpId" placeholder="Phone number"
                         value="${employeeLogin.phoneNumber }" }>
                        </div>

                   </div>
                   <div class="col-4 offset-1">
                       <div class="form-group">
                         <label for="dateOfBirth">Date of birth</label>
                         <input type="date"
                           class="form-control" name="dateOfBirth" id="dateOfBirth" aria-describedby="helpId" placeholder="Date of birth"
                           value="${dateOfBirth	 }">
                       </div>
                       <div class="form-group">
                         <label for="address">Address</label>
                         <input type="text" class="form-control" name="address" id="address" aria-describedby="addressHelpId" placeholder="address"
                         value="${employeeLogin.addr }" >
                       </div>
                       <div class="form-group">
                         <label for="username">Username </label>
                         <input type="text" class="form-control" name="username" id="username" aria-describedby="usernameHelpId" placeholder="Username"
                         value="${employeeLogin.account }">
                         <small id="usernameHelpId" class="form-text text-muted">Username is required</small>
                       </div>
                       <div class="form-group">
                         <label for="password">Password</label>
                         <input type="password"
                           class="form-control" name="password" id="password" aria-describedby="passwordHelpId" placeholder="Password"
                           value = "${employeeLogin.password }">
                         <small id="passwordHelpId" class="form-text text-muted">Password is required</small>
                       </div>
                   </div>
                   <div class="row offset-2">
                    <button class="btn btn-warning ml-3" formaction="ProfileServlet/update">Cập nhật</button>
                    <button class="btn btn-info ml-3" formaction="LogoffServlet">Đăng xuất</button>
                </div>
               </div>
            </div>
        </div>
      </form>
    </section>