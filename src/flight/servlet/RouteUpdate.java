package flight.servlet;

import java.io.IOException;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flight.dal.*;
import flight.model.*;



@WebServlet("/routeupdate")
public class RouteUpdate extends HttpServlet{
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
        
        String routeid = req.getParameter("routeid");
        if (routeid == null || routeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RouteId.");
        } else {
        	try {
        		Route route = routeDao.getRouteFromRouteId(Integer.parseInt(routeid));
        		if(route == null) {
        			messages.put("success", "RouteId does not exist.");
        		}
        		req.setAttribute("route", route);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RouteUpdate.jsp").forward(req, resp);

	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String routeid = req.getParameter("routeid");
        if (routeid == null || routeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RouteId.");
        } else {
        	try {
        		Route route = routeDao.getRouteFromRouteId(Integer.parseInt(routeid));
        		if(route == null) {
        			messages.put("success", "RouteId does not exist. No update to perform.");
        		} else {
        			String newDistance = req.getParameter("distance");
        			if (newDistance == null || newDistance.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Distance.");
        	        } else {
        	        	route = routeDao.updateDistance(route, Integer.parseInt(newDistance));
        	        	messages.put("success", "Successfully updated distance to " + newDistance);
        	        }
        		}
        		req.setAttribute("route", route);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RouteUpdate.jsp").forward(req, resp);
    }

}
