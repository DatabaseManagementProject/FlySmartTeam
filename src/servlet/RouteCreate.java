package servlet;

import dal.*;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/routecreate")
public class RouteCreate extends HttpServlet {
	protected RouteDao routeDao;
	
	@Override
	public void init() throws ServletException {
		routeDao = RouteDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/RouteCreate.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String routeId = req.getParameter("routeid");
	        if (routeId == null || routeId.trim().isEmpty()) {
	            messages.put("success", "Invalid RouteId");
	        } else {
	        	// Create the BlogUser.
	        	String origin = req.getParameter("origin");
	        	String destination = req.getParameter("destination");
	        	String distance = req.getParameter("distance");

		        try {
		        	// Exercise: parse the input for StatusLevel.
		        	Route route = new Route(Integer.parseInt(routeId), origin, destination, Integer.parseInt(distance));
		        	route = routeDao.create(route);
		        	messages.put("success", "Successfully created " + routeId);
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/RouteCreate.jsp").forward(req, resp);
	}
}