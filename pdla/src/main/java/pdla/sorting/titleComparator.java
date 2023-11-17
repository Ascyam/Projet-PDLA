package pdla.sorting;

import java.util.Comparator;
import pdla.task.Task;

public class titleComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		if (t1.getTitle().equals(t2.getTitle())) {
			return 0;
		} else if(t1.getTitle().compareTo(t2.getTitle())>0){
			return 1;
		} else {
			return -1;
		}
	}

}