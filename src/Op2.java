import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op2 {
    public static void Query2() {
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
            ResultSet result = query.executeQuery("SELECT Squadra.Nome,\n"
            		+ "   Squadra.Vittorie AS NVittorie,\n"
            		+ "   Squadra.Pareggi AS NPareggi,\n"
            		+ "   Squadra.Sconfitte AS NSconfitte\n"
            		+ "FROM Squadra;");
            
            System.out.println(String.format("%-15s | %-10s | %-10s | %-10s ", "Squadra", "Vittorie", "Pareggi", "Sconfitte"));
            
            while (result.next()) {
            	String squadra = result.getString("Squadra.Nome");
                int vittorie = result.getInt("NVittorie");
                int pareggi = result.getInt("NPareggi");
                int sconfitte = result.getInt("NSconfitte");

                System.out.println(String.format("%-15s | %-10d | %-10d | %-10d", squadra, vittorie, pareggi, sconfitte));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}