package flight.model;

import java.util.Date;
/**
 * 
 * @author XueyiGu
 *
 */
public class Airport 
{
	 int AirportId;
	 String AirportName;
	 String Description;
	 String Address;
	 Date CreatedTime;
	 boolean Active;
	 
	public Airport(int airportId, String airportName, String description, String address, Date createdTime,
			boolean active) {
		super();
		AirportId = airportId;
		AirportName = airportName;
		Description = description;
		Address = address;
		CreatedTime = createdTime;
		Active = active;
	}
	
	public Airport(String airportName, String description, String address, Date createdTime, boolean active) {
		super();
		AirportName = airportName;
		Description = description;
		Address = address;
		CreatedTime = createdTime;
		Active = active;
	}



	public int getAirportId() {
		return AirportId;
	}
	public void setAirportId(int airportId) {
		AirportId = airportId;
	}
	public String getAirportName() {
		return AirportName;
	}
	public void setAirportName(String airportName) {
		AirportName = airportName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Date getCreatedTime() {
		return CreatedTime;
	}
	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	 
	 

}
