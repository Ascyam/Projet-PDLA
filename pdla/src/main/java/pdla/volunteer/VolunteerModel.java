package pdla.volunteer;

import java.util.List;

import pdla.database.DatabaseCommunication;
import pdla.task.*;

public class VolunteerModel extends DatabaseCommunication{
	public VolunteerModel() {
		super();
	}
	
	public List<Task> getTask() {
		return VolunteerModel.getTaskDB(VolunteerTask.class);
	}
	
	public void changeTaskString(String field,String value,int id) {
		VolunteerModel.changeTaskStringDB(field,value,id);
	}
}
