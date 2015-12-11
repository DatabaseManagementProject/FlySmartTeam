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




@WebServlet("/routedelete")
public class RouteDelete extends HttpServlet {
	protected RouteDao routeDao;
	
	@Override
	public void init() throws ServletException {
		routeDao = RouteDao.getInstance();
		}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Route");        
        req.getRequestDispatcher("/RouteDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // 
        String routeId = req.getParameter("routeid");
        if (routeId == null || routeId.trim().isEmpty()) {
            messages.put("title", "Invalid RouteId");
            messages.put("disableSubmit", "true");
        } else {
        	//
	        Route route = new Route (Integer.parseInt(routeId));
	        try {
	        	route = routeDao.delete(route);
	        	// Update the message.
		        if (route == null) {
		            messages.put("title", "Successfully deleted " + routeId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + routeId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RouteDelete.jsp").forward(req, resp);
    }
}


