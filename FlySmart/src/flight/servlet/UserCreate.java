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




@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String userName = req.getParameter("username");
	        if (userName == null || userName.trim().isEmpty()) {
	            messages.put("success", "Invalid UserId");
	        } else {
	        	// Create the BlogUser.
	        	String firstname = req.getParameter("firstname");
	        	String lastname = req.getParameter("lastname");
	        	String password = req.getParameter("password");
	        	String phone = req.getParameter("phone");
	        	String email = req.getParameter("email");
	        	

		        try {
		        	// Exercise: parse the input for StatusLevel.
		        	User user = new User(userName, firstname, lastname, password, email, phone);
		        	user = userDao.create(user);
		        	messages.put("success", "Successfully created " + userName);
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
}