
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op13 {
    public static void Query13() {
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
            String sql = "SELECT Squadra.Nome, Torneo.Nome, PartecipazioneTorneo.Fase "
            		+ "FROM Squadra "
            		+ "JOIN PartecipazioneTorneo ON Squadra.Codice = PartecipazioneTorneo.SquadraCodice "
            		+ "JOIN Torneo ON PartecipazioneTorneo.TorneoID = Torneo.ID "
            		+ "WHERE Squadra.Codice IN ("
            		+ "    SELECT SquadraCodice "
            		+ "    FROM PartecipazioneTorneo "
            		+ "    WHERE TorneoID IN ('UCL', 'UEL', 'ECL') "
            		+ ") AND PartecipazioneTorneo.TorneoID IN ('UCL', 'UEL', 'ECL');";

            ResultSet result = query.executeQuery(sql);
            System.out.format("%-20s %-20s %-20s\n", "Squadra", "Torneo", "Fase");
            System.out.println("----------------------------------------------------------");
            while (result.next()) {
                String squadra = result.getString("Squadra.Nome");
                String torneo = result.getString("Torneo.Nome");
                String fase = result.getString("PartecipazioneTorneo.Fase");
                System.out.format("%-20s %-20s %-20s\n", squadra, torneo, fase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
