package pdla.task;


import java.awt.Component;

/**  
 * 
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
public interface Task {
	
	/**
	 * @return the ID of this task. 
	 */
	public int getID();
	
	/**
	 * @return the first and last name of the creator of this task. 
	 */
	public String getUsername();

	/**
	 * @return the title of this task. 
	 */
	public String getTitle();
	
	/**
	 * @return the status of the task.
	 */
	public String getStatus();
	
	/**
	 * @return the reason why this task has been canceled. 
	 */
	public String getReason();
	
	/**
	 * @return the volunteer of this task. 
	 */
	public String getVolunteer();
	
	/**
	 * @return the note of this task. 
	 */
	public String getFeedback();
	
	/**
	 * Sets the task listener on which this task reports various events. 
	 * @param t the listener to use.
	 */
	public void setTaskListener(TaskListener t);
	
	/**
	 * @return the task listener currently used. 
	 */
	public TaskListener getTaskListener();
		
	/**
	 * @return the graphical user interface component representing this task. 
	 */
	public Component getGuiComponent();
}