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

import flight.dal.CityDao;
import flight.model.City;

/**
 * 
 * @author XueyiGu
 *
 */
@WebServlet("/citydelete")
public class CityDelete extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1751467501389205796L;
	protected CityDao cityDao;
	private City city;
	
	@Override
	public void init() throws ServletException {
		cityDao = CityDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Provide a title and render the JSP.
        messages.put("title", "Delete City"); 
        req.getRequestDispatcher("/CityDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String cityid = req.getParameter("cityid");
        if (cityid == null || cityid.trim().isEmpty()) {
            messages.put("title", "Invalid CityId");
            messages.put("disableSubmit", "true");
        } else {
        	
	        try {
	        	city = cityDao.getCityById(Integer.parseInt(cityid));
	        	city = cityDao.delete(city);
	        	// Update the message.
		        if (city == null) {
		            messages.put("title", "Successfully deleted " + cityid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + cityid );
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CityDelete.jsp").forward(req, resp);
    }
}
