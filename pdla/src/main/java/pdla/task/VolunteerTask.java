package pdla.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Volunteer task.
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
public class VolunteerTask extends JPanel implements Task, ItemListener {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private JLabel title;
	private JLabel status;
	private JLabel reason;
	private JCheckBox validate;
	private JLabel feedback;
	private TaskListener listener;
	private String volunteer;
	private String user;
	
	/**
	 * 
	 * @param Every fields from the database
	 *
	 */
	public VolunteerTask(int id, String title, String username, String volunteer, String status, String reason, String feedback,String user) {
		super(new BorderLayout());
		this.volunteer = volunteer;
		this.user=user;
		this.id = id;
		this.username=username;
		this.title=new JLabel(title);
		this.status=new JLabel(status);
		this.reason=new JLabel(reason);
		this.validate = new JCheckBox();
		this.feedback=new JLabel(feedback);
		JPanel center = new JPanel();
		
		center.add(this.title);
		center.add(new JLabel(" | "));
		center.add(this.status);
		center.add(new JLabel(" | "));
		
		validate.setSelected(!volunteer.isEmpty());
		
		if(status.equals("Wait validation")) {
			center.add(validate);
			validate.addItemListener(this);
		}
		
		else if(status.equals("In progress")) {
			this.status.setForeground(Color.BLUE);
			validate.setEnabled(false);
			center.add(this.validate);
		}
		
		else if(status.equals("End")) {
			Color green = new Color (50, 129, 50);
			this.status.setForeground(green);
			validate.setEnabled(false);
			center.add(this.validate);
			center.add(new JLabel(" | "));
			center.add(this.feedback);
		}
		
		else if(status.equals("Cancel")) {
			this.status.setForeground(Color.RED);
			center.add(this.reason);
			validate.setEnabled(false);
			center.add(this.validate);
		}
		
		add(center);
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getUsername()));
	}

	/*
	 * Task implementation
	 */
	
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
		return this.status.getText();
	}

	@Override
	public String getReason() {
		return this.reason.getText();
	}

	@Override
	public String getVolunteer() {
		return this.volunteer;
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
	
	/*
	 * ItemListener implementation
	 */

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			System.out.println(user + " s'est ajouté à une tâche");
			volunteer=user;
			listener.taskChanged(this);
		} else {
			System.out.println(user + " s'est retirer d'une tâche");
			volunteer = "";
			listener.taskChanged(this);
		}
	}
}
