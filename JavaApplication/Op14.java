
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Op14 {
    public static void Query14() {
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
        String cod = sc.nextLine();
        
        Statement query;
        try {
            query = con.createStatement();
            String sql = "SELECT Calciatore.Nome, Calciatore.Cognome, Calciatore.Ruolo, Calciatore.Nazionalita " +
                "FROM Squadra " +
                "JOIN ContrattoCalciatore ON Squadra.Codice = ContrattoCalciatore.SquadraCodice " +
                "JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF " +
                "WHERE Squadra.Codice = '"+cod+"'";

            ResultSet result = query.executeQuery(sql);
            System.out.println("Calciatori della squadra " + cod + ":");
            while (result.next()) {
                String nome = result.getString("Nome");
                String cognome = result.getString("Cognome");
                String ruolo = result.getString("Ruolo");
                String nazionalita = result.getString("Nazionalita");
                System.out.println(nome + " " + cognome + " (" + ruolo + ") - " + nazionalita);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
