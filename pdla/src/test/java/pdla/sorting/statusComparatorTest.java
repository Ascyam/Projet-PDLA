package pdla.sorting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import pdla.task.Task;
import pdla.task.UserTask;

class statusComparatorTest {

	@Test
	void test() {
		List<Task> list = new ArrayList<>();
		list.add(new UserTask(0, "", "", "", "c", "", "", ""));
		list.add(new UserTask(0, "", "", "", "a", "", "", ""));
		list.add(new UserTask(0, "", "", "", "b", "", "", ""));
		
		List<Task> listOK = new ArrayList<>();
		listOK.add(new UserTask(0, "", "", "", "c", "", "", ""));
		listOK.add(new UserTask(0, "", "", "", "b", "", "", ""));
		listOK.add(new UserTask(0, "", "", "", "a", "", "", ""));
		
		Collections.sort(list, new statusComparator());
		
		for(int i=0; i<list.size(); i++ ) {
			assertEquals(list.get(i).getStatus(), listOK.get(i).getStatus());
		}
	}

}
