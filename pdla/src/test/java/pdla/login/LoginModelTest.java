package pdla.login;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LoginModelTest {

	@Test
	void test() {
		LoginModel login = new LoginModel();
		ArrayList<String> info = new ArrayList<String>();
		info.add("test");info.add("password");
		assertNotEquals(0, login.tryConnection(info));
	}

}
