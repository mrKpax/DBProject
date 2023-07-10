
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Op1
{
	public static void Query1()
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
		
		System.out.println("Inserire le informazioni relative alla partita.");
		System.out.println("Codice Squadra che disputa la partita:");
		String codice = sc.nextLine();
		System.out.println("ID Partita:");
		int ID = sc.nextInt();
		sc.nextLine();
		System.out.println("Data (aaaa-mm-gg):");
		String dataPartita = sc.nextLine();
		System.out.println("Avversario:");
		String avversario = sc.nextLine();
		System.out.println("Reti Squadra 1:");
		int retis1 = sc.nextInt();
		sc.nextLine();
		System.out.println("Reti Squadra 2:");
		int retis2 = sc.nextInt();
		sc.nextLine();
		System.out.println("ID Competizione:");
		String competizione = sc.nextLine();
		System.out.println("CF Arbitro:");
		String arbitro = sc.nextLine();
		
		Statement query;
		try
		{
			query = con.createStatement();
			query.execute("INSERT INTO Partita (ID, DataPartita, Avversario, RetiS1, RetiS2, Arbitro, Squadra)\n"
					+ "VALUES ("+ID+", '"+dataPartita+"', '"+avversario+"', "+retis1+", "+retis2+", '"+arbitro+"', '"+codice+"');");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Statement query2;
		if (retis1 > retis2)
		{
			try
			{
				query2 = con.createStatement();
				query2.execute("UPDATE Squadra AS s\n"
						+ "JOIN Partita AS p ON p.Squadra = s.Codice\n"
						+ "SET s.Vittorie = s.Vittorie + 1\n"
						+ "WHERE s.Codice = '"+codice+"';");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (retis1 == retis2)
		{
			try
			{
				query2 = con.createStatement();
				query2.execute("UPDATE Squadra AS s\n"
						+ "JOIN Partita AS p ON p.Squadra = s.Codice\n"
						+ "SET s.Pareggi = s.Pareggi + 1\n"
						+ "WHERE s.Codice = '"+codice+"';");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (retis1 < retis2)
		{
			try
			{
				query2 = con.createStatement();
				query2.execute("UPDATE Squadra AS s\n"
						+ "JOIN Partita AS p ON p.Squadra = s.Codice\n"
						+ "SET s.Sconfitte = s.Sconfitte + 1\n"
						+ "WHERE s.Codice = '"+codice+"';");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		Statement query3;
		if(competizione.equals("SA"))
		{
			try
			{
				query3 = con.createStatement();
				query3.execute("INSERT INTO PertinenzaClassificata (PartitaID, ClassificataID)"
						+ "    VALUES ('"+ID+"', '"+competizione+"');");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (competizione.equals("CI") || competizione.equals("UCL") || competizione.equals("UEL") || competizione.equals("ECL"))
		{
			try
			{
				query3 = con.createStatement();
				query3.execute("INSERT INTO PertinenzaTorneo (PartitaID, TorneoID)"
						+ "    VALUES ('"+ID+"', '"+competizione+"');");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
