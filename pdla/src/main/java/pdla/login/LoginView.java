package pdla.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * View for the login view. This class create a interface where the user can interact.
 *  
 * @author Benjamin Zolver
 * @version 1.0
 * */
public class LoginView {
	
	private JFrame frame = new JFrame("Login");
	private JPanel panelcenter = new JPanel(new GridLayout(2,1));
	private JPanel panelbottom = new JPanel();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JLabel userlabel = new JLabel("Username :");
	private JLabel passlabel = new JLabel("Password :");
	private JButton validate = new JButton("Validate");
	private JLabel label = new JLabel("Welcome");
	private JButton addUser = new JButton("Add user");
	
	/**
     * Constructor.
     * */
	public LoginView() {
		CreateScreen();
	}

	/**
     * Create the screen with fields and buttons.
     * */
	private void CreateScreen() {
		password.setEchoChar('*');
		panelcenter.add(userlabel);
		panelcenter.add(username);
		panelcenter.add(passlabel);
		panelcenter.add(password);
		panelbottom.add(addUser);
		panelbottom.add(validate);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label, BorderLayout.NORTH);
		frame.add(panelcenter, BorderLayout.CENTER);
		frame.add(panelbottom, BorderLayout.SOUTH);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
     * Return the button validate.
     * @return button validate.
     * */
	public JButton getValidationButton() {
		return validate;
	}
	
	/**
     * Return the button validate.
     * @return button add user.
     * */
	public JButton getAddUserButton() {
		return addUser;
	}
	
	/**
     * Return the password field. Can be used for a action listener.
     * @return password fieldValidate.
     * */
	public JPasswordField getTextField() {
		return password;
	}
	
	/**
     * Get the value entered by the user in the different fields.
     * @return ArrayList of inputs.
     * */
	public ArrayList<String> getValue() {
		String inputUsername = username.getText();
        String inputPassword = String.valueOf(password.getPassword());
		ArrayList<String> list = new ArrayList<String>();
		list.add(inputUsername);list.add(inputPassword);
		return list;
	}
	
	/**
     * Print error on the screen.
     * */
	public void error() {
		label.setForeground(Color.RED);
    	label.setText("Wrong");
	}
}