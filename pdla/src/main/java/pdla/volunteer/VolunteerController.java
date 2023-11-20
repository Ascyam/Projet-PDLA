package pdla.volunteer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pdla.sorting.*;
import pdla.task.*;

/**
 * Controller for the volunteer view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class VolunteerController implements TaskListener, ActionListener, ItemListener{
	
	private List<Task> listTasks = new ArrayList<>();
	private VolunteerModel model;
	private VolunteerView view;
	
	public VolunteerController(int id) {
		this.model = new VolunteerModel(id);
		this.view = new VolunteerView();
		getTask();
		view.getButton().addActionListener(this);
		view.getButtonSort().addItemListener(this);
	}
	
	private void getTask() {
		this.listTasks.clear();
		this.listTasks.addAll(model.getTask());
		listTasks.forEach((c)->c.setTaskListener(this));
		sort((String) view.getButtonSort().getSelectedItem());
		view.setTaskList(listTasks);
    	view.updateScreen();
	}
	
	private void sort(String sort) {
		if (sort.equals("Alphabetic")) {
			Collections.sort(this.listTasks, new titleComparator());
		}
		else if  (sort.equals("Status")) {
			Collections.sort(this.listTasks, new statusComparator());
		}
		else if  (sort.equals("User")) {
			Collections.sort(this.listTasks, new usernameComparator());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		getTask();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		getTask();
	}
}