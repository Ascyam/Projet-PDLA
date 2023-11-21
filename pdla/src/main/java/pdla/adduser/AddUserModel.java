package pdla.adduser;

import java.util.ArrayList;

import pdla.database.DatabaseCommunication;

/**
 * Model for the adduser view. This class connect with the database to add user.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class AddUserModel extends DatabaseCommunication{
	DatabaseCommunication database;
	
	public void addUser(ArrayList<String> list) {
		database = new DatabaseCommunication();
		database.addUserDB(list.get(0), list.get(1), list.get(2), list.get(3));
	}
}
