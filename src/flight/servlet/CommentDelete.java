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




@WebServlet("/commentdelete")
public class CommentDelete extends HttpServlet {
	protected CommentDao commentDao;
	
	@Override
	public void init() throws ServletException {
		commentDao = CommentDao.getInstance();
		}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Comment");        
        req.getRequestDispatcher("/CommentDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // 
        String commentId = req.getParameter("commentid");
        if (commentId == null || commentId.trim().isEmpty()) {
            messages.put("title", "Invalid CommentId");
            messages.put("disableSubmit", "true");
        } else {
        	//
	        Comment comment = new Comment (Integer.parseInt(commentId));
	        try {
	        	comment = commentDao.delete(comment);
	        	// Update the message.
		        if (comment == null) {
		            messages.put("title", "Successfully deleted " + commentId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + commentId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CommentDelete.jsp").forward(req, resp);
    }
}


