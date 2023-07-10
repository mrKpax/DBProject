
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op18 {
    public static void Query18() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/databaseFIGC";
            String username = "root";
            String pwd = "pwd";

            con = DriverManager.getConnection(url, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Statement query;
        try {
        	query = con.createStatement();
            String sql = "SELECT Squadra.Nome AS NomeSquadra, Allenatore.Nome AS NomeAllenatore, " +
                    "Allenatore.Cognome AS CognomeAllenatore, Allenatore.Nazionalita " +
                    "FROM Squadra " +
                    "JOIN ContrattoAllenatore ON Squadra.Codice = ContrattoAllenatore.SquadraCodice " +
                    "JOIN Allenatore ON ContrattoAllenatore.AllenatoreCF = Allenatore.CF";

            ResultSet result = query.executeQuery(sql);
            
            while (result.next()) {
                String nomeSquadra = result.getString("NomeSquadra");
                String nomeAllenatore = result.getString("NomeAllenatore");
                String cognomeAllenatore = result.getString("CognomeAllenatore");
                String nazionalita = result.getString("Nazionalita");
                
                System.out.printf("%-15s%-25s%-25s%s%n", nomeSquadra, nomeAllenatore, cognomeAllenatore, nazionalita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
