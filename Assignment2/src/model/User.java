package model;


import java.awt.*;

public class User {
	private String username;
	private String password;

	private String firstname;

	private String lastname;

	private  String Image;







	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String firstname, String lastname ,String Image) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}



    public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}
}
