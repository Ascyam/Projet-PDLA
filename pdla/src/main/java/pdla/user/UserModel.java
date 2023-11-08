package pdla.user;

import java.util.List;

import pdla.database.*;
import pdla.task.Task;
import pdla.task.UserTask;

public class UserModel extends DatabaseCommunication{
	
	public UserModel(int id) {
		super(id);
	}
	
	public List<Task> getTask() {
		return UserModel.getTaskDB(UserTask.class);
	}
	
	public void addTask(String title) {
		UserModel.addTaskDB(title);
	}
	
	public void removeTask(int id) {
		UserModel.removeTaskDB(id);
	}
	
	public void changeTaskString(String field,String value,int id) {
		UserModel.changeTaskStringDB(field,value,id);
	}
	
	public void changeTaskInt(String field,int value,int id) {
		UserModel.changeTaskIntDB(field,value,id);
	}
}
