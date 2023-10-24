package  fr.insa.alanben;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login {
	
	private JFrame frame = new JFrame("Login");
	
	private JPanel panelcenter = new JPanel(new GridLayout(2,2));
	private JPanel panelbottom = new JPanel();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JLabel userlabel = new JLabel("Username :");
	private JLabel passlabel = new JLabel("Password :");
	private JButton Validate = new JButton("Validate");
	private JLabel label = new JLabel("Welcome");
	
	private void CreateScreen() {
		password.setEchoChar('*');
		panelcenter.add(userlabel);
		panelcenter.add(username);
		panelcenter.add(passlabel);
		panelcenter.add(password);
		panelbottom.add(Validate);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label, BorderLayout.NORTH);
		frame.add(panelcenter, BorderLayout.CENTER);
		frame.add(panelbottom, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		Validate.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	Validate();
	        }});
	}
	
	private void Validate() {
			String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013"; // table details
	        String usernamedb = "projet_gei_013"; // MySQL credentials
	        String passworddb = "thoo1Xoh";
	        try {
	            // Établir une connexion à la base de données
	            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
	
	            // Créer un objet Statement pour exécuter des requêtes SQL
	            Statement statement = connexion.createStatement();
	
	            // Exécuter des requêtes SQL, par exemple, une requête SELECT
	            String inputUsername = username.getText();
	            String inputPassword = String.valueOf(password.getPassword());
	            String sql = "SELECT * FROM Login WHERE username=\"" + inputUsername + "\" AND password=\"" + inputPassword + "\""; 
	            ResultSet resultSet = statement.executeQuery(sql);
	            
	            // Traiter les données récupérées
	            if(resultSet.isBeforeFirst()) {
	            	resultSet.next();
	            	int value = resultSet.getInt(1);
	            	new Controller(value);
	            	
	            	resultSet.close();
	                statement.close();
	                connexion.close();
	            }else {
	            	label.setForeground(Color.RED);
	            	label.setText("Wrong");
	            }
	            
	        } catch (SQLException e) {
	            System.out.println(e);
	            e.printStackTrace();
	        }
		
	}
	
	public Login() {
		CreateScreen();
	}

    public static void main(String[] args) {
    	new Login();
    }
}