package flight.model;
/**
 * 
 * @author XueyiGu
 *
 */
public class City 
{
	int CityId;
	String CityName;
	String State;
	
	public City(int cityId, String cityName, String state) {
		super();
		CityId = cityId;
		CityName = cityName;
		State = state;
	}
	
	public City(String cityName, String state) {
		super();
		CityName = cityName;
		State = state;
	}

	public City(int cityId) {
		super();
		CityId = cityId;
	}

	public int getCityId() {
		return CityId;
	}
	public void setCityId(int cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
}
