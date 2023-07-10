#1. Registrare una nuova partita.
	INSERT INTO Partita (ID, DataPartita, Avversario, RetiS1, RetiS2, Arbitro, Squadra)
	VALUES (id_partita, 'data_partita', 'avversario', retis1, retis2, 'arbitro', 'squadra');
		
	#CASE RetiS1 > RetiS2
	UPDATE Squadra AS s
	JOIN Partita AS p ON p.Squadra = s.Codice
	SET s.Vittorie = s.Vittorie + 1
	WHERE s.Codice = 'codice_squadra';
		
	#CASE RetiS1 == RetiS2
	UPDATE Squadra AS s
	JOIN Partita AS p ON p.Squadra = s.Codice
	SET s.Pareggi = s.Pareggi + 1
	WHERE s.Codice = 'codice_squadra';
		
	#CASE RetiS1 < RetiS2
	UPDATE Squadra AS s
	JOIN Partita AS p ON p.Squadra = s.Codice
	SET s.Sconfitte = s.Sconfitte + 1
	WHERE s.Codice = 'codice_squadra';

	INSERT INTO PertinenzaClassificata (PartitaID, ClassificataID)
	VALUES ('id_partita', 'id_competizione');
	#OR
	INSERT INTO PertinenzaTorneo (PartitaID, TorneoID)
	VALUES ('id_partita', 'id_competizione');
    
#2. Visualizza il rendimento delle squadre: ottieni numero di vittorie, pareggi e sconfitte.
	SELECT Squadra.Nome,
		Squadra.Vittorie AS NVittorie,
		Squadra.Pareggi AS NPareggi,
		Squadra.Sconfitte AS NSconfitte
	FROM Squadra;
    
#3. Registrare un nuovo calciatore.
	INSERT INTO Calciatore (CF, Nome, Cognome, DataNascita, Nazionalita, Ruolo, Valore, Costo)
	VALUES ('cf_calciatore', 'nome', 'cognome', 'data_nascita', 'nazionalita', 'ruolo', valore, costo);

	INSERT INTO ContrattoCalciatore (SquadraCodice, CalciatoreCF, DataScadenza, Retribuzione)
	VALUES ('codice_squadra', 'cf_calciatore', 'data_scadenza', retribuzione);

	UPDATE Squadra AS s
	JOIN ContrattoCalciatore AS i ON s.Codice = i.SquadraCodice
	JOIN Calciatore AS c ON i.CalciatoreCF = c.CF
	SET s.ValoreRosa = s.ValoreRosa + NEW.Valore
	WHERE s.Codice = 'codice_squadra' AND c.CF = 'cf_calciatore';
    
#4. Visualizza il valore della rosa di tutte le squadre.
	SELECT Nome, ValoreRosa
	FROM Squadra;
    
#5. Registrare un nuovo allenatore.
	INSERT INTO Allenatore (CF, Nome, Cognome, DataNascita, Nazionalita)
	VALUES ('cf_allenatore', 'nome', 'cognome', 'data_nascita', 'nazionalita');

	INSERT INTO ContrattoAllenatore (SquadraCodice, AllenatoreCF, DataScadenza, Retribuzione)
	VALUES ('codice_squadra', 'cf_allenatore', 'data_scadenza', retribuzione);
    
#6. Visualizzare il numero di partite dirette da ciascun arbitro.
	SELECT A.Nome, A.Cognome, COUNT(P.Arbitro) AS NumeroPartite
	FROM Arbitro AS A
		LEFT JOIN Partita AS P ON A.CF = P.Arbitro
	GROUP BY A.CF, A.Nome, A.Cognome;

#7. Visualizzare i calciatori il cui contratto è in scadenza entro la fine della prossima stagione (01/06/2024).
	SELECT Squadra.Nome AS NomeSquadra, Calciatore.Nome AS NomeCalciatore, Calciatore.Cognome AS CognomeCalciatore
	FROM ContrattoCalciatore
		JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice
		JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF
	WHERE ContrattoCalciatore.DataScadenza <= '2024-06-01';
    
#8. Visualizza le squadre che hanno vinto almeno un trofeo tra la stagione 2021-2022 e la stagione 2022-2023.
	SELECT DISTINCT S.Nome
	FROM Squadra AS S
	JOIN Palmares AS P ON S.Codice = P.Squadra
	WHERE P.Stagione = '2021-2022' OR P.Stagione = '2022-2023';
    
#9. Modifica la posizione della squadra, inserendo il suo codice, nella classifica della competizione Serie A.
	UPDATE PartecipazioneClassificata 
	SET Posizione = 'nuova_posizione'
	WHERE SquadraCodice = 'squadra_codice' and ClassificataID = 'SA';
    
#10. Visualizza le squadre che hanno vinto almeno una Serie A o una Coppa Italia e che abbiano vinto anche la Champions League.
	SELECT Squadra.Nome
	FROM Squadra
	WHERE Squadra.Codice IN (
		SELECT Squadra
		FROM Palmares
		WHERE (NomeTrofeo, Stagione) IN (
			SELECT NomeTrofeo, Stagione
			FROM Palmares
			WHERE NomeTrofeo = 'Serie A'
			UNION
			SELECT NomeTrofeo, Stagione
			FROM Palmares
			WHERE NomeTrofeo = 'Coppa Italia'
		)
	)
	AND Squadra.Codice IN (
		SELECT Squadra
		FROM Palmares
		WHERE (NomeTrofeo, Stagione) IN (
			SELECT NomeTrofeo, Stagione
			FROM Palmares
			WHERE NomeTrofeo = 'Champions League'
		)
	);
    
#11. Visualizza il numero di calciatori extracomunitari (non cittadini europei) presenti in ciascuna squadra.
	SELECT Squadra.Nome, COUNT(*) AS Extracomunitari
	FROM Calciatore 
		JOIN ContrattoCalciatore ON Calciatore.CF = ContrattoCalciatore.CalciatoreCF
		JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice
	WHERE Nazionalita NOT IN ('Austria', 'Belgio', 'Bulgaria', 'Cipro', 'Croazia', 'Danimarca', 'Estonia', 
		'Finlandia', 'Francia', 'Germania', 'Grecia', 'Irlanda', 'Italia', 'Lettonia', 'Lituania', 
		'Lussemburgo', 'Malta', 'Olanda', 'Polonia', 'Portogallo', 'Repubblica Ceca', 'Romania', 
		'Slovacchia', 'Slovenia', 'Spagna', 'Svezia')
	GROUP BY Squadra.Nome;
    
#12. Ottieni la classifica della competizione “Serie A” dalla prima in classifica all’ultima.
	SELECT Squadra.Nome, PartecipazioneClassificata.Posizione
	FROM Squadra
		JOIN PartecipazioneClassificata ON Squadra.Codice = PartecipazioneClassificata.SquadraCodice
		JOIN Classificata ON PartecipazioneClassificata.ClassificataID = Classificata.ID
	WHERE Classificata.ID = 'SA'
	ORDER BY PartecipazioneClassificata.Posizione
	LIMIT 20;
    
#13. Ottieni un report delle squadre che partecipano ad una competizione europea (Champions League, Europa League, Conference League) con nome del relativo torneo e fase del torneo raggiunta.
	SELECT Squadra.Nome AS Squadra, Torneo.Nome AS Torneo, PartecipazioneTorneo.Fase
	FROM Squadra
	JOIN PartecipazioneTorneo ON Squadra.Codice = PartecipazioneTorneo.SquadraCodice
	JOIN Torneo ON PartecipazioneTorneo.TorneoID = Torneo.ID
	WHERE Squadra.Codice IN (
		SELECT SquadraCodice
		FROM PartecipazioneTorneo
		WHERE TorneoID IN ('UCL', 'UEL', 'ECL')
	) AND PartecipazioneTorneo.TorneoID IN ('UCL', 'UEL', 'ECL');
    
#14. Visualizza tutti i calciatori appartenenti alla squadra di cui si inserisce il codice.
	SELECT Calciatore.Nome, Calciatore.Cognome, Calciatore.Ruolo, Calciatore.Nazionalita
	FROM Squadra
		JOIN ContrattoCalciatore ON Squadra.Codice = ContrattoCalciatore.SquadraCodice
		JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF
	WHERE Squadra.Codice = 'codice_squadra';
    
#15. Aggiorna la scadenza contrattuale di un calciatore.
	UPDATE ContrattoCalciatore
	JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF
	SET ContrattoCalciatore.DataScadenza = 'nuova_scadenza'
	WHERE Calciatore.CF = 'CF_calciatore';
    
#16. Visualizza “plusvalenze” o “minusvalenze”, ricavate da valore di mercato - valore di acquisto, di tutti i calciatori della squadra di cui si inserisce il codice.
	SELECT Calciatore.Nome, Calciatore.Cognome,
		   CASE
			  WHEN Calciatore.Valore > Calciatore.Costo THEN 'Plusvalenza'
			  WHEN Calciatore.Valore < Calciatore.Costo THEN 'Minusvalenza'
			  ELSE 'Nessuna variazione'
		   END AS PlusMinus,
		   (Calciatore.Valore - Calciatore.Costo) AS ValoreDiff
	FROM Calciatore
		JOIN ContrattoCalciatore ON Calciatore.CF = ContrattoCalciatore.CalciatoreCF
		JOIN Squadra ON ContrattoCalciatore.SquadraCodice = Squadra.Codice
	WHERE Squadra.Codice = 'codice_squadra';
    
#17. Visualizza gli stadi che sono la sede casalinga di due o più squadre, con relative squadre.
	SELECT St.Nome, GROUP_CONCAT(Sq.Nome) AS Squadre
	FROM Squadra AS Sq
	  JOIN Stadio AS St ON Sq.Stadio = St.Nome
	GROUP BY St.Nome
	HAVING COUNT(Sq.Codice) >= 2;
    
#18. Visualizza le nazionalità di ogni allenatore di una squadra.
	SELECT Squadra.Nome AS NomeSquadra, Allenatore.Nome AS NomeAllenatore, Allenatore.Cognome AS CognomeAllenatore, Allenatore.Nazionalita
	FROM Squadra
		JOIN ContrattoAllenatore ON Squadra.Codice = ContrattoAllenatore.SquadraCodice
	  JOIN Allenatore ON ContrattoAllenatore.AllenatoreCF = Allenatore.CF;
      
#19. Ottieni l’età media dei calciatori di tutte le squadre.
	SELECT Squadra.Nome AS NomeSquadra, AVG(YEAR(CURDATE()) - YEAR(Calciatore.DataNascita)) AS EtaMediaAnni
	FROM Squadra
		JOIN ContrattoCalciatore ON Squadra.Codice = ContrattoCalciatore.SquadraCodice
		JOIN Calciatore ON ContrattoCalciatore.CalciatoreCF = Calciatore.CF
	GROUP BY Squadra.Nome;
    
#20. Elimina la relazione contrattuale che vige tra una squadra e un calciatore.
	DELETE FROM ContrattoCalciatore
	WHERE CalciatoreCF = 'CF_calciatore';