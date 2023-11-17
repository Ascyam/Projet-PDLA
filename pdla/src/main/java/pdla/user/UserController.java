package pdla.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pdla.sorting.*;
import pdla.task.*;

public class UserController implements TaskListener, ActionListener, ItemListener{
	
	private List<Task> listTasks = new ArrayList<>();
	private UserModel model;
	private UserView view;
	
	public UserController(int id) {
		this.model = new UserModel(id);
		this.view = new UserView();
		view.getButton().addActionListener(this);
		view.getButtonRefresh().addActionListener(this);
		view.getButtonSort().addItemListener(this);
		getTask();
	}
	
	private void getTask() {
		this.listTasks.clear();
		this.listTasks.addAll(model.getTask());
		listTasks.forEach((c)->c.setTaskListener(this));
		sort((String) view.getButtonSort().getSelectedItem());
		view.setTaskList(this.listTasks);
		view.updateScreen();
	}
	
	private void createTask() {
		model.addTask("New request");
		getTask();
	}
	
	private void sort(String sort) {
		if (sort.equals("Alphabetic")) {
			Collections.sort(this.listTasks, new titleComparator());
		}
		else if  (sort.equals("Status")) {
			Collections.sort(this.listTasks, new statusComparator());
		}
	}

	@Override
	public void taskChanged(Task t) {
		model.changeTaskString("title",t.getTitle(),t.getID());
		model.changeTaskString("feedback",t.getFeedback(),t.getID());
		getTask();
	}

	@Override
	public void taskRemoved(Task t) {
		model.removeTask(t.getID());
		getTask();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.getButton())) {
			createTask();
		}else {
			getTask();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		getTask();
	}
}
