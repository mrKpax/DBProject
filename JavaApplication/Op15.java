
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op15 {
    public static void Query15() {
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

        Scanner sc = new Scanner(System.in);
        System.out.println("Codice fiscale calciatore:");
        String CF = sc.nextLine();
        System.out.println("Nuova scadenza contratto (aaaa-mm-gg):");
        String scadenza = sc.nextLine();
        
        Statement query;
        try {
            query = con.createStatement();
            String sql = "UPDATE ContrattoCalciatore " +
                "JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF " +
                "SET ContrattoCalciatore.DataScadenza = '"+scadenza+"' " +
                "WHERE Calciatore.CF = '"+CF+"'";
            
            int rowsAffected = query.executeUpdate(sql);
            if (rowsAffected > 0)
            	System.out.println("Aggiornamento effettuato.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
