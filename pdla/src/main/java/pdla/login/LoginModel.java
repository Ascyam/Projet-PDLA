package pdla.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Model for the login view. This class try a connection with the user inputs.
 *  
 * @author Benjamin Zolver
 * @version 1.0
 * */
public class LoginModel {
	
	private String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013";
    private String usernamedb = "projet_gei_013";
    private String passworddb = "thoo1Xoh";
	
    /**
     * Try a connection with the user inputs.
     *  
     * @param information ArrayList<String> : first field is the username and then the password.
     * */
	public int tryConnection(ArrayList<String> information) {
	    try {
	        Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
	        Statement statement = connexion.createStatement();
	        String inputUsername = information.get(0);
	        String inputPassword = information.get(1);
	        String sql = "SELECT * FROM Login WHERE username=\"" + inputUsername + "\" AND password=\"" + inputPassword + "\""; 
	        ResultSet resultSet = statement.executeQuery(sql);
	        if(resultSet.isBeforeFirst()) {
	        	resultSet.next();
	        	int result = resultSet.getInt(1);
	        	resultSet.close();
	            statement.close();
	            connexion.close();
	            return result;
	        }else {
	        	resultSet.close();
	            statement.close();
	            connexion.close();
	            return 0;
	        }
	        
	    } catch (SQLException e) {
	        System.out.println(e);
	        e.printStackTrace();
	    }
		return 0;
	}
	
	/**
     * Get the role of an user from the table Login according to his id.
     *  
     * @param id int : the id of the user
     * @return the role or an empty string is there is a error 
     * */
	public String getRole(int id) {
		try {
	        Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
	        Statement statement = connexion.createStatement();
	        String sql = "SELECT Role FROM Login WHERE userID="+Integer.toString(id); 
	        ResultSet resultSet = statement.executeQuery(sql);
	        resultSet.next();
	        String result = resultSet.getString(1);
	        resultSet.close();
            statement.close();
            connexion.close();
	        return result;
	    } catch (SQLException e) {
	        System.out.println(e);
	        e.printStackTrace();
	    }
		return "";
	}
}
