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

@WebServlet("/citycreate")
public class CityCreate extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7628363846006971191L;
	protected CityDao cityDao;
	
	@Override
	public void init() throws ServletException {
		cityDao = cityDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CityCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String cityname = req.getParameter("cityname");
        if (cityname == null || cityname.trim().isEmpty()) {
            messages.put("success", "Invalid CityName");
        } else {
        	// Create the BlogUser.
        	String state = req.getParameter("state");
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	City city = new City(cityname, state);
	        	city = cityDao.create(city);
	        	messages.put("success", "Successfully created " + city.getCityName() + ", " + state);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/CityCreate.jsp").forward(req, resp);
    }
}
