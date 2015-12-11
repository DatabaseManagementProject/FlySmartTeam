package flight.model;
import java.sql.Timestamp;





public class Comment {
	protected int CommentId;
	protected String Content;
	protected String CarrierId;
	protected String UserName;
	protected int Rating;
	protected Timestamp CreatedTime;
	

	
	public Comment(int commentid, String content, String carrierid, String username, int rating, Timestamp createdtime)
	{
		this.CommentId = commentid;
		this.Content = content;
		this.CarrierId = carrierid;
		this.Rating = rating;
		this.UserName = username;
		this.CreatedTime = createdtime;
	}
	
	public Comment(int commentid) {
		this.CommentId = commentid;
	}
	
	public Comment(String content, String carrierid, String username, int rating, Timestamp createdtime)
	{
		this.Content = content;
		this.CarrierId = carrierid;
		this.Rating = rating;
		this.UserName = username;
		this.CreatedTime = createdtime;
	}
	
	public Comment(int commentid, String content, String carrierid, String username, int rating)
	{
		this.Content = content;
		this.CarrierId = carrierid;
		this.Rating = rating;
		this.UserName = username;
		this.CommentId = commentid;
	}
	
	public Comment(String content, String carrierid, String username, int rating)
	{
		this.Content = content;
		this.CarrierId = carrierid;
		this.Rating = rating;
		this.UserName = username;
	}

	/** Getters and setters. */
	
	public int getCommentId() {
		return CommentId;
	}

	public void setCommentId (int commentid) {
		this.CommentId = commentid;
	}
	
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
	
	
	public String getCarrierId() {
		return CarrierId;
	}

	public void setCarrierId(String carrierid) {
		this.CarrierId = carrierid;
	}
	
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String username) {
		this.UserName = username;
	}

	public Timestamp getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(Timestamp createdtime) {
		this.CreatedTime = createdtime;
	}

	
	public int getRating() {
		return Rating;
	}

	public void setRating (int rating) {
		this.Rating = rating;
	}
	
	

}
