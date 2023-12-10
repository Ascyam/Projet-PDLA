package pdla.adduser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * View for the adduser screen. This class display the connection interface for the user. 
 *  
 * @author Alan Dutems, Benjamin Zolver
 * @version 1.0
 * */
public class AddUserView {
	private JFrame frame = new JFrame("Add user");
	private JPanel panelcenter = new JPanel(new GridLayout(4,1));
	private JPanel panelbottom = new JPanel();
	private JTextField firstname = new JTextField();
	private JTextField lastname = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JLabel userlabel = new JLabel("Firstname :");
	private JLabel lastnamelabel = new JLabel("Lastname :");
	private JLabel passlabel = new JLabel("Password :");
	private String[] optionsToChoose = {"User", "Medecin", "Volunteer"};
	private JComboBox<String> role = new JComboBox<>(optionsToChoose);
	private JLabel label = new JLabel("Add User");
	private JButton validate = new JButton("Validate");
	
	/**
     * Constructor.
     * */
	public AddUserView() {
		CreateScreen();
	}
	
	/**
	 * Return values in the differents fields.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getValue() {
		if(!(firstname.getText().isEmpty() && lastname.getName().isEmpty() && String.valueOf(password.getPassword()).isEmpty())) {
			String inputUsername = firstname.getText();
			String inputLastname = lastname.getText();
	        String inputPassword = String.valueOf(password.getPassword());
	        String inputRole = (String) role.getSelectedItem();
			ArrayList<String> list = new ArrayList<String>();
			list.add(inputUsername);list.add(inputLastname);list.add(inputPassword);list.add(inputRole);
			return list;
		}else {
			return null;
		}
		
	}
	
	/**
	 * Return the validation button.
	 * @return JButton validation.
	 */
	public JButton getButton() {
		return validate;
	}
	
	public void closeWindow() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	/**
     * Create the screen with fields and buttons.
     * */
	private void CreateScreen() {
		password.setEchoChar('*');
		panelcenter.add(userlabel);
		panelcenter.add(firstname);
		panelcenter.add(lastnamelabel);
		panelcenter.add(lastname);
		panelcenter.add(passlabel);
		panelcenter.add(password);
		panelcenter.add(role);
		panelbottom.add(validate);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label, BorderLayout.NORTH);
		frame.add(panelcenter, BorderLayout.CENTER);
		frame.add(panelbottom, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}
