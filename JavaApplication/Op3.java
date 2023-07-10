
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op3
{
	public static void Query3()
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
		
		System.out.println("Dati calciatore.");
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
		System.out.println("Ruolo:");
		String ruolo = sc.nextLine();
		System.out.println("Valore di mercato:");
		int valore = sc.nextInt();
		sc.nextLine();
		System.out.println("Costo d'acquisto:");
		int costo = sc.nextInt();
		sc.nextLine();
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
			query.execute("INSERT INTO Calciatore (CF, Nome, Cognome, DataNascita, Nazionalita, Ruolo, Valore, Costo)\n"
					+ "VALUES ('"+CF+"', '"+nome+"', '"+cognome+"', '"+dataNascita+"', '"+nazionalita+"', "
					+ " '"+ruolo+"', "+valore+", "+costo+");");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		try
		{
			query2 = con.createStatement();
			query2.execute("INSERT INTO ContrattoCalciatore (SquadraCodice, CalciatoreCF, DataScadenza, Retribuzione)\n"
					+ "VALUES ('"+squadra+"', '"+CF+"', '"+dataScadenza+"', "+retribuzione+");");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query3;
		try
		{
			query3 = con.createStatement();
			query3.execute("UPDATE Squadra AS s"
					+ "	JOIN ContrattoCalciatore AS i ON s.Codice = i.SquadraCodice"
					+ "	JOIN Calciatore AS c ON i.CalciatoreCF = c.CF"
					+ "	SET s.ValoreRosa = s.ValoreRosa + c.Valore"
					+ "	WHERE s.Codice = '"+squadra+"' AND c.CF = '"+CF+"';");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}