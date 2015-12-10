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




@WebServlet("/commentcreate")
public class CommentCreate extends HttpServlet {
	protected CommentDao commentDao;
	
	@Override
	public void init() throws ServletException {
		commentDao = CommentDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        // Retrieve and validate name.
	        String commentId = req.getParameter("commentid");
	        if (commentId == null || commentId.trim().isEmpty()) {
	            messages.put("success", "Invalid CommentId");
	        } else {
	        	String content = req.getParameter("content");
	        	String carrierid = req.getParameter("carrierid");
	        	String username = req.getParameter("username");
	        	String rating = req.getParameter("rating");

		        try {
		        	// Exercise: parse the input for StatusLevel.
		        	Comment comment = new Comment(Integer.parseInt(commentId), content, carrierid, username,
		        			Integer.parseInt(rating));
		        	comment = commentDao.create(comment);
		        	messages.put("success", "Successfully created " + commentId);
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/CommentCreate.jsp").forward(req, resp);
	}
}