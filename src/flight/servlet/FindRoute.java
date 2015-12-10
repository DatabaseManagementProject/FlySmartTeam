package flight.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flight.dal.*;
import flight.model.*;


@WebServlet("/findroute")
public class FindRoute extends HttpServlet{

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

        Route route = null;
        List<Route> routelist = new ArrayList<Route>();
        
        // Retrieve and validate name.
        // routeid is retrieved from the URL query string.
        String routeid = req.getParameter("routeid");
        if (routeid == null || routeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RouteId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	route = routeDao.getRouteFromRouteId(Integer.parseInt(routeid));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + routeid);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previous RouteId", routeid);
        }
        
        routelist.add(route);
        req.setAttribute("Routes", routelist);
        
        req.getRequestDispatcher("/FindRoute.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Route route = null;

        List<Route> routelist = new ArrayList<Route>();
        
        // Retrieve and validate name.
        // routeid is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String routeid = req.getParameter("routeid");
        if (routeid == null || routeid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid routeid.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	route = routeDao.getRouteFromRouteId(Integer.parseInt(routeid));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + routeid);
        }
        if (route != null){
        routelist.add(route);
        }
        req.setAttribute("Routes", routelist);
        
        req.getRequestDispatcher("/FindRoute.jsp").forward(req, resp);
    }
}
