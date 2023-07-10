
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Op16 {
    public static void Query16() {
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
            String sql = "SELECT Calciatore.Nome, Calciatore.Cognome, " +
                "CASE " +
                "WHEN Calciatore.Valore > Calciatore.Costo THEN 'Plusvalenza' " +
                "WHEN Calciatore.Valore < Calciatore.Costo THEN 'Minusvalenza' " +
                "ELSE 'Nessuna variazione' " +
                "END AS PlusMinus, " +
                "(Calciatore.Valore - Calciatore.Costo) AS ValoreDiff " +
                "FROM Calciatore " +
                "JOIN ContrattoCalciatore ON Calciatore.CF = ContrattoCalciatore.CalciatoreCF " +
                "JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice " +
                "WHERE Squadra.Codice = '"+cod+"'";
            
            ResultSet result = query.executeQuery(sql);
            
            System.out.println("Nome\t\t\tCognome\t\tPlusMinus\t\tValoreDiff");
            System.out.println("---------------------------------------------------------------------------------");
            DecimalFormat df = new DecimalFormat("#,##0.00");
            
            while (result.next()) {
                String nome = result.getString("Nome");
                String cognome = result.getString("Cognome");
                String plusMinus = result.getString("PlusMinus");
                double valoreDiff = result.getDouble("ValoreDiff");
                
                String formattedValoreDiff = df.format(valoreDiff);
                
                System.out.printf("%-20s%-20s%-20s%-20s%n", nome, cognome, plusMinus, formattedValoreDiff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 