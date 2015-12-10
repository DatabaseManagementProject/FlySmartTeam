package flight.tool;

import java.sql.SQLException;
import java.util.List;

import flight.dal.AirportDao;
import flight.dal.CityAirportRelationDao;
import flight.dal.CityDao;
import flight.model.Airport;
import flight.model.City;
import flight.model.CityAirportRelation;
/**
 * 
 * @author XueyiGu
 *
 */
public class Inserter 
{
	public static void main(String[] args) throws SQLException
	{
		CityDao cityDao = CityDao.getInstance();
		AirportDao airportDao = AirportDao.getInstance();
		CityAirportRelationDao relaitonDao = CityAirportRelationDao.getInstance();
		
		City city = new City("Seattle", "WA");
		city = cityDao.create(city);
		
		Airport airport = airportDao.getAirportByName("ABE");
		
		CityAirportRelation relation = new CityAirportRelation(city, airport);
		relation = relaitonDao.create(relation);
		
		//read
		List<City> citys = cityDao.getCityByName("Seattle");
		for(City c : citys) {
			System.out.format("Looping cities: u:%s f:%s l:%s \n",
				c.getCityId(), c.getCityName(), c.getState());
		}
		
		System.out.format("Reading airport: u:%s f:%s l:%s \n",
				airport.getAirportId(), airport.getAirportName(), airport.getDescription());
		
		List<CityAirportRelation> relations1 = relaitonDao.getRelationByAirport(airport.getAirportId());
		for(CityAirportRelation r : relations1)
		{
			System.out.format("Looping relations: u:%s f:%s l:%s \n",
					r.getRelationId(), r.getCity().getCityName(), r.getAirport().getAirportName());
		}
		List<CityAirportRelation> relations2 = relaitonDao.getRelationByCity(city.getCityId());
		for(CityAirportRelation r : relations2)
		{
			System.out.format("Looping realtions: u:%s f:%s l:%s \n",
					r.getRelationId(), r.getCity().getCityName(), r.getAirport().getAirportName());
		}
	}

}
