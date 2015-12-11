package flight.model;


public class Route {
	protected int RouteId;
	protected String OriginAirportId;
	protected String DestinationAirportId;
	protected int Distance;
	

	
	public Route(int routeid, String origin, String destination, int distance)
	{
		this.RouteId = routeid;
		this.OriginAirportId = origin;
		this.DestinationAirportId = destination;
		this.Distance = distance;
	}
	
	public Route(int routeid) {
		this.RouteId = routeid;
	}
	
	public Route(String origin, String destination, int distance)
	{
		this.OriginAirportId = origin;
		this.DestinationAirportId = destination;
		this.Distance = distance;
	}

	/** Getters and setters. */
	
	public int getRouteId() {
		return RouteId;
	}

	public void setRouteId (int routeid) {
		this.RouteId = routeid;
	}
	
	
	public String getOriginAirportId() {
		return OriginAirportId;
	}

	public void setOriginAirportId(String origin) {
		this.OriginAirportId = origin;
	}
	
	
	public String getDestinationAirportId() {
		return DestinationAirportId;
	}

	public void setDestinationAirportId(String dest) {
		this.DestinationAirportId = dest;
	}
	
	public int getDistance() {
		return Distance;
	}

	public void setDistance (int distance) {
		this.Distance = distance;
	}
	
	

}
