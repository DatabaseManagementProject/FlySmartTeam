package flight.model;
/**
 * 
 * @author XueyiGu
 *
 */
public class CityAirportRelation 
{
	int RelationId;
	City city;
	Airport airport;
	
	public CityAirportRelation(int relationId, City city, Airport airport) {
		super();
		RelationId = relationId;
		this.city = city;
		this.airport = airport;
	}
	
	
	public CityAirportRelation(City city, Airport airport) {
		super();
		this.city = city;
		this.airport = airport;
	}


	public int getRelationId() {
		return RelationId;
	}
	public void setRelationId(int relationId) {
		RelationId = relationId;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Airport getAirport() {
		return airport;
	}
	public void setAirport(Airport airport) {
		this.airport = airport;
	}
	
	

}
