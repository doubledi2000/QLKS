   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="row d-flex justify-content-center" style="display: flex">
	<c:forEach var="item"  items="${currentRoomStts}">
	<c:choose>
		<c:when test="${item.room_stt == 1 }">
				<div class="col-2" style="height:150px; background-color: #faf99f; border: 2px solid black; margin: 3px;" style="flex-wrap: wrap" >
                <div class="text-center">Loại phòng: ${item.room_type_name }</div>
                <div class="text-center mt-2"><b>Phòng: ${item.room_number }</b></div>
                <div class="text-center mt-1">Khách hàng:  ${item.fullname != null ?item.fullname :'Trong' }</div>
                <div class="text-center">${item.timeActive != null ? item.timeActive :'' }</div>
           </div>
		</c:when>
		
		<c:when test="${item.room_stt == 2 }">
				<div class="col-2" style="height:150px; background-color: #f5ab9d; border: 2px solid black; margin: 3px;" style="flex-wrap: wrap" >
                	<div class="text-center">Loại phòng: ${item.room_type_name }</div>
                	<div class="text-center mt-2"><b>Phòng: ${item.room_number }</b></div>
                	<div class="text-center mt-1">Khách hàng:  ${item.fullname != null ?item.fullname :'Trong' }</div>
            	    <div class="text-center">${item.timeActive != null ? item.timeActive :'' }</div>
        	   </div>
           </c:when>
           <c:when test="${item.room_stt ==0 }">
           		<div class="col-2" style="height:150px; background-color: #41ea8c; border: 2px solid black; margin: 3px;" style="flex-wrap: wrap" >
                	<div class="text-center">Loại phòng: ${item.room_type_name }</div>
                	<div class="text-center mt-2"><b>Phòng: ${item.room_number }</b></div>
                	<div class="text-center mt-1">Trống</div>
       	    	</div>
			</c:when>
	 
    </c:choose>
	</c:forEach>
</section>