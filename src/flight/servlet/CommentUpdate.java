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



@WebServlet("/commentupdate")
public class CommentUpdate extends HttpServlet{
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
        
        String commentid = req.getParameter("commentid");
        if (commentid == null || commentid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CommentId.");
        } else {
        	try {
        		Comment comment = commentDao.getCommentFromCommentId(Integer.parseInt(commentid));
        		if(comment == null) {
        			messages.put("success", "CommentId does not exist.");
        		}
        		req.setAttribute("comments", comment);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CommentUpdate.jsp").forward(req, resp);

	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String commentid = req.getParameter("commentid");
        if (commentid == null || commentid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CommentId.");
        } else {
        	try {
        		Comment comment = commentDao.getCommentFromCommentId(Integer.parseInt(commentid));
        		if(comment == null) {
        			messages.put("success", "CommentId does not exist. No update to perform.");
        		} else {
        			String newContent = req.getParameter("content");
        			if (newContent == null || newContent.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Distance.");
        	        } else {
        	        	comment = commentDao.updateContent(comment,newContent);
        	        	messages.put("success", "Successfully updated content to " + newContent);
        	        }
        		}
        		req.setAttribute("comment", comment);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CommentUpdate.jsp").forward(req, resp);
    }

}
