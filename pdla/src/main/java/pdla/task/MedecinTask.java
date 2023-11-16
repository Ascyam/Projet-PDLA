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
import javax.swing.JComboBox;

/**
 * 
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
public class MedecinTask extends JPanel implements Task {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private JLabel title;
	private String[] optionsToChoose = {"Wait validation", "In progress", "End", "Cancel"};
	private JComboBox<String> status = new JComboBox<>(optionsToChoose);	
	private JTextField reasonField;
	private JLabel volunteer;
	private JLabel feedback;
	private TaskListener listener;
	
	/**
	 * 
	 * @param Every fields from the database
	 *
	 */
	public MedecinTask(int id, String title, String username, String volunteer, String status, String reason, String feedback, String user) {
		super(new BorderLayout());
		this.id = id;
		this.username=username;
		this.title=new JLabel(title);
		this.status.setSelectedItem(status);
		this.reasonField=new JTextField(reason,15);
		this.volunteer=new JLabel(volunteer);
		this.feedback=new JLabel(feedback);
		JPanel center = new JPanel();
		TaskInputListener inputListener = new TaskInputListener(this, reasonField);
		
		center.add(this.title);
		center.add(this.status);
		this.status.addItemListener(inputListener);
		
		if(status.equals("Wait validation")) {
			center.add(this.volunteer);
		}
		
		else if(status.equals("In progress")) {
			this.status.setForeground(Color.BLUE);
			center.add(this.volunteer);
		}
		
		else if(status.equals("End")) {
			Color green = new Color (50, 129, 50);
			this.status.setForeground(green);
			center.add(this.volunteer);
			this.feedback.setText(feedback);
			center.add(this.feedback);
		}
		
		else if(status.equals("Cancel")) {
			this.status.setForeground(Color.RED);
			center.add(this.reasonField);
			this.reasonField.addKeyListener(inputListener);
			center.add(this.volunteer);
		}
		
		add(center);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(inputListener);
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getUsername()));
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
		return this.title.getText();
	}

	@Override
	public String getStatus() {
		return (String) this.status.getSelectedItem();
	}

	@Override
	public String getReason() {
		return this.reasonField.getText();
	}

	@Override
	public String getVolunteer() {
		return this.volunteer.getText();
	}

	@Override
	public String getFeedback() {
		return null;
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
