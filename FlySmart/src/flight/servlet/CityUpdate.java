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
@WebServlet("/cityupdate")
public class CityUpdate extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8652107935290223012L;
	protected CityDao cityDao;
	
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

        // Retrieve user and validate.
        String cityid = req.getParameter("cityid");
        if (cityid == null || cityid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CityId.");
        } else {
        	try {
        		City city = cityDao.getCityById(Integer.parseInt(cityid));
        		if(city == null) {
        			messages.put("success", "CityId does not exist.");
        		}
        		req.setAttribute("city", city);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CityUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String cityid = req.getParameter("cityid");
        if (cityid == null || cityid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CityId.");
        } else {
        	try {
        		City city = cityDao.getCityById(Integer.parseInt(cityid));
        		if(city == null) {
        			messages.put("success", "CityId does not exist. No update to perform.");
        		} else {
        			String newCityName = req.getParameter("cityname");
        			if (newCityName == null || newCityName.trim().isEmpty()) 
        			{
        	            messages.put("success", "Please enter a valid CityName.");
        	        }
        			else
        	        {
        	        	String newState = req.getParameter("state");
            			if(newState == null || newState.trim().isEmpty())
            			{
            				messages.put("success", "Please enter a valid State");
            			}
            			else
            			{
            				city = cityDao.update(city, newCityName, newState);
            	        	messages.put("success", "Successfully updated " + newCityName + ", " + newState);
            			}
        	        }
        		}
        		req.setAttribute("city", city);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CityUpdate.jsp").forward(req, resp);
    }
}
