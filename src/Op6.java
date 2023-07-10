
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op6 {
    public static void Query6() {
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
            ResultSet result = query.executeQuery("SELECT A.Nome, A.Cognome, COUNT(P.Arbitro) AS NumeroPartite\n"
            		+ "FROM Arbitro AS A\n"
            		+ "	LEFT JOIN Partita AS P ON A.CF = P.Arbitro\n"
            		+ "GROUP BY A.CF, A.Nome, A.Cognome;");
            while (result.next()) {
            	String nome = result.getString("A.Nome");
            	String cognome = result.getString("A.Cognome");
                int n = result.getInt("NumeroPartite");
                
                System.out.println(String.format("%-15s %-15s | %-5d", nome, cognome, n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
