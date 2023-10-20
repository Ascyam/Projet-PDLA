package fr.insa.alanben;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");
        System.out.println("---------------------------------------");
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_013"; // table details
        String username = "projet_gei_013"; // MySQL credentials
        String password = "thoo1Xoh";
        try {
            // Charger le pilote MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir une connexion à la base de données
            Connection connexion = DriverManager.getConnection(url, username, password);

            // Créer un objet Statement pour exécuter des requêtes SQL
            Statement statement = connexion.createStatement();

            // Exécuter des requêtes SQL, par exemple, une requête SELECT
            String sql = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(sql);

            // Traiter les données récupérées
            while (resultSet.next()) {
                String column1Value = resultSet.getString("FirstName");
                String column2Value = resultSet.getString("LastName");
                String column3Value = resultSet.getString("Role");

                System.out.println("Colonne 1: " + column1Value);
                System.out.println("Colonne 2: " + column2Value);
                System.out.println("Colonne 3: " + column3Value);
                System.out.println("------------------");
            }

            // Fermer la connexion et les ressources
            resultSet.close();
            statement.close();
            connexion.close();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}