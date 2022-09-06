package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Model;
import model.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";
	private User user;
	private Model model;
	private FileInputStream fis;

	public UserDaoImpl() {
	}

	@Override
	public void setup() throws SQLException {
		try (Connection connection = Database.getConnection();
			 Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username TEXT NOT NULL,"
					+ "password TEXT NOT NULL," + "firstname TEXT NOT NULL," + "lastname TEXT NOT NULL," + " Image TEXT NOT NULL," + "PRIMARY KEY (username))";
			stmt.executeUpdate(sql);
		}
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);


			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setImage(rs.getString("image"));

					return user;
				}
				return null;
			}
		}
	}

	@Override
	public User createUser(String username, String password, String firstname, String lastname, String filepath) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ? ,? ,?,?)";


		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, firstname);
			stmt.setString(4, lastname);
			stmt.setString(5,filepath);




			stmt.executeUpdate();
			return new User(username, password, firstname, lastname,filepath);
		}
	}

	@Override
	public User updateUser(String firstname, String lastname, String username) throws SQLException {


		String sql = "UPDATE " + TABLE_NAME + " SET firstname = ?  , lastname = ?  " + "WHERE username  LIKE ?";


		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setString(3, username);

			stmt.executeUpdate(sql);



			}





		return null;
	}


}
