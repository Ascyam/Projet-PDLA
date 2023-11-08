package pdla.medecin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import pdla.task.*;

public class MedecinController implements TaskListener{
	
	private List<Task> listTasks = new ArrayList<>();
	private MedecinModel model;
	private MedecinView view;
	
	public MedecinController(int id) {
		this.model = new MedecinModel(id);
		this.view = new MedecinView();
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
	public void taskChanged(Task t) {
		model.changeTaskString("status",t.getStatus(),t.getID());
		model.changeTaskString("reason",t.getReason(),t.getID());
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}

	@Override
	public void taskRemoved(Task t) {
		model.removeTask(t.getID());
		getTask();
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}
}