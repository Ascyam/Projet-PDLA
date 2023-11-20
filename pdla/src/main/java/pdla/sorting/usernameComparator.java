package pdla.sorting;

import java.util.Comparator;
import pdla.task.Task;

/**
 * Sorting task based on the username.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class usernameComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		if (t1.getUsername().equals(t2.getUsername())) {
			return 0;
		} else if(t1.getUsername().compareTo(t2.getUsername())>0){
			return -1;
		} else {
			return 1;
		}
	}

}
