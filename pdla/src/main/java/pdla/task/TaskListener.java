package pdla.task;

/**
 * Listener interface. In this interface all the methods used by task listeners are defined. 
 * @author Benjamin Zolver
 * @version 1.0
 *
 */
public interface TaskListener {
	/**
	 * Called when a task is modified.
	 * 
	 * @param t is the modified task
	 */
	public void taskChanged(Task t);
	
	/**
	 * Called when a task is removed.
	 * 
	 * @param t is the removed task
	 */
	public void taskRemoved(Task t);
	
}