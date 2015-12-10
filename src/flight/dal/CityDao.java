package flight.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flight.model.City;

/**
 * 
 * @author XueyiGu
 *
 */
public class CityDao 
{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CityDao instance = null;
	protected CityDao() {
		connectionManager = new ConnectionManager();
	}
	public static CityDao getInstance() {
		if(instance == null) {
			instance = new CityDao();
		}
		return instance;
	}
	
	public City create(City city) throws SQLException
	{
		String insert = " insert into City(CityName, State) values(?, ?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, city.getCityName());
			insertStmt.setString(2, city.getState());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int cityId = -1;
			if(resultKey.next()) 
			{
				cityId = resultKey.getInt(1);
			} 
			else 
			{
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			city.setCityId(cityId);
			return city;
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
	 * get city by city id
	 * @param cityId
	 * @return
	 * @throws SQLException
	 */
	public City getCityById(int cityId) throws SQLException
	{
		String select = "select * from City where CityId =?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, cityId);
			results = selectStmt.executeQuery();
			if(results.next()) 
			{
				String cityName = results.getString("CityName");
				String state = results.getString("State");
				
				City city = new City(cityId, cityName, state);
				return city;
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
	 * get a list of cities by cityName 
	 * @param state
	 * @return
	 * @throws SQLException
	 */
	public List<City> getCityByName(String cityName) throws SQLException
	{
		String select = " select * from City where CityName like '%"+cityName+ "%' ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<City> citylist = new ArrayList<City>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			results = selectStmt.executeQuery();
			while(results.next()) 
			{
				int cityId = results.getInt("CityId");
				String state = results.getString("State");
				String cityname = results.getString("CityName");
				
				City city = new City(cityId, cityname, state);
				citylist.add(city);
			}
			return citylist;
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
	}
	/**
	 * update one record of city
	 * @param city
	 * @param cityname
	 * @param state
	 * @return
	 * @throws SQLException
	 */
	public City update(City city, String cityname, String state) throws SQLException
	{
		String update = " update City set CityName = ?, State = ? where CityId = ? ";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setString(1, cityname);
			updateStmt.setString(2, state);
			updateStmt.setInt(3, city.getCityId());
			updateStmt.executeUpdate();

			city.setCityName(cityname);
			city.setState(state);
			return city;
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
	/**
	 * delete one record from City 
	 * @param city
	 * @return
	 * @throws SQLException
	 */
	public City delete(City city) throws SQLException
	{
		String delete = " delete from City where CityId =?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, city.getCityId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the City instance.
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
