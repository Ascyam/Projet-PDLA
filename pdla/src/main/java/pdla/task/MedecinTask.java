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
	private JLabel note;
	private TaskListener listener;
	
	/**
	 * 
	 * @param Every fields from the database
	 *
	 */
	public MedecinTask(int id, String title, String username, String volunteer, String status, String reason, int note) {
		super(new BorderLayout());
		this.id = id;
		this.username=username;
		this.title=new JLabel(title);
		this.status.setSelectedItem(status);
		this.reasonField=new JTextField(reason,10);
		this.volunteer=new JLabel(volunteer);
		this.note=new JLabel();
		TaskInputListener inputListener = new TaskInputListener(this, reasonField);
		JPanel center = new JPanel();
		
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
			String noteString = Integer.toString(note)+"/10";
			this.note.setText(noteString);
			center.add(this.note);
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
		return this.title.getText();
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return (String) this.status.getSelectedItem();
	}

	@Override
	public String getReason() {
		// TODO Auto-generated method stub
		return this.reasonField.getText();
	}

	@Override
	public String getVolunteer() {
		// TODO Auto-generated method stub
		return this.volunteer.getText();
	}

	@Override
	public int getNote() {
		// TODO Auto-generated method stub
		return 0;
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
