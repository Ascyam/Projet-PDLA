package pdla.user;

import java.util.List;

import pdla.database.*;
import pdla.task.Task;
import pdla.task.UserTask;

/**
 * Model for the user view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class UserModel {
	DatabaseCommunication database;
	
	public UserModel(int id) {
		this.database = new DatabaseCommunication(id);
	}
	
	public List<Task> getTask() {
		return database.getTaskDB(UserTask.class);
	}
	
	public void addTask(String title) {
		database.addTaskDB(title);
	}
	
	public void removeTask(int id) {
		database.removeTaskDB(id);
	}
	
	public void changeTaskString(String field,String value,int id) {
		database.changeTaskStringDB(field,value,id);
	}
}
