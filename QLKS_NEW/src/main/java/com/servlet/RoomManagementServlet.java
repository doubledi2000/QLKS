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
import com.dao.EmployeeDao;
import com.dao.RoomDao;
import com.dao.RoomTypeDao;
import com.model.Employee;
import com.model.Room;
import com.model.Room_type;

/**
 * Servlet implementation class RoomManagementServlet
 */
@WebServlet({"/RoomManagementServlet",
	"/RoomManagementServlet/create",
	"/RoomManagementServlet/update",
	"/RoomManagementServlet/delete",
	"/RoomManagementServlet/reset",
	"/RoomManagementServlet/edit"})
public class RoomManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		findAllRoomType(request, response);
		findSome(request, response);
		String url = request.getRequestURI();
		if(url.contains("edit")) {
			edit(request, response);
			return;
		} else if(url.contains("delete")) {
			delete(request, response);
			return;
		}
		request.setAttribute("room", new Room());
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		findAllRoomType(request, response);
		findSome(request, response);
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
		RoomDao dao = new RoomDao();
		List<Room> list = dao.findAll();
		request.setAttribute("rooms", list);
	}
	protected void findAllRoomType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomTypeDao dao = new RoomTypeDao();
		
		List<Room_type> list = dao.findAll();
		request.setAttribute("roomTypes", list);
	}
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Room room = new Room();
			BeanUtils.populate(room, request.getParameterMap());
			RoomDao dao = new RoomDao();
			dao.insert(room);
			request.setAttribute("room", room);
			request.setAttribute("message", "Room is inserted");
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Insert room failed ");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("roomCode"); 
			if(id == null) {
				request.setAttribute("error", "Mã phòng không hợp lệ.");
				PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);
				return;
			}
			RoomDao dao = new RoomDao();
			
			Room room = dao.findById(id);
			request.setAttribute("room", room);
					
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Lỗi ");
			e.printStackTrace();
		}
		findSome(request, response);	
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);

	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Room room = new Room();
			BeanUtils.populate(room, request.getParameterMap());
			RoomDao dao = new RoomDao();
			RoomTypeDao rtDao = new RoomTypeDao();
			Room_type room_type = rtDao.findById(request.getParameter("roomTypeCode").toString());
			room.setRoomType(room_type);
			dao.update(room);
			request.setAttribute("message", "Room updated");
			request.setAttribute("room", room);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Room type updated failed");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);

	}
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("roomCode");
			if(id==null) {
				request.setAttribute("error", "Room code is required");
				PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);
				return;
			}
			RoomDao dao = new RoomDao();
			Room room = dao.findById(id);
			if(room == null) {
				request.setAttribute("error", "room code does not exit");
				PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "Room type have been deleted");
			request.setAttribute("room", new Room());
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Room updated failed");
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);

	}
	
	protected void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("room", new Room());
		} catch (Exception e) {
			e.printStackTrace();
		}
		findSome(request, response);
		PageInfo.prepareAndForward(request, response, PageType.ROOM_MANAGEMENT_PAGE);

	}
	private void findSome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Room> rooms = new ArrayList<Room>();
			RoomDao dao = new RoomDao();
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
			rooms = dao.findAll(false, (page-1)*size , size);
			request.setAttribute("currentPage", page);
			request.setAttribute("rooms", rooms);
			request.setAttribute("numberOfPage", numberOfPage);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Lỗi tìm kiếm danh sách nhân viên");
			e.printStackTrace();
		}
		
	}
	
}
