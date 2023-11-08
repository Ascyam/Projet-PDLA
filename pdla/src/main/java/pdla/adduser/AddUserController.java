package pdla.adduser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the adduser view.
 *  
 * @author Benjamin Zolver
 * @param view LoginView : the interface with the user. 
 * @param model LoginModel : the model to communicate with the database. 
 * @version 1.0
 * */
public class AddUserController implements ActionListener{
	private AddUserView view;
	private AddUserModel model;
	
	public AddUserController(AddUserView view, AddUserModel model) {
		this.view=view;
		this.model=model;
		view.getButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.addUser(view.getValue());
		view.closeWindow();
	}
}
