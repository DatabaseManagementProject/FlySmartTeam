package flight.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import flight.model.Airport;
import flight.model.City;
import flight.model.CityAirportRelation;
/**
 * 
 * @author XueyiGu
 *
 */
public class CityAirportRelationDao 
{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CityAirportRelationDao instance = null;
	protected CityAirportRelationDao() {
		connectionManager = new ConnectionManager();
	}
	public static CityAirportRelationDao getInstance() {
		if(instance == null) {
			instance = new CityAirportRelationDao();
		}
		return instance;
	}
	/**
	 * insert into table CityAirportRelation a record
	 * @param relation
	 * @return
	 * @throws SQLException
	 */
	public CityAirportRelation create(CityAirportRelation relation) throws SQLException
	{
		String insert = " insert into CityAirportRelation(CityId, AirportId) values(?, ?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, relation.getCity().getCityId());
			insertStmt.setInt(2, relation.getAirport().getAirportId());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int relationId = -1;
			if(resultKey.next()) 
			{
				relationId = resultKey.getInt(1);
			} 
			else 
			{
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			relation.setRelationId(relationId);
			return relation;
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
	 * get a list of relation by city id
	 * @param cityId
	 * @return
	 * @throws SQLException
	 */
	public List<CityAirportRelation> getRelationByCity(int cityId) throws SQLException
	{
		String select = " select * from CityAirportRelation where CityId = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<CityAirportRelation> relationlist = new ArrayList<CityAirportRelation>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, cityId);
			results = selectStmt.executeQuery();
			
			CityDao cityDao = CityDao.getInstance();
			AirportDao airportDao = AirportDao.getInstance();
			
			if(results.next()) 
			{
				int relationId = results.getInt("RelationId");
				int airportId = results.getInt("AirportId");
				
				City city = cityDao.getCityById(cityId);
				Airport airport = airportDao.getAirportById(airportId);
				
				CityAirportRelation relation = new CityAirportRelation(relationId, city, airport);
				relationlist.add(relation);
				
			}
			return relationlist;
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
	 * get a list of city by airport id
	 * @param airportId
	 * @return
	 * @throws SQLException
	 */
	public List<CityAirportRelation> getRelationByAirport(int airportId) throws SQLException
	{

		String select = " select * from CityAirportRelation where AirportId = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<CityAirportRelation> relationlist = new ArrayList<CityAirportRelation>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, airportId);
			results = selectStmt.executeQuery();
			
			CityDao cityDao = CityDao.getInstance();
			AirportDao airportDao = AirportDao.getInstance();
			
			if(results.next()) 
			{
				int relationId = results.getInt("RelationId");
				int cityId = results.getInt("cityId");
				
				City city = cityDao.getCityById(cityId);
				Airport airport = airportDao.getAirportById(airportId);
				
				CityAirportRelation relation = new CityAirportRelation(relationId, city, airport);
				relationlist.add(relation);
				
			}
			return relationlist;
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
	 * delete one record from CityAirportRelation by relation id
	 * @param relation
	 * @return
	 * @throws SQLException
	 */
	public CityAirportRelation delete(CityAirportRelation relation) throws SQLException
	{
		String delete = "delete from CityAirportRelation where RelationId = ?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, relation.getRelationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the CityAirportRelation instance.
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
	/**
	 * delete one record from CityAirportRelation by relation id
	 * @param relation
	 * @return
	 * @throws SQLException
	 */
	public CityAirportRelation update(CityAirportRelation relation, int cityId, int airportid) throws SQLException
	{
		String update = "update CityAirportRelation set CityId = ?, AirportId = ? where RelationId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setInt(1, cityId);
			updateStmt.setInt(2, airportid);
			updateStmt.setInt(3, relation.getRelationId());
			updateStmt.executeUpdate();

			CityDao cityDao = CityDao.getInstance();
			AirportDao airportDao = AirportDao.getInstance();
			
			relation.setCity(cityDao.getCityById(cityId));
			relation.setAirport(airportDao.getAirportById(airportid));
			return relation;
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

}
