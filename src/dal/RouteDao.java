package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class RouteDao {

	protected ConnectionManager connectionManager;
	private static RouteDao instance = null;
	protected RouteDao() {
		connectionManager = new ConnectionManager();
	}
	public static RouteDao getInstance() {
		if(instance == null) {
			instance = new RouteDao();
		}
		return instance;
	}
	
	public Route create(Route route) throws SQLException {
		String insertRoute = "INSERT INTO Route (RouteId, OriginAirportId ,DestinationAirportId, Distance)"
							+ " VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRoute);
			insertStmt.setInt (1, route.getRouteId());
			insertStmt.setString(2, route.getOriginAirportId());
			insertStmt.setString(3, route.getDestinationAirportId());
			insertStmt.setInt(4, route.getDistance());
			insertStmt.executeUpdate();
			return route;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Route updateDistance(Route route, int newDistance) throws SQLException {
		String updateRoute = "UPDATE Route SET Distance=? WHERE RouteId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRoute);
			updateStmt.setInt(1, newDistance);
			updateStmt.setInt(2, route.getRouteId());
			updateStmt.executeUpdate();
			
			route.setDistance(newDistance);
			return route;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Route getRouteFromRouteId(int routeid) throws SQLException {
		String selectRoute = "SELECT RouteId,OriginAirportId,DestinationAirportId, Distance"
						   + " FROM Route WHERE RouteId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRoute);
			selectStmt.setInt(1, routeid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultRouteId = results.getInt("RouteId");
				String origin = results.getString("OriginAirportId");
				String dest = results.getString("DestinationAirportId");
				int distance = results.getInt("Distance");
				Route route = new Route(resultRouteId, origin, dest, distance);
				return route;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public Route delete(Route route) throws SQLException {
		String deleteRoute = "DELETE FROM Route WHERE RouteId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRoute);
			deleteStmt.setInt(1, route.getRouteId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
}
