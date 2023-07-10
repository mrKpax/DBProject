
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op17 {
    public static void Query17() {
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
            String sql = "SELECT St.Nome, GROUP_CONCAT(Sq.Nome) AS Squadre\n"
            		+ "FROM Squadra AS Sq\n"
            		+ "  JOIN Stadio AS St ON Sq.Stadio = St.Nome\n"
            		+ "GROUP BY St.Nome\n"
            		+ "HAVING COUNT(Sq.Codice) >= 2;";

            ResultSet result = query.executeQuery(sql);
            
            System.out.println("Nome Stadio\t\t\tSquadre");
            System.out.println("----------------------------------------");
            
            while (result.next()) {
                String nomeStadio = result.getString("St.Nome");
                String squadre = result.getString("Squadre");
                
                System.out.printf("%-30s%s%n", nomeStadio, squadre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
