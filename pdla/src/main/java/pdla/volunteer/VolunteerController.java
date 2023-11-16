package pdla.volunteer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import pdla.task.*;

public class VolunteerController implements TaskListener, ActionListener{
	
	private List<Task> listTasks = new ArrayList<>();
	private VolunteerModel model;
	private VolunteerView view;
	
	public VolunteerController(int id) {
		this.model = new VolunteerModel(id);
		this.view = new VolunteerView();
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
		view.getButton().addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	getTask();
	        	view.setTaskList(listTasks);
	        	view.updateScreen();
	        }});
	}
	
	private void getTask() {
		this.listTasks.clear();
		this.listTasks.addAll(model.getTask());
		listTasks.forEach((c)->c.setTaskListener(this));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}

	@Override
	public void taskChanged(Task t) {
		model.changeTaskString("volunteer",t.getVolunteer(),t.getID());
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}

	@Override
	public void taskRemoved(Task t) {}
}