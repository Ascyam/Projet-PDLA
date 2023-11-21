package pdla.sorting;

import java.util.Comparator;
import pdla.task.Task;

/**
 * Sorting task based on the status.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class statusComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		if (t1.getStatus().equals(t2.getStatus())) {
			return 0;
		} else if(t1.getStatus().compareTo(t2.getStatus())>0){
			return -1;
		} else {
			return 1;
		}
	}

}
