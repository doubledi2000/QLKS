package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.PageInfo;
import com.common.PageType;
import com.dao.RoomDao;
import com.domain.CurrentRoomStt;

/**
 * Servlet implementation class Home
 */
@WebServlet({"/Home"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoomDao dao = new RoomDao();
		try {
			List<CurrentRoomStt> currentRoomStt = dao.findCurrentRoomStt(new Date());
			Date now = new Date();
			currentRoomStt = dao.findCurrentRoomStt(now);
			request.setAttribute("currentRoomStts", currentRoomStt);
			PageInfo.prepareAndForward(request, response, PageType.HOME_PAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
