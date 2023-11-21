package pdla.volunteer;

import java.awt.BorderLayout;
import java.sql.SQLException;
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
 * View for the volunteer view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class VolunteerView {
	
	private JFrame frame = new JFrame("Volunteer");
	private JButton refresh = new JButton("Refresh");
	private List<Task> listTasks = new ArrayList<>();
	private String[] optionsToChoose = {"Status", "Alphabetic", "User"};
	private JComboBox<String> SortButton = new JComboBox<>(optionsToChoose);
	
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel topPanel = new JPanel();    
	private JPanel centerPanel = new JPanel();
	private JScrollPane scrollerBar = new JScrollPane(centerPanel);
	
	/*
	 * Initiate all the component on the screen.
	 */
	private void createScreen() {
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
     * @return refresh button (JButton).
     * */
	public JButton getButton() {
		return refresh;
	}
	
	/**
     * @return refresh button (JButton).
     * */
	public JComboBox<String> getButtonSort () {
		return SortButton;
	}
	
	/**
     * Set list of tasks.
     * */
	public void setTaskList(List<Task> list) {
		listTasks.clear();
		listTasks.addAll(list);
	}
	
	/**
     * Update the screen by printing of the task in the list. 
     * */
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
     * Constructor
     * */
	public VolunteerView() {
		createScreen();
	}

}
