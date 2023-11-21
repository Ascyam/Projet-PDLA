package pdla.database;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import pdla.task.Task;

/**
 * Communication with the database. 
 * Different methods are implement to connect, get, change and remove values.
 * This class only work with database projet_gei_013 from srv-bdens.insa-toulouse.fr.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class DatabaseCommunication {
	private String userName;
	
	private static String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013";
    private static String usernamedb = "projet_gei_013";
    private static String passworddb = "thoo1Xoh";
    
    private List<Task> listTasks = new ArrayList<>();
    
    private String selectAll;

    /**
     * Constructor.
     * @param id the id of the task
     * */
	public DatabaseCommunication(int id) {
		setUsernameDB(id);
		if (getRole(id).equals("Volunteer")) {
			selectAll = "SELECT * FROM Task WHERE volunteer =\"\" OR volunteer=\""+userName+"\"";
		}else {
			selectAll = "SELECT * FROM Task WHERE username=\""+ userName +"\"";
		}
	}
	
	/**
     * Constructor.
     * */
	public DatabaseCommunication() {
		selectAll = "SELECT * FROM Task";
	}
	
	/**
     * Set the username of the current user.
     * @param id the id of the task
     * @throws SQLException.
     * */
	public void setUsernameDB(int id) {
		try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            Statement statement = connexion.createStatement();
            String sql = "SELECT Firstname,Lastname FROM Login WHERE userID="+ String.valueOf(id); 
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()) {
            	while(resultSet.next()) {
            		userName = resultSet.getString(1) +" " +resultSet.getString(2);
            	}
            }
        }catch (SQLException e) {e.printStackTrace();}
	}
	
	/**
     * Get all the task from the table Task.
     * @param classType the type of task you want to create.
     * @return List of all task. 
     * @throws SQLException
     * */
	public List<Task> getTaskDB(Class<? extends Task> classType){
		try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            Statement statement = connexion.createStatement();
            String sql = selectAll; 
            ResultSet resultSet = statement.executeQuery(sql);
            listTasks.clear();
            Constructor<? extends Task> task = classType.getConstructor(int.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class);
            if(resultSet.isBeforeFirst()) {
            	while(resultSet.next()) {
            		if(resultSet.getString(5).equals("Wait validation")) {
            			listTasks.add(task.newInstance(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"Wait validation",resultSet.getString(6),resultSet.getString(7),userName));
            		}else if(resultSet.getString(5).equals("In progress")) {
            			listTasks.add(task.newInstance(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"In progress",resultSet.getString(6),resultSet.getString(7),userName));
            		}else if(resultSet.getString(5).equals("Cancel")) {
            			listTasks.add(task.newInstance(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"Cancel",resultSet.getString(6),resultSet.getString(7),userName));
            		}else if(resultSet.getString(5).equals("End")){
            			listTasks.add(task.newInstance(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),"End",resultSet.getString(6),resultSet.getString(7),userName));
            		}
            	}
            }
            return listTasks;
        }catch (SQLException e) {e.printStackTrace();}catch (Exception e) {e.printStackTrace();}
		return listTasks;
	}

	/**
     * Add a task to the table Task. Some fields are automatically filled (username,volunteer,status,reason,note). 
     * @param title the title of task created.
     * @throws SQLException
     * */
	public void addTaskDB(String title) {
        try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            String sql = "INSERT INTO Task (title,username,volunteer,status,reason,feedback) VALUES (\""+title+ "\", \"" + userName + "\",\"\",\"Wait validation\",\"\",\"\")"; 
            PreparedStatement preparedSmnt =connexion.prepareStatement(sql);
            preparedSmnt.execute();
            connexion.close();
        }catch (SQLException e) {e.printStackTrace();}
	}
	
	/**
     * Add a user to the table Login.
     * @param firstname of the user.
     * @param lastname of the user. The lastname is also the username in lower character.
     * @param password of the user.
     * @param role of the user.
     * @throws SQLException
     * */
	public void addUserDB(String firstname, String lastname, String password, String role) {
        try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            String sql = "INSERT INTO Login (username, password , Firstname , Lastname , Role) VALUES (\""+lastname.toLowerCase()+ "\",\""+password+"\",\""+firstname+"\",\""+lastname+ "\",\""+role+ "\")"; 
            PreparedStatement preparedSmnt =connexion.prepareStatement(sql);
            preparedSmnt.execute();
            connexion.close();
        }catch (SQLException e) {e.printStackTrace();}
	}
	
	/**
     * Remove a task from the table Task. 
     * @param id the id of task removed.
     * @throws SQLException
     * */
	public void removeTaskDB(int id) {
		try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            String sql = "DELETE FROM Task WHERE id=\""+Integer.toString(id)+"\""; 
            PreparedStatement preparedSmnt =connexion.prepareStatement(sql);
            preparedSmnt.execute();
            connexion.close();
        }catch (SQLException e) {e.printStackTrace();}
	}
	
	/**
     * Change one field in a row. The field must be a String in the table.
     * @param field the field that is changed.
     * @param value the value that is set in the field.
     * @param id the id of the task.
     * @throws SQLException
     * */
	public void changeTaskStringDB(String field,String value,int id) {
		try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            String sql = "UPDATE Task SET "+field+"=\""+value+"\" WHERE id=\""+Integer.toString(id)+"\""; 
            PreparedStatement preparedSmnt =connexion.prepareStatement(sql);
            preparedSmnt.execute();
            connexion.close();
        }catch (SQLException e) {e.printStackTrace();}
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
