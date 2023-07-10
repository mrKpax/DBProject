import java.util.Scanner;

public class Menu 
{

	public static void main(String[] args) 
	{
		while (true)
		{
			Scanner sc = new Scanner(System.in);
					
			System.out.println("\nQuale operazione vuoi effettuare?");
			System.out.println("1. Registrare una nuova partita.");
			System.out.println("2. Visualizza il rendimento delle squadre: ottieni numero di vittorie, pareggi e sconfitte.");
			System.out.println("3. Registrare un nuovo calciatore.");
			System.out.println("4. Visualizzare il valore della rosa di tutte squadre.");
			System.out.println("5. Registrare un nuovo allenatore.");
			System.out.println("6. Visualizzare il numero di partite dirette da ciascun arbitro.");
			System.out.println("7. Visualizzare i calciatori il cui contratto è in scadenza entro la fine della prossima stagione (01/06/2024)");
			System.out.println("8. Visualizza le squadre che hanno vinto almeno un trofeo tra la stagione 2021-2022 e la stagione 2022-2023.");
			System.out.println("9. Modifica la posizione della squadra, inserendo il suo codice, nella classifica della competizione Serie A.");
			System.out.println("10. Visualizza le squadre che hanno vinto almeno una Serie A o una Coppa Italia e che abbiano vinto anche la Champions League.");
			System.out.println("11. Visualizza il numero di calciatori extracomunitari (non cittadini europei) presenti in ciascuna squadra.");
			System.out.println("12. Ottieni la classifica della competizione “Serie A” dalla prima in classifica all’ultima.");
			System.out.println("13. Ottieni un report delle squadre che partecipano ad una competizione europea (Champions League, Europa League, Conference League) con nome del relativo torneo e fase del torneo raggiunta.");
			System.out.println("14. Visualizza tutti i calciatori appartenenti alla squadra di cui si inserisce il codice.");
			System.out.println("15. Aggiorna la scadenza contrattuale di un calciatore.");
			System.out.println("16. Visualizza “plusvalenze” o “minusvalenze”, ricavate da valore di mercato - valore di acquisto, di tutti i calciatori della squadra di cui si inserisce il codice.");
			System.out.println("17. Visualizza gli stadi che sono la sede casalinga di due o più squadre, con relative squadre.");
			System.out.println("18. Visualizza le nazionalità di ogni allenatore di una squadra.");
			System.out.println("19. Ottieni l’età media dei calciatori di tutte le squadre.");
			System.out.println("20. Elimina la relazione contrattuale che vige tra una squadra e un calciatore.");
			
			String op = sc.nextLine();
					
			if (op.equals("0"))
			{
				break;
			}
					
			switch (op)
			{
				case "1": Op1.Query1(); break;
				case "2": Op2.Query2(); break;
				case "3": Op3.Query3(); break;
				case "4": Op4.Query4(); break;
				case "5": Op5.Query5(); break;
				case "6": Op6.Query6(); break;
				case "7": Op7.Query7(); break;
				case "8": Op8.Query8(); break;
				case "9": Op9.Query9(); break;
				case "10": Op10.Query10(); break;
				case "11": Op11.Query11(); break;
				case "12": Op12.Query12(); break;
				case "13": Op13.Query13(); break;
				case "14": Op14.Query14(); break;
				case "15": Op15.Query15(); break;
				case "16": Op16.Query16(); break;
				case "17": Op17.Query17(); break;
				case "18": Op18.Query18(); break;
				case "19": Op19.Query19(); break;
				case "20": Op20.Query20(); break;
			}
		}
	}

}
