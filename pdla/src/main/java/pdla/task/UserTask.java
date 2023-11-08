package pdla.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
 public class UserTask extends JPanel implements Task {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private JTextField titleField;
	private JLabel title;
	private JLabel status;
	private JLabel reason;
	private JLabel volunteer;
	private JTextField noteField;
	private JLabel note;
	private TaskListener listener;
	int noteInt;
	
	/**
	 * 
	 * @param Every fields from the database
	 *
	 */
	public UserTask(int id, String title, String username, String volunteer, String status, String reason, int note) {
		super(new BorderLayout());
		this.id = id;
		this.username=username;
		this.titleField=new JTextField(title,20);
		this.title=new JLabel(title);
		this.status=new JLabel(status);
		this.reason=new JLabel(reason);
		this.volunteer=new JLabel(volunteer);
		this.note=new JLabel("/10");
		this.noteField = new JTextField(Integer.toString(note),2);
		JPanel center = new JPanel();
		TaskInputListener inputListener = new TaskInputListener(this);
		this.noteInt=note;
		
		if(status.equals("Wait validation")) {
			center.add(this.titleField);
			inputListener = new TaskInputListener(this, titleField);
			titleField.addKeyListener(inputListener);
			center.add(this.status);
			center.add(this.volunteer);
		}
		
		else if(status.equals("In progress")) {
			center.add(this.title);
			center.add(this.status);
			this.status.setForeground(Color.BLUE);
			center.add(this.volunteer);
		}
		
		else if(status.equals("End")) {
			center.add(this.title);
			center.add(this.status);
			Color green = new Color (50, 129, 50);
			this.status.setForeground(green);
			center.add(this.volunteer);
			center.add(this.noteField);
			inputListener = new TaskInputListener(this, noteField);
			noteField.addKeyListener(inputListener);
			center.add(this.note);
		}
		
		else if(status.equals("Cancel")) {
			center.add(this.title);
			center.add(this.status);
			this.status.setForeground(Color.RED);
			center.add(this.reason);
			center.add(this.volunteer);
		}
		
		add(center);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(inputListener);
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getUsername()));
	}
	
	public UserTask() {
		this.titleField=new JTextField("New request");
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.titleField.getText();
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status.getText();
	}

	@Override
	public String getReason() {
		// TODO Auto-generated method stub
		return this.reason.getText();
	}

	@Override
	public String getVolunteer() {
		// TODO Auto-generated method stub
		return this.volunteer.getText();
	}

	@Override
	public int getNote() {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.noteField.getText());
	}

	@Override
	public void setTaskListener(TaskListener t) {
		// TODO Auto-generated method stub
		this.listener=t;
	}

	@Override
	public TaskListener getTaskListener() {
		// TODO Auto-generated method stub
		return this.listener;
	}

	@Override
	public Component getGuiComponent() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}
