package pdla.medecin;

import java.util.List;

import pdla.database.DatabaseCommunication;
import pdla.task.*;

/**
 * Model for the medecin view. This class made the link between the controller and different methods from the database class.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class MedecinModel{
	DatabaseCommunication database;
	
	public MedecinModel(int id) {
		this.database=new DatabaseCommunication();
	}
	
	public List<Task> getTask() {
		return database.getTaskDB(MedecinTask.class);
	}
	
	public void removeTask(int id) {
		database.removeTaskDB(id);
	}
	
	public void changeTaskString(String field,String value,int id) {
		database.changeTaskStringDB(field,value,id);
	}
}
