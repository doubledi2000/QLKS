package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.common.PageInfo;
import com.common.PageType;
import com.dao.CustomerDao;
import com.dao.RoomDao;
import com.dao.RoomTypeDao;
import com.model.Room;
import com.model.Room_type;

/**
 * Servlet implementation class RoomTypeManagementServlet
 */
@WebServlet({"/RoomTypeManagementServlet",
	"/RoomTypeManagementServlet/create",
	"/RoomTypeManagementServlet/update",
	"/RoomTypeManagementServlet/delete",
	"/RoomTypeManagementServlet/reset",
	"/RoomTypeManagementServlet/edit"})
public class RoomTypeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if(url.contains("edit")) {
			edit(request, response);
			return;
		} else if(url.contains("delete")) {
			delete(request, response);
			return;
		}
		findSome(request, response);
		request.setAttribute("roomType", new Room_type());
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		if(url.contains("create")) {
			create(request, response);
			return;
		} else if(url.contains("edit")) {
			edit(request, response);
			return;
		}else if(url.contains("update")) {
			update(request, response);
			return;
		}else if(url.contains("delete")) {
			delete(request, response);
			return;
		}else if(url.contains("reset")) {
			reset(request, response);
			return;
		}

	}
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			RoomTypeDao dao = new RoomTypeDao();
			List<Room_type> list = dao.findAll();
			request.setAttribute("roomTypes", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Room_type roomType = new Room_type();
			BeanUtils.populate(roomType, request.getParameterMap());
			RoomTypeDao dao = new RoomTypeDao();
			if(roomType.getRoomTypeCode()== null || roomType.getRoomTypeCode().equals("")) {
				throw new Exception();
			}
			dao.insert(roomType);
			request.setAttribute("roomType", roomType);
			request.setAttribute("message", "Room type is inserted");
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Insert room type failed ");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("roomTypeCode"); 
			if(id == null) {
				request.setAttribute("error", "Mã loại phòng không hợp lệ.");
				throw new Exception();
			}
			RoomTypeDao dao = new RoomTypeDao();
			
			Room_type roomType = dao.findById(id);
			request.setAttribute("roomType", roomType);
					
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Lỗi");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);

	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Room_type roomType = new Room_type();
			BeanUtils.populate(roomType, request.getParameterMap());
			RoomTypeDao dao = new RoomTypeDao();
			dao.update(roomType);
			request.setAttribute("message", "Room type updated");
			request.setAttribute("roomType", roomType);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Room type updated failed");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);

	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("roomTypeCode");
			if(id==null) {
				request.setAttribute("error", "Room type code is required");
				PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);
				return;
			}
			RoomTypeDao dao = new RoomTypeDao();
			Room_type roomType = dao.findById(id);
			if(roomType == null) {
				request.setAttribute("error", "room type code does not exit");
				PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "Room type have been deleted");
			request.setAttribute("roomType", new Room_type());
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Room type updated failed");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);

	}
	
	protected void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("roomType", new Room_type());
		} catch (Exception e) {
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_TYPE_MANAGEMENT_PAGE);

	}
	private void findSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Room_type> roomTypes = new ArrayList<Room_type>();
			RoomTypeDao dao = new RoomTypeDao();
			int numberOfRoom = dao.count().intValue();
			int size = 2;
			int numberOfPage = numberOfRoom/size;
			if(numberOfRoom % size != 0) numberOfPage++;
			String pageStr = request.getParameter("page");
			int page;
			if(pageStr == null || pageStr.equals("")) {
				page = 1;
			}else page = Integer.parseInt(pageStr);
			String showList = request.getParameter("showList");
			request.setAttribute("showList", showList);
			roomTypes = dao.findAll(false, (page-1)*size , size);
			request.setAttribute("currentPage", page);
			request.setAttribute("roomTypes", roomTypes);
			request.setAttribute("numberOfPage", numberOfPage);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Lỗi tìm kiếm danh sách nhân viên");
			e.printStackTrace();
		}
		
	}
	
	
}
