package  fr.insa.alanben;

import java.util.Comparator;
import se.his.it401g.todo.Task;

public class CheckComparator implements Comparator<Task> {
	
	@Override
	public int compare(Task t1, Task t2) {
		if(!t1.isComplete() && t2.isComplete()) {
			return 1;
		}
		if(t1.isComplete() && !t2.isComplete()) {
			return -1;
		}
		return 0;
	}

}
