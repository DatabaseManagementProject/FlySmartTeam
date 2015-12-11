package flight.model;

public class User {
	
    protected String UserName;
    protected String FirstName;
    protected String LastName;
    protected String Password;
    protected String Email;
    protected String Phone;
    
    public User(String _username, String _firstname, String _lastname,
    		String _password, String _email, String _phone){

    	this.UserName = _username;
    	this.FirstName = _firstname;
    	this.LastName = _lastname;
    	this.Password = _password;
    	this.Email = _email;
    	this.Phone = _phone;
    }

	public String getUserName(){
		return UserName;
	}
	
	public String getFirstName(){
		return FirstName;
	}
	
	public String getLastName(){
		return LastName;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public String getEmail(){
		return Email;
	}
	
	public String getPhone(){
		return Phone;
	}
	
	public void setUserName(String _username){
		this.UserName = _username;
	}
	
	public void setFirstName(String _firstname){
		this.FirstName = _firstname;
	}
	
	public void setLastName(String _lastname){
		this.LastName = _lastname;
	}
	
	public void setPassword(String _password){
		this.Password = _password;
	}
	
	public void setEmail(String _email){
		this.Email = _email;
	}
	
	public void setPhone(String _phone){
		this.Phone = _phone;
	}
	
	


}
