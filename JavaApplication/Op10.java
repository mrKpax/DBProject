import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Op10 {
    public static void Query10() {
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
            String sql = "SELECT Squadra.Nome\n"
            		+ "FROM Squadra\n"
            		+ "WHERE Squadra.Codice IN (\n"
            		+ "    SELECT Squadra\n"
            		+ "    FROM Palmares\n"
            		+ "    WHERE (NomeTrofeo, Stagione) IN (\n"
            		+ "        SELECT NomeTrofeo, Stagione\n"
            		+ "        FROM Palmares\n"
            		+ "        WHERE NomeTrofeo = 'Serie A'\n"
            		+ "        UNION\n"
            		+ "        SELECT NomeTrofeo, Stagione\n"
            		+ "        FROM Palmares\n"
            		+ "        WHERE NomeTrofeo = 'Coppa Italia'\n"
            		+ "    )\n"
            		+ ")\n"
            		+ "AND Squadra.Codice IN (\n"
            		+ "    SELECT Squadra\n"
            		+ "    FROM Palmares\n"
            		+ "    WHERE (NomeTrofeo, Stagione) IN (\n"
            		+ "        SELECT NomeTrofeo, Stagione\n"
            		+ "        FROM Palmares\n"
            		+ "        WHERE NomeTrofeo = 'Champions League'\n"
            		+ "    )\n"
            		+ ");";

            ResultSet result = query.executeQuery(sql);
            System.out.println("Squadre vincitrici di Serie A o Coppa Italia, e Champions League:");
            while (result.next()) {
                String squadra = result.getString("Squadra.Nome");
                System.out.println(squadra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}