package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.sql.Timestamp;

public class CommentDao {
	protected ConnectionManager connectionManager;

	private static CommentDao instance = null;
	protected CommentDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentDao getInstance() {
		if(instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}
	
	public Comment create(Comment comment) throws SQLException {
		String insertBlogComment =
			"INSERT INTO Comment(CommentId,Content,CarrierId,UserName,Rating) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBlogComment,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, comment.getCommentId());
			insertStmt.setString(2, comment.getContent());
			insertStmt.setString(3, comment.getCarrierId());
			insertStmt.setString(4, comment.getUserName());
			insertStmt.setInt(5, comment.getRating());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			comment.setCommentId(commentId);
			return comment;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Update the content of the Comment instance.
	 * This runs a UPDATE statement.
	 */
	public Comment updateContent(Comment comment, String newContent) throws SQLException {
		String updateBlogComment = "UPDATE Comment SET Content=?,CreatedTime=? WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlogComment);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, comment.getCommentId());
			updateStmt.executeUpdate();

			// Update the comment param before returning to the caller.
			comment.setContent(newContent);
			comment.setCreatedTime(new Timestamp(newCreatedTimestamp.getTime()));
			return comment;
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
	 * Get the all the Comment for a user.
	 */
	public List<Comment> getCommentFromUserName(String userName) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		String selectComment =
			"SELECT CommentId,Content,CarrierId,UserName,Rating,CreatedTime " +
			"FROM Comment " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				String content = results.getString("Content");
				String carrierid = results.getString("CarrierId");
				String username = results.getString("UserName");
				int rating = results.getInt("Rating");
				Timestamp created = results.getTimestamp("CreatedTime");

				Comment comment = new Comment(commentId, content, carrierid, username, rating, created);
				comments.add(comment);
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
		return comments;
	}
	
	
	public List<Comment> getCommentFromCarrierId(String carrier) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		String selectComment =
			"SELECT CommentId,Content,CarrierId,UserName,Rating,CreatedTime " +
			"FROM Comment " +
			"WHERE CarrierId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setString(1, carrier);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				String content = results.getString("Content");
				String carrierid = results.getString("CarrierId");
				String username = results.getString("UserName");
				int rating = results.getInt("Rating");
				Timestamp created = results.getTimestamp("CreatedTime");

				Comment comment = new Comment(commentId, content, carrierid, username, rating, created);
				comments.add(comment);
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
		return comments;
	}
	/**
	 * Get the Comment for a commentid.
	 */
	public Comment getCommentFromCommentId(int commentid) throws SQLException {
		Comment comment = null;
		String selectComment =
			"SELECT CommentId,Content,CarrierId,UserName,Rating,CreatedTime " +
			"FROM Comment " +
			"WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1, commentid);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int commentId = results.getInt("CommentId");
				String content = results.getString("Content");
				String carrierid = results.getString("CarrierId");
				String username = results.getString("UserName");
				int rating = results.getInt("Rating");
				Timestamp created = results.getTimestamp("CreatedTime");

				comment = new Comment(commentId, content, carrierid, username, rating, created);
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
		return comment;
	}
	/**
	 * Delete the Comment instance.
	 * This runs a DELETE statement.
	 */
	public Comment delete(Comment comment) throws SQLException {
		String deleteBlogComment = "DELETE FROM Comment WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogComment);
			deleteStmt.setInt(1, comment.getCommentId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Comment instance.
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
