
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op20 {
    public static void Query20() {
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
        System.out.println("Codice fiscale Calciatore:");
        String cf = sc.nextLine();
        
        Statement query;
        try {
            query = con.createStatement();
            String sql = "DELETE FROM ContrattoCalciatore WHERE CalciatoreCF = '"+cf+"'";

            int rowsDeleted = query.executeUpdate(sql);
            if (rowsDeleted == 1)
            	System.out.println("Eliminazione avvenuta.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
