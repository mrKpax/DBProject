
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op8 {
    public static void Query8() {
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
            ResultSet result = query.executeQuery("SELECT DISTINCT S.Nome\n"
            		+ "FROM Squadra AS S\n"
            		+ "JOIN Palmares AS P ON S.Codice = P.Squadra\n"
            		+ "WHERE P.Stagione = '2021-2022' OR P.Stagione = '2022-2023';");
            
            while (result.next()) {
            	String squadra = result.getString("S.Nome");
                System.out.println(squadra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}