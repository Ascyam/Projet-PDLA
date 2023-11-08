package pdla.medecin;

import java.util.List;

import pdla.database.DatabaseCommunication;
import pdla.task.*;

public class MedecinModel extends DatabaseCommunication{
	public MedecinModel(int id) {
		super();
	}
	
	public List<Task> getTask() {
		return MedecinModel.getTaskDB(MedecinTask.class);
	}
	
	public void removeTask(int id) {
		MedecinModel.removeTaskDB(id);
	}
	
	public void changeTaskString(String field,String value,int id) {
		MedecinModel.changeTaskStringDB(field,value,id);
	}
}
