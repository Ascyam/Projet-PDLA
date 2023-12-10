package pdla.sorting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import pdla.task.Task;
import pdla.task.UserTask;

class titleComparatorTest {

	@Test
	void test() {
		List<Task> list = new ArrayList<>();
		list.add(new UserTask(0, "", "b", "", "", "", "", ""));
		list.add(new UserTask(0, "", "a", "", "", "", "", ""));
		list.add(new UserTask(0, "", "c", "", "", "", "", ""));
		
		List<Task> listOK = new ArrayList<>();
		listOK.add(new UserTask(0, "", "c", "", "", "", "", ""));
		listOK.add(new UserTask(0, "", "b", "", "", "", "", ""));
		listOK.add(new UserTask(0, "", "a", "", "", "", "", ""));
		
		Collections.sort(list, new titleComparator());
		
		for(int i=0; i<list.size(); i++ ) {
			assertEquals(list.get(i).getStatus(), listOK.get(i).getStatus());
		}
	}

}
