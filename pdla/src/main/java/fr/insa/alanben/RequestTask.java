package  fr.insa.alanben;

import java.awt.Component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import se.his.it401g.todo.Task;
import se.his.it401g.todo.TaskInputListener;
import se.his.it401g.todo.TaskListener;

public class RequestTask extends JPanel implements KeyListener, Task {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The editable text field. 
	 */
	private JTextField text;
	
	/**
	 * The non editable text label.
	 */
	private JLabel textLabel;
	
	/**
	 * Check box holding the completion status. 
	 */
	JCheckBox completed = new JCheckBox();
	
	/**
	 * The task listener used for reporting changes to the main application. 
	 */
	
	private JLabel volunteer = new JLabel("");
	private JLabel statue = new JLabel("En cours");
	private JLabel reason = new JLabel("");
	private JSlider slider = new JSlider(0,10);
	private JLabel title = new JLabel("");
	
	private TaskListener listener;
	
	
	private String userName;
	
	/**
	 * This is the constructor for the task, initiating the GUI component for the task. Several listeners are used to react to various events in the GUI.  
	 */
	public RequestTask(String userName) {
		super(new BorderLayout());
		this.userName = userName;
		this.text = new JTextField("New request",15);
		this.textLabel = new JLabel();
		this.textLabel.setVisible(false);
		JPanel center = new JPanel();
		center.add(text);
		center.add(textLabel);
		center.add(volunteer);
		center.add(statue);
		center.add(reason);
		center.add(slider);
		add(center);
		
		TaskInputListener inputListener = new TaskInputListener(this, text, textLabel);
		this.text.addKeyListener(inputListener);
		this.textLabel.addMouseListener(inputListener);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(inputListener);
		
		add(completed,BorderLayout.WEST);
		completed.addItemListener(inputListener);
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getTaskType()));
	}
	
	public RequestTask(String userName, String Titre, String Volunteer, String Statue, String Reason, int note) {
		super(new BorderLayout());
		this.userName = userName;
		this.text = new JTextField("New request",15);
		this.textLabel = new JLabel();
		this.textLabel.setVisible(false);
		JPanel center = new JPanel();
		//center.add(text);
		//center.add(textLabel);
		title.setText(Titre);
		center.add(title);
		volunteer.setText(Volunteer);
		center.add(volunteer);
		statue.setText(Statue);
		center.add(statue);
		reason.setText(Reason);
		center.add(reason);
		slider.setValue(note);
		center.add(slider);
		add(center);
		
		TaskInputListener inputListener = new TaskInputListener(this, text, textLabel);
		this.text.addKeyListener(inputListener);
		this.textLabel.addMouseListener(inputListener);
		
		JButton remove = new JButton("Remove");
		add(remove,BorderLayout.EAST);
		remove.addActionListener(inputListener);
		
		add(completed,BorderLayout.WEST);
		completed.addItemListener(inputListener);
		
		setMaximumSize(new Dimension(1000,50));
		setBorder(new TitledBorder(getTaskType()));
	}

	@Override
	public String getText() {
		return text.getText();
	}

	@Override
	public String getTaskType() {
		return this.userName;
	}

	@Override
	public void setTaskListener(TaskListener t) {
		listener = t;		
	}

	@Override
	public TaskListener getTaskListener() {
		return listener;
	}

	@Override
	public boolean isComplete() {
		return completed.isSelected();
	}

	@Override
	public Component getGuiComponent() {
		// Since this class extends JPanel, it is itself a GUI component, and thus we can return "this". 
		return this;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
