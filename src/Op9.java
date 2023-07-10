import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op9 {
    public static void Query9() {
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
        System.out.println("Codice Squadra:");
        String codice = sc.nextLine();

        System.out.println("Nuova Posizione:");
        String posizione = sc.nextLine();
        
        Statement query;
        try {
        	query = con.createStatement();
            String sql = "UPDATE PartecipazioneClassificata " +
                    "SET Posizione = '"+posizione+"' " +
                    "WHERE SquadraCodice = '"+codice+"' AND ClassificataID = 'SA'";

            int rowsAffected = query.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Aggiornamento della posizione della squadra eseguito con successo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}