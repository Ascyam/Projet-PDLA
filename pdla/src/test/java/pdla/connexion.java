package pdla;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

import pdla.database.DatabaseCommunication;
import pdla.task.Task;
import pdla.task.UserTask;

class DatabaseTest {
	
	@Test
	void setUsernameDBTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		database.setUsernameDB(1);
		assertEquals("Benjamin Zolver", database.userName);
		database.setUsernameDB(2);
		assertNotEquals("BenjaminZolver", database.userName);
	}
	
	@Test
	void addTaskDBTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		try {
			database.addTaskDB("test");
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void getTaskDBTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		try {
			database.addTaskDB("test");
			List<Task> list = database.getTaskDB(UserTask.class);
			assertEquals("test", list.get(list.size()-1).getTitle());
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	void removeTaskDBTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		try {
			List<Task> list = database.getTaskDB(UserTask.class);
			database.removeTaskDB(list.get(list.size() - 1).getID());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void changeTaskStringDBTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		database.addTaskDB("test");
		List<Task> list = database.getTaskDB(UserTask.class);
		
		database.changeTaskStringDB("title", "testtest", list.get(list.size() - 1).getID());
		list = database.getTaskDB(UserTask.class);
		assertEquals("testtest", list.get(list.size()-1).getTitle());
		
		database.changeTaskStringDB("volunteer", "test", list.get(list.size() - 1).getID());
		list = database.getTaskDB(UserTask.class);
		assertEquals("test", list.get(list.size()-1).getVolunteer());
		
		database.removeTaskDB(list.get(list.size() - 1).getID());
	}
	
	@Test
	void getRoleTest() {
		DatabaseCommunication database = new DatabaseCommunication(1);
		database.addTaskDB("test");
		List<Task> list = database.getTaskDB(UserTask.class);
		
		assertEquals("User", database.getRole(1));
	
		database.removeTaskDB(list.get(list.size() - 1).getID());
	}
}
