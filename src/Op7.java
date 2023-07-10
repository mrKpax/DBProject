import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op7 {
    public static void Query7() {
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
            ResultSet result = query.executeQuery("SELECT Squadra.Nome AS NomeSquadra, Calciatore.Nome AS NomeCalciatore, Calciatore.Cognome AS CognomeCalciatore " +
                "FROM ContrattoCalciatore " +
                "JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice " +
                "JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF " +
                "WHERE ContrattoCalciatore.DataScadenza <= '2024-06-01'");
            while (result.next()) {
                String nomeSquadra = result.getString("NomeSquadra");
                String nomeCalciatore = result.getString("NomeCalciatore");
                String cognomeCalciatore = result.getString("CognomeCalciatore");
                System.out.println("Squadra: " + nomeSquadra);
                System.out.println("Calciatore: " + nomeCalciatore + " " + cognomeCalciatore +"\n");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}