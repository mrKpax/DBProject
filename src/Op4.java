import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

public class Op4 {
    public static void Query4() {
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
            ResultSet result = query.executeQuery("SELECT Nome, ValoreRosa\n"
            		+ "FROM Squadra;");
            
            System.out.println(String.format("%-15s | %-15s", "Squadra", "Valore"));
            
            while (result.next()) 
            {
            	String squadra = result.getString("Nome");
                double valore = result.getDouble("ValoreRosa");
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String valoreFormatted = decimalFormat.format(valore);
                System.out.println(String.format("%-15s | %-12s", squadra, valoreFormatted));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}