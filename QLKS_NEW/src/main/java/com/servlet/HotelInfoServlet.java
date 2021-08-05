package com.servlet;

import java.beans.Beans;
import java.io.IOException;		
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import com.common.PageInfo;
import com.common.PageType;
import com.dao.HotelInfoDao;
import com.model.HotelInfo;

@WebServlet("/HotelInfoServlet")
public class HotelInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HotelInfoDao dao = new HotelInfoDao();
		HotelInfo hotel = dao.find();
		request.setAttribute("hotel", hotel);
		PageInfo.prepareAndForward(request, response, PageType.HOTELINFO_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HotelInfo hotel = new HotelInfo();
		HotelInfoDao dao = new HotelInfoDao();
		try {
			BeanUtils.populate(hotel, request.getParameterMap());
			dao.update(hotel);
			request.setAttribute("message", "Cập nhật thông tin khách sạn thành công");
			request.setAttribute("hotel", hotel);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageInfo.prepareAndForward(request, response, PageType.HOTELINFO_PAGE);
	}

}

