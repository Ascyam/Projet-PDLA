package pdla.volunteer;

import java.util.List;

import pdla.database.DatabaseCommunication;
import pdla.task.*;

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
