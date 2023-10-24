package  fr.insa.alanben;

import java.util.Comparator;
import se.his.it401g.todo.Task;

public class TypeComparator implements Comparator<Task>{
	
	@Override
	public int compare(Task t1, Task t2) {
		if (t1.getTaskType().equals(t2.getTaskType())){
			return 0;
		}else if(t1.getTaskType().compareTo(t2.getTaskType())>0){
			return 1;
		}else {
			return -1;
		}
	}
	
	
	
}
