package servlet;

import dal.*;
import model.*;

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


@WebServlet("/findcarriercomment")
public class FindCarrierComment extends HttpServlet{

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

        List<Comment> commentlist = new ArrayList<Comment>();
        
        // Retrieve and validate name.
        // carrier is retrieved from the URL query string.
        String carrier = req.getParameter("carrier");
        if (carrier == null || carrier.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Carrier.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	commentlist = commentDao.getCommentFromCarrierId(carrier);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + carrier);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previous Carrier", carrier);
        }
        
 
        req.setAttribute("Comments", commentlist);
        
        req.getRequestDispatcher("/FindCarrierComment.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        List<Comment> commentlist = new ArrayList<Comment>();
        
        // Retrieve and validate name.
        // carrier is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String carrier = req.getParameter("carrier");
        if (carrier == null || carrier.trim().isEmpty()) {
            messages.put("success", "Please enter a valid carrier.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	commentlist = commentDao.getCommentFromCarrierId(carrier);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + carrier);
        }
        req.setAttribute("Comments", commentlist);
        
        req.getRequestDispatcher("/FindCarrierComment.jsp").forward(req, resp);
    }
}

