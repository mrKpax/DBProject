
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op19 {
    public static void Query19() {
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
            String sql = "SELECT Squadra.Nome AS NomeSquadra, AVG(YEAR(CURDATE()) - YEAR(Calciatore.DataNascita)) AS EtaMediaAnni " +
                    "FROM Squadra " +
                    "JOIN ContrattoCalciatore ON Squadra.Codice = ContrattoCalciatore.SquadraCodice " +
                    "JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF " +
                    "GROUP BY Squadra.Nome";

            ResultSet result = query.executeQuery(sql);
            while (result.next()) {
                String nomeSquadra = result.getString("NomeSquadra");
                double etaMediaAnni = result.getDouble("EtaMediaAnni");

                System.out.printf("%-15s%.2f%n", nomeSquadra, etaMediaAnni);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
