package pdla.user;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pdla.task.*;

/**
 * View for the user view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class UserView {
	
	private JFrame frame = new JFrame("Request Help");
	private JButton newTask = new JButton("Request help");
	private JButton refresh = new JButton("Refresh");
	private String[] optionsToChoose = {"Status", "Alphabetic"};
	private JComboBox<String> SortButton = new JComboBox<>(optionsToChoose);
	private List<Task> listTasks = new ArrayList<>();
	
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel topPanel = new JPanel();    
	private JPanel centerPanel = new JPanel();
	private JScrollPane scrollerBar = new JScrollPane(centerPanel);
	
	/**
	 * Init the screen. 
	 */
	private void createScreen() {
		topPanel.add(newTask);
		topPanel.add(refresh);
		topPanel.add(SortButton);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    panel.add(topPanel, BorderLayout.NORTH);
	    panel.add(scrollerBar, BorderLayout.CENTER);
	    frame.add(panel);
	    frame.setSize(800,300);
	    frame.setVisible(true); 
	}
	
	/**
	 * Get the add task button.
	 * @return JButton. 
	 */
	public JButton getButton() {
		return newTask;
	}
	
	/**
	 * Get the refresh button.
	 * @return JButton. 
	 */
	public JButton getButtonRefresh() {
		return refresh;
	}
	
	/**
	 * Return the ComboBox for sorting tasks.
	 * @return JComboBox.
	 */
	public JComboBox<String> getButtonSort() {
		return SortButton;
	}
	
	/**
	 * Set list of tasks in the class.
	 * @param list of task.
	 */
	public void setTaskList(List<Task> list) {
		listTasks.clear();
		listTasks.addAll(list);
	}
	
	/**
	 * Upadate the screen and print all tasks.
	 */
	public void updateScreen() {
		centerPanel.removeAll();
		centerPanel.revalidate();
		centerPanel.repaint();
		for (int f=0; f<listTasks.size(); f++) {
			centerPanel.add(listTasks.get(f).getGuiComponent());
		}
		frame.setVisible(true);
	}
	
	/**
	 * Add only one new task on the screen.
	 * @param t : the new task to add on the screen
	 */
	public void addNewTaskScreen(Task t) {
		centerPanel.add(t.getGuiComponent(),0);
		frame.setVisible(true);
	}
	
	public UserView() {
		createScreen();
	}

}
