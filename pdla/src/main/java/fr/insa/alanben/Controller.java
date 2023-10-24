package  fr.insa.alanben;

import java.sql.*;

public class Controller {
	
	private int userId;
	
	
	private void setUser() {
		String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013"; // table details
        String usernamedb = "projet_gei_013"; // MySQL credentials
        String passworddb = "thoo1Xoh";
        try {
            Connection connexion = DriverManager.getConnection(url, usernamedb, passworddb);
            Statement statement = connexion.createStatement();
            String sql = "SELECT Role FROM Login WHERE userID="+ String.valueOf(userId); 
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()) {
            	while(resultSet.next()) {
            		String value = resultSet.getString(1);
	            	if(value.contentEquals("User")) {
	            		new UserScreen(userId);
	            	}else if(value.contentEquals("Medecin")) {
	            		new MedecinScreen(userId);
	            	}else {
	            		new VolunteerScreen(userId);
	            	}
            	}
            }
        }catch (SQLException e) {
	            System.out.println(e);
	            e.printStackTrace();
	        }
	}
	
	public Controller(int userID) {
		this.userId = userID;
		setUser();
	}
}