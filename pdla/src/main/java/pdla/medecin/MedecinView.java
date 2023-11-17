package pdla.medecin;

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

public class MedecinView {
	
	private JFrame frame = new JFrame("Medecin");
	private JButton newTask = new JButton("Refresh");
	private List<Task> listTasks = new ArrayList<>();
	private String[] optionsToChoose = {"Status", "Alphabetic", "User"};
	private JComboBox<String> SortButton = new JComboBox<>(optionsToChoose);
	
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel topPanel = new JPanel();    
	private JPanel centerPanel = new JPanel();
	private JScrollPane scrollerBar = new JScrollPane(centerPanel);
	
	private void createScreen() {
		topPanel.add(newTask);
		topPanel.add(SortButton);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    panel.add(topPanel, BorderLayout.NORTH);
	    panel.add(scrollerBar, BorderLayout.CENTER);
	    frame.add(panel);
	    frame.setSize(800,300);
	    frame.setVisible(true); 
	}
	
	public JButton getButton() {
		return newTask;
	}
	
	public JComboBox<String> getButtonSort(){
		return SortButton;
	}
	
	public void setTaskList(List<Task> list) {
		listTasks.clear();
		listTasks.addAll(list);
	}
	
	public void updateScreen() {
		centerPanel.removeAll();
		centerPanel.revalidate();
		centerPanel.repaint();
		for (int f=0; f<listTasks.size(); f++) {
			centerPanel.add(listTasks.get(f).getGuiComponent());
		}
		frame.setVisible(true);
	}
	
	public void addNewTaskScreen(Task t) {
		centerPanel.add(t.getGuiComponent(),0);
		frame.setVisible(true);
	}
	
	public MedecinView() {
		createScreen();
	}

}
