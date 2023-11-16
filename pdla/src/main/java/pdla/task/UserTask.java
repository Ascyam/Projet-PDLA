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
	private JTextField feedbackField;
	private TaskListener listener;
	
	/**
	 * 
	 * @param Every fields from the database
	 *
	 */
	public UserTask(int id, String title, String username, String volunteer, String status, String reason, String feedback,String user) {
		super(new BorderLayout());
		this.id = id;
		this.username=username;
		this.titleField=new JTextField(title,20);
		this.title=new JLabel(title);
		this.status=new JLabel(status);
		this.reason=new JLabel(reason);
		this.volunteer=new JLabel(volunteer);
		this.feedbackField=new JTextField(feedback,20);
		JPanel center = new JPanel();
		
		if(status.equals("Wait validation")) {
			center.add(this.titleField);
			TaskInputListener inputListener = new TaskInputListener(this, titleField);
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
			center.add(this.feedbackField);
			TaskInputListener inputListener = new TaskInputListener(this, feedbackField);
			feedbackField.addKeyListener(inputListener);
		}
		
		else if(status.equals("Cancel")) {
			center.add(this.title);
			center.add(this.status);
			this.status.setForeground(Color.RED);
			this.reason.setForeground(Color.RED);
			center.add(this.reason);
			center.add(this.volunteer);
		}
		
		add(center);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(new TaskInputListener(this));
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getUsername()));
	}
	
	public UserTask() {
		this.titleField=new JTextField("New request");
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getTitle() {
		return this.titleField.getText();
	}

	@Override
	public String getStatus() {
		return this.status.getText();
	}

	@Override
	public String getReason() {
		return this.reason.getText();
	}

	@Override
	public String getVolunteer() {
		return this.volunteer.getText();
	}

	@Override
	public String getFeedback() {
		return this.feedbackField.getText();
	}

	@Override
	public void setTaskListener(TaskListener t) {
		this.listener=t;
	}

	@Override
	public TaskListener getTaskListener() {
		return this.listener;
	}

	@Override
	public Component getGuiComponent() {
		return this;
	}
}
