package pdla.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pdla.user.*;
import pdla.adduser.AddUserController;
import pdla.adduser.AddUserModel;
import pdla.adduser.AddUserView;
import pdla.medecin.*;
import pdla.volunteer.*;

/**
 * Controller for the login view.
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @param view LoginView : the interface with the user. 
 * @param model LoginModel : the model to communicate with the database. 
 * @version 1.0
 * */
public class LoginController implements ActionListener, KeyListener {
	private LoginView view;
	private LoginModel model;
	
	/**
	 * Constructor.
	 * @param view LoginView : the interface with the user. 
	 * @param model LoginModel : the model to communicate with the database. 
	 * */
	public LoginController(LoginView view, LoginModel model) {
		this.view=view;
		this.model=model;
		view.getValidationButton().addActionListener(this);
		view.getAddUserButton().addActionListener(this);
		view.getTextField().addKeyListener(this);
	}
	
	/**
	 * Try the connection according to inputs of the user. 
	 * Create a controller according to the role of the user.
	 * */
	private void connection() {
		int userID = model.tryConnection(view.getValue());
		if (userID != 0) {
			switch(model.getRole(userID)){
		       case "User": 
		           new UserController(userID);
		           break;
		       case "Medecin":
		    	   new MedecinController(userID);
		           break;		   
		       case "Volunteer":
		    	   new VolunteerController(userID);
		           break;
		   }

		}else {
			view.error();
		}
	}
	
	/**
	 * Create the controller for the adduser page. 
	 * */
	private void addUser() {
		new AddUserController(new AddUserView(),new AddUserModel());
	}

	/**
	 * Action when a button is clicked.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getValidationButton()) {
			connection();
		}else if(e.getSource() == view.getAddUserButton()) {
			addUser();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Action when the key "enter" is clicked.
	 * <p>Works only for the field password
	 * </p>
	 * */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			connection();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
