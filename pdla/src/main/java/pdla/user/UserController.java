package pdla.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import pdla.task.*;

public class UserController implements TaskListener{
	
	private List<Task> listTasks = new ArrayList<>();
	private UserModel model;
	private UserView view;
	
	public UserController(int id) {
		this.model = new UserModel(id);
		this.view = new UserView();
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
		view.getButton().addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	createTask();
	        }});
	}
	
	private void getTask() {
		this.listTasks.clear();
		this.listTasks.addAll(model.getTask());
		listTasks.forEach((c)->c.setTaskListener(this));
	}
	
	private void createTask() {
		model.addTask("New request");
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}

	@Override
	public void taskChanged(Task t) {
		model.changeTaskString("title",t.getTitle(),t.getID());
		model.changeTaskInt("note",t.getNote(),t.getID());
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}

	@Override
	public void taskRemoved(Task t) {
		System.out.println("Task removed !");
		model.removeTask(t.getID());
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}
}
