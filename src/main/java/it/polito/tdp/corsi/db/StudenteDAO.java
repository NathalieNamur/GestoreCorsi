package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	//METODO PER ELENCARE TUTTI GLI STUDENTI ISCRITTI A UN DATO CORSO (codins):
	public List<Studente> getStudentiByCorso(String codins) {
		
		//Stringa contenente la query:
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
					+ "FROM studente s, iscrizione i "
					+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		//*
		//Copiare la stringa da HeidySQL e inserirla tra "":
		//Sostituire i parametri usati come esempio con ? (pd = ?).
		//Eliminare tutti gli \r\n. E qualora fosse necessario andare a
		//capo inserire sempre uno spazio dopo ciascun elemento.
		
		
		//Struttura dati dei valori di ritorno:
		List<Studente> studentiResult = new ArrayList<>();
				
		//Codice di accesso effettivo al database (try-catch):
		try {
					
			//Connessione:
			Connection conn = ConnectDB.getConnection();
				
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql);
				
			//Inserire paramentri nella query (metodo set corretto!):
			st.setString(1, codins); //i parametri della query 
			   						 //sono numerati a partire da 1
			
			//Eseguire la query e salvarne il risultato:
			
			ResultSet rs = st.executeQuery();
				
			while(rs.next()) {
						
				//Creare uno Studente in cui salvare temporaneamente
				//le informazioni della riga del database letta:
				Studente s = new Studente(rs.getInt("matricola"), 	
										  rs.getString("nome"),		//*
										  rs.getString("cognome"),		
										  rs.getString("CDS"));		
						
				//*:
				//I nomi da inserire tra "" sono quelli delle colonne 
				//del database heidiSQL, non necessariamente quelli 
				//degli attributi della classe corso.
						
						
				//Inserire tale Studente nella struttura dati risultante:
				studentiResult.add(s);		
			}
				
			//Chiudere tutti gli elementi:
			conn.close();
			st.close();
			rs.close();
					
			//Ritornare la struttura dati creata:
			return studentiResult; 
						
		} catch (SQLException e) {
					
			System.err.println("Errore nel DAO.");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	//METODO PER STAMPARE LA DIVISIONE DEGLI STUDENTI 
	//TRA I CORSI DI STUDIO (CDS), DATO UN CODICE CORSO (codins).
	
	//NB: 
	//In alcuni casi potrebbe essere più conveniente una List<Divisione>
	//(in particolar modo nel caso in cui venga richiesto l'ordinamento 
	//del risultato): ved. L13 (52m15) 
	
	public Map<String,Integer> getDivisione(String codins){
		
		//Stringa contenente la query:
		String sql = "SELECT s.CDS, COUNT(*) AS num "
					+ "FROM studente s, iscrizione i "
					+ "WHERE s.matricola = i.matricola AND i.codins = ? "
					+ "GROUP BY s.CDS";
		
		//NB:
		//Qualora si volesse filtrare delle righe del database,
		//ad esempio per eliminare i risultati dovuti a righe sporche o incomplete,
		//tale operazione va inserita nella query:
		
		//+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.cds<>'' "
		
		
		//Struttura dati dei valori di ritorno:
		Map<String,Integer> divisioneResult = new HashMap<>();
		
		//Codice di accesso effettivo al database (try-catch):
		try {
					
			//Connessione:
			Connection conn = ConnectDB.getConnection();
					
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql);
					
			//Inserire paramentri nella query:
			st.setString(1, codins);
					
			//Eseguire la query e salvarne il risultato:
			
			ResultSet rs = st.executeQuery();
				
			while(rs.next()) {
						
				//Creare una stringa in cui salvare temporaneamente
				//le informazioni della riga del database letta:
				String str = rs.getString("CDS");	
										
				//Inserire tale stringa nella struttura dati risultante,
				//insieme al numero di studenti iscritti al corso corrispondente:
				divisioneResult.put(str, rs.getInt("num"));
						
			}
					
			//Chiudere tutti gli elementi:
			conn.close();
			st.close();
			rs.close();
					
			//Ritornare la struttura dati creata:
			return divisioneResult; 
							
			} catch (SQLException e) {
					
				System.err.println("Errore nel DAO.");
				e.printStackTrace();
				return null;
			}
		
		
	}
	
	
	
}
