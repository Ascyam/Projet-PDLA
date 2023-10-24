package fr.insa.alanben;

import java.util.Comparator;
import se.his.it401g.todo.Task;

public class AlphabeticComparator implements Comparator<Task> {
	
	@Override
	public int compare(Task t1, Task t2) {
		if (t1.getText().equals(t2.getText())) {
			return 0;
		} else if(t1.getText().compareTo(t2.getText())>0){
			return -1;
		} else {
			return 1;
		}
	}
}
