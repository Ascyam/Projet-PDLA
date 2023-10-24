package  fr.insa.alanben;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.SQLException;

import java.util.*;
import java.util.List;

import se.his.it401g.todo.*;

public class VolunteerScreen implements TaskListener {
private JButton newTask = new JButton("Request help"); 
	
	private JFrame frame = new JFrame("ToDo app");
	private String[] optionsToChoose = {"Done", "Alphabetical", "User"};
	private JComboBox<String> SortButton = new JComboBox<>(optionsToChoose);
	private List<Task> listTasks = new ArrayList<>();
	private JLabel label= new JLabel();
	
	private JPanel panel = new JPanel(new BorderLayout());
	private JPanel panel1 = new JPanel();    
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JScrollPane scrollerBar = new JScrollPane(panel2);
	
	private int nbTaskCheck = 0;
	
	private int userId;
	private String userName;
	
	//Init screen
	private void CreateScreen() {
	    //Add button to the screen
		panel1.add(newTask);
	    panel1.add(SortButton);
	    
	    //Layout panel2
	    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    
	    //Add text to the screen
	    label.setText("0 task");
	    panel3.add(label);
	    
	    //Conf screen
	    panel.add(panel1, BorderLayout.NORTH);
	    panel.add(scrollerBar, BorderLayout.CENTER);
	    panel.add(panel3, BorderLayout.SOUTH);
	    frame.add(panel);
	    frame.setSize(700,500);
	    frame.setVisible(true); 
	    
	    //Call function when button is pressed
	    SortButton.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ActionSortButton();
	        }});
	    newTask.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	ActionNewTask();
	        }}); 
	}
	
	//Action button and add on the screen
	private void ActionNewTask () {
		Task task = new RequestTask(userName);
		task.setTaskListener(this);
		listTasks.add(task);
		AddTaskOnScreen(task);
		ActionSortButton();
	}
	
	//Add on task on the screen
	private void AddTaskOnScreen(Task t) {
		panel2.add(t.getGuiComponent(), 0);
		UpdateNbDone();
		frame.setVisible(true);
	}
	
	//sorting of the list according to the choice 
	private void ActionSortButton() {
		if (SortButton.getItemAt(SortButton.getSelectedIndex())=="Done") {
			sortDone();
		}
		else if  (SortButton.getItemAt(SortButton.getSelectedIndex())=="Alphabetical") {
			sortAlphabetical();
		}
		else if  (SortButton.getItemAt(SortButton.getSelectedIndex())=="User") {
			sortType();
		}
	}
	private void sortDone() {
		CheckComparator sorter = new CheckComparator();
		Collections.sort(listTasks, sorter);
		for (int f=0; f<listTasks.size(); f++) {
			AddTaskOnScreen(listTasks.get(f));
		}
	}
	private void sortAlphabetical() {
		AlphabeticComparator sorter = new AlphabeticComparator();
		Collections.sort(listTasks, sorter);
		for (int f=0; f<listTasks.size(); f++) {
			AddTaskOnScreen(listTasks.get(f));
		}
	}
	private void sortType(){
		TypeComparator sorter = new TypeComparator();
		Collections.sort(listTasks, sorter);
		for (int f=0; f<listTasks.size(); f++) {
			AddTaskOnScreen(listTasks.get(f));
		}
	}
	
	//Update of the text at the bottom
	private void UpdateNbDone() {
		label.setText(nbTaskCheck + " out of " + listTasks.size()+" task(s) completed");
	}

	@Override
	public void taskChanged(Task t) {
		ActionSortButton();
	}
	@Override
	public void taskCompleted(Task t) {
		nbTaskCheck ++;
		UpdateNbDone();
		ActionSortButton();
	}
	@Override
	public void taskUncompleted(Task t) {
		nbTaskCheck --;
		UpdateNbDone();
		ActionSortButton();
	}
	@Override
	public void taskCreated(Task t) {
		ActionSortButton();
	}
	@Override
	public void taskRemoved(Task t) {
		if(t.isComplete()) {
			nbTaskCheck --;
		}
		panel2.remove(t.getGuiComponent());
		listTasks.remove(t);
		UpdateNbDone();
		panel2.repaint();
	}
	
	private void setUser() {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013"; // table details
        String usernamedb = "projet_gei_013"; // MySQL credentials
        String passworddb = "thoo1Xoh";
        try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            Statement statement = connexion.createStatement();
            String sql = "SELECT Firstname,Lastname FROM Login WHERE userID="+ String.valueOf(userId); 
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()) {
            	while(resultSet.next()) {
            		this.userName = resultSet.getString(1) +" " +resultSet.getString(2); 
            	}
            }
        }catch (SQLException e) {
	            System.out.println(e);
	            e.printStackTrace();
	        }
	}
	
	//Main call for the app
	public VolunteerScreen() {
		CreateScreen();
	}
	
	public VolunteerScreen(int userID) {
		this.userId = userID;
		CreateScreen();
		setUser();
	}
}