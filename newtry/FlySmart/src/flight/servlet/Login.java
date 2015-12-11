package flight.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flight.dal.UserDao;
import flight.model.User;

/**
 * 
 * @author XueyiGu
 *
 */

@WebServlet("/login")
public class Login extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2650268553764414512L;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = userDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        } else {
        	// get password
        	String password = req.getParameter("password");
        	if(password == null || password == "")
        	{
        		messages.put("success", "Please enter your password");
        		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        		return;
        	}
        	
	        try {
	        	// find this user from database
	        	User user = userDao.getUserFromUserName(username);
	        	if(user == null)
	        	{
	        		messages.put("success", "There is no such user, please check your information!");
	                req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	                return;
	        	}
	        	if(password.equals(user.getPassword()) || password == user.getPassword())
	        	{
	        		//LocalGlobals.username = user.getUserName();
	        		messages.put("success", "Welcome back, "+ user.getUserName());
	        		req.setAttribute("user", user);
	        		//remember this user in this session
	        		req.getSession().setAttribute("user", user);
	                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
	        	}
	        	else
	        	{
	        		messages.put("success", "UserName or Password is invalid");
	        		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	        	}
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
    }
}
