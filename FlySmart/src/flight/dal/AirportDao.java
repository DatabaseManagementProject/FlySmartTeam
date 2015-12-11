package flight.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import flight.model.Airport;
/**
 * 
 * @author XueyiGu
 *
 */
public class AirportDao 

gii

{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AirportDao instance = null;
	protected AirportDao() {
		connectionManager = new ConnectionManager();
	}
	public static AirportDao getInstance() {
		if(instance == null) {
			instance = new AirportDao();
		}
		return instance;
	}
	/**
	 * insert a new record into the table Airport
	 * @param airport
	 * @return
	 * @throws SQLException
	 */
	public Airport create(Airport airport) throws SQLException
	{
		String insert = "INSERT INTO Airport(AirportName, Description, "
				+ " Address, CreatedTime, Active) VALUES(?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, airport.getAirportName());
			insertStmt.setString(2, airport.getDescription());
			insertStmt.setString(3, airport.getAddress());
			insertStmt.setTimestamp(4, new Timestamp(airport.getCreatedTime().getTime()));
			insertStmt.setBoolean(5, airport.isActive());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int airportId = -1;
			if(resultKey.next()) 
			{
				airportId = resultKey.getInt(1);
			} 
			else 
			{
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			airport.setAirportId(airportId);
			return airport;
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
	/**
	 * get airport by airport id
	 * @param airportId
	 * @return
	 * @throws SQLException
	 */
	public Airport getAirportById(int airportId) throws SQLException
	{
		String select = " select * from Airport where airportId =? ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, airportId);
			results = selectStmt.executeQuery();
			if(results.next()) 
			{
				String aiportName = results.getString("AirportName");
				String description = results.getString("Description");
				String address = results.getString("Address");
				Date createdTime = new Date();
				boolean active = results.getBoolean("Active");
				
				Airport airpoort = new Airport(airportId, aiportName, description, address, createdTime, active);
				return airpoort;
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
	/**
	 * get airport by airport name
	 * @param airportId
	 * @return
	 * @throws SQLException
	 */
	public Airport getAirportByName(String airportName) throws SQLException
	{
		String select = " select * from Airport where AirportName =? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, airportName);
			results = selectStmt.executeQuery();
			if(results.next()) 
			{
				int airportId = results.getInt("AirportId");
				String description = results.getString("Description");
				String address = results.getString("Address");
				Date createdTime =  new Date();
				boolean active = results.getBoolean("Active");
				
				Airport airpoort = new Airport(airportId, airportName, description, address, createdTime, active);
				return airpoort;
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
	/**
	 * delete a record from Airport by airport id
	 * @param airport
	 * @return
	 * @throws SQLException
	 */
	public Airport delete(Airport airport) throws SQLException
	{
		String delete = " delete from Airprot where AirportId = ?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, airport.getAirportId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Airport instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) 
			{
				connection.close();
			}
			if(deleteStmt != null) 
			{
				deleteStmt.close();
			}
		}
	}
	/**
	 * update airport name
	 * @param airport
	 * @param airportName
	 * @return
	 * @throws SQLException
	 */
	public Airport update(Airport airport, String airportName) throws SQLException
	{
		String update = " update Airport set AirportName =? where AirportId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setString(1, airportName);
			updateStmt.setInt(2, airport.getAirportId());
			updateStmt.executeUpdate();

			airport.setAirportName(airportName);
			return airport;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) 
			{
				connection.close();
			}
			if(updateStmt != null) 
			{
				updateStmt.close();
			}
		}
	}


}
