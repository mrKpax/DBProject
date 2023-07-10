
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op5
{
	public static void Query5()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/databaseFIGC";
			String username = "root";
			String pwd = "pwd";
			
			con = DriverManager.getConnection(url, username, pwd);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dati allenatore.");
		System.out.println("Codice Fiscale:");
		String CF = sc.nextLine();
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Cognome:");
		String cognome = sc.nextLine();
		System.out.println("Data di nascita (aaaa-mm-gg):");
		String dataNascita = sc.nextLine();
		System.out.println("Nazionalità:");
		String nazionalita = sc.nextLine();
		System.out.println("\nDati contratto.");
		System.out.println("Codice squadra:");
		String squadra = sc.nextLine();
		System.out.println("Data scadenza contratto (aaaa-mm-gg):");
		String dataScadenza = sc.nextLine();
		System.out.println("Retribuzione annuale:");
		int retribuzione = sc.nextInt();
		
		Statement query;
		try
		{
			query = con.createStatement();
			query.execute("INSERT INTO Allenatore (CF, Nome, Cognome, DataNascita, Nazionalita)\n"
					+ "VALUES ('"+CF+"', '"+nome+"', '"+cognome+"', '"+dataNascita+"', '"+nazionalita+"');");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		try
		{
			query2 = con.createStatement();
			query2.execute("INSERT INTO ContrattoAllenatore (SquadraCodice, AllenatoreCF, DataScadenza, Retribuzione)\n"
					+ "VALUES ('"+squadra+"', '"+CF+"', '"+dataScadenza+"', "+retribuzione+");");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}