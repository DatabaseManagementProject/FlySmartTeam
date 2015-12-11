package flight.tool;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import flight.dal.*;
import flight.model.*;

import java.sql.Timestamp;


public class insert {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		//RouteDao routeDao = RouteDao.getInstance();
		
		
		// Insertion(create) with Dao instances.
		//Route route = new Route(1, "12", "34", 500);
		//Route route4 = new Route(2, "23", "56", 400);
		//routeDao.create(route);
		//routeDao.create(route4);
		
		
		
		// Read from the database
		
		//Route route1 = routeDao.getRouteFromRouteId(1);
		//System.out.format("Reading routes: u:%s f:%s l:%s a:%s \n",
		//	route1.getRouteId(), route1.getOriginAirportId(), route1.getDestinationAirportId(), route1.getDistance());
		
		
		// Update
		
		//Route route2 = routeDao.updateDistance(route, 200);
		
		// Delete items
		//routeDao.delete(route);
		
		
//		// DAO instances.
//		CommentDao commentDao = CommentDao.getInstance();
//		
//		
//		// Insertion(create) with Dao instances.
//		Comment comment = new Comment(1, "comment1", "carrier1", "user1", 4);
//		Comment comment2 = new Comment(2, "comment2", "carrier2", "user2", 5);
//		Comment comment4 = new Comment(3, "comment1", "carrier1", "user1", 4);
////		commentDao.create(comment);
//		commentDao.create(comment4);
//		
//		
//		
//		// Read from the database
//		
//		List<Comment> comments = commentDao.getCommentFromUserName("user1");
//		for(Comment c : comments) {
//			System.out.format("Looping comments: u:%s f:%s l:%s d:%s s:%s %s \n",
//				c.getCommentId(), c.getContent(), c.getCarrierId(), c.getUserName(), c.getRating(), c.getCreatedTime());
//		}
//		
//		// Update
//		
//		//Comment comment3 = commentDao.updateContent(comment2, "NewComment2");
//		
//		 // Delete items
//		//commentDao.delete(comment2);


		// DAO instances.
		UserDao userDao = UserDao.getInstance();
		
		
		// Insertion(create) with Dao instances.
		User user1 = new User("Zhiyu", "Zhiyu", "Li", "12345", "lizhiyu@gmail.com", "765777123");
		User user2 = new User("Zhiyu2", "Zhiyu", "Li", "12345", "lizhiyu2@gmail.com", "765777123");
//		userDao.create(user1);
//		userDao.create(user2);
		
		
		
		// Read from the database
		
//		User result_user1 = userDao.getUserFromUserName("Zhiyu");
//		System.out.format("Reading routes: username:%s firstname:%s lastname:%s password:%s  email:%s phone:%s \n",
//				result_user1.getUserName(), result_user1.getFirstName(), result_user1.getLastName(), result_user1.getPassword(),
//				result_user1.getEmail(), result_user1.getPhone());
			
		
		
		// Update
		
		//User result_user2 = userDao.updatePassword(user1, "new password");
		
		// Delete items
		// userDao.delete(user1);		
		
		
	}

}
