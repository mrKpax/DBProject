
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op11 {
    public static void Query11() {
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
            String sql = "SELECT Squadra.Nome, COUNT(*) AS Extracomunitari\n"
            		+ "FROM Calciatore \n"
            		+ "	JOIN ContrattoCalciatore ON Calciatore.CF = ContrattoCalciatore.CalciatoreCF\n"
            		+ "	JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice\n"
            		+ "WHERE Nazionalita NOT IN ('Austria', 'Belgio', 'Bulgaria', 'Cipro', 'Croazia', 'Danimarca', 'Estonia', \n"
            		+ "	'Finlandia', 'Francia', 'Germania', 'Grecia', 'Irlanda', 'Italia', 'Lettonia', 'Lituania', \n"
            		+ "    'Lussemburgo', 'Malta', 'Olanda', 'Polonia', 'Portogallo', 'Repubblica Ceca', 'Romania', \n"
            		+ "    'Slovacchia', 'Slovenia', 'Spagna', 'Svezia')\n"
            		+ "GROUP BY Squadra.Nome;";

            ResultSet result = query.executeQuery(sql);
            
            System.out.println(String.format("%-15s | %-15s ", "Squadra", "Extracomunitari"));
            
            while (result.next()) {
            	String squadra = result.getString("Squadra.Nome");
                int extracomunitari = result.getInt("Extracomunitari");
                System.out.println(String.format("%-15s | %-3d ", squadra, extracomunitari));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 