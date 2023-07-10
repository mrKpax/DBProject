
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op12 {
    public static void Query12() {
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
            String sql = "SELECT Squadra.Nome, PartecipazioneClassificata.Posizione " +
                    "FROM Squadra " +
                    "JOIN PartecipazioneClassificata ON Squadra.Codice = PartecipazioneClassificata.SquadraCodice " +
                    "JOIN Classificata ON PartecipazioneClassificata.ClassificataID = Classificata.ID " +
                    "WHERE Classificata.ID = 'SA' " +
                    "ORDER BY PartecipazioneClassificata.Posizione " +
                    "LIMIT 20";

            ResultSet result = query.executeQuery(sql);
            System.out.println("Squadra\t\tPosizione");
            while (result.next()) {
                String nomeSquadra = result.getString("Nome");
                int posizione = result.getInt("Posizione");
                System.out.println(String.format("%-15s\t%d", nomeSquadra, posizione));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}