package pdla.volunteer;

import java.util.List;

import pdla.database.DatabaseCommunication;
import pdla.task.*;

/**
 * Model for the volunteer view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class VolunteerModel{
	DatabaseCommunication database;
	
	public VolunteerModel(int id) {
		this.database = new DatabaseCommunication(id);
	}
	
	public List<Task> getTask() {
		return database.getTaskDB(VolunteerTask.class);
	}
	
	public void changeTaskString(String field,String value,int id) {
		database.changeTaskStringDB(field,value,id);
	}
}
