package pdla.task;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;

/**
 * 
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
public class TaskInputListener implements KeyListener, ActionListener, ItemListener {

	private Task task;
	private JTextField text;

	/**
	 * @param task is the task object that this listener is responsible for. 
	 * @param text is the text input field that will be display or not.
	 */
	public TaskInputListener(Task task, JTextField text) {
		this.task = task;
		this.text = text;
	}

	public TaskInputListener(Task task) {
		this.task = task;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			TaskListener listener = task.getTaskListener();
			if (listener != null) listener.taskChanged(task);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TaskListener listener = task.getTaskListener();
		if (listener != null) listener.taskRemoved(task);				
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(((String) e.getItem()).equals("Cancel")) {
			text.setVisible(true);
		}else {
			text.setVisible(false);
		}
		TaskListener listener = task.getTaskListener();
		if (listener != null) listener.taskChanged(task);
	}
}

