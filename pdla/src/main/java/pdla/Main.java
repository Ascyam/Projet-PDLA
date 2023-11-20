package pdla;

import pdla.login.*;

/**
 * Main class to start the application. 
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @param
 * @version 1.0
 * */
public class Main {

	public static void main(String[] args){
		new LoginController(new LoginView(), new LoginModel());
	}

}
