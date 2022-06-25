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

public class CorsoDAO {
	
	//METODO PER ELENCARE TUTTI I CORSI DI UN DATO PERIODO DIDATTICO:
	public List<Corso> getCorsiByPeriodo(int periodo) {
		
		//Stringa contenente la query:
		String sql = "SELECT * FROM corso WHERE pd = ?"; //*
		
		//*
		//Copiare la stringa da HeidySQL e inserirla tra "":
		//Sostituire i parametri usati come esempio con ? (pd = ?).
		//Eliminare tutti gli \r\n. E qualora fosse necessario andare a
		//capo inserire sempre uno spazio dopo ciascun elemento.
		
		
		//Struttura dati dei valori di ritorno:
		List<Corso> corsiResult = new ArrayList<>();
		
		
		//Codice di accesso effettivo al database (try-catch):
		try {
			
			//Connessione:
			Connection conn = ConnectDB.getConnection();
			
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql);
			
			//Inserire paramentri nella query (metodo set corretto!):
			st.setInt(1, periodo); //i parametri della query 
								   //sono numerati a partire da 1
			
			//Eseguire la query e salvarne il risultato:
			
			ResultSet rs = st.executeQuery();
		
			while(rs.next()) {
				
				//Creare un Corso in cui salvare temporaneamente
				//le informazioni della riga del database letta:
				Corso c = new Corso(rs.getString("codins"), 	
									rs.getInt("crediti"),		
									rs.getString("nome"),	//*	
									rs.getInt("pd"));		
				
				//*:
				//I nomi da inserire tra "" sono quelli delle colonne 
				//del database heidiSQL, non necessariamente quelli 
				//degli attributi della classe Corso.
				
				
				//Inserire tale Corso nella struttura dati risultante:
				corsiResult.add(c);
			}
			
			//Chiudere tutti gli elementi:
			conn.close();
			st.close();
			rs.close();
			
			//Ritornare la struttura dati creata:
			return corsiResult; 
					
		} catch (SQLException e) {
			
			System.err.println("Errore nel DAO.");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	//MEDOTO PER STAMPARE IL NUMERO DI ISCRITTI AI CORSI DI UN DATO PERIODO:
	public Map<Corso,Integer> getIscritti(int periodo){
		
		//Stringa contenente la query:
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS num "
					+ "FROM corso c, iscrizione i "
					+ "WHERE c.codins = i.codins AND c.pd = ? "
					+ "GROUP BY c.codins, c.crediti, c.nome, c.pd";
		
		//Struttura dati dei valori di ritorno:
		Map<Corso,Integer> corsiResult = new HashMap<>();
		
		//Codice di accesso effettivo al database (try-catch):
		try {
					
			//Connessione:
			Connection conn = ConnectDB.getConnection();
					
			//PreparedStatement:
			PreparedStatement st = conn.prepareStatement(sql);
					
			//Inserire paramentri nella query:
			st.setInt(1, periodo);
					
			//Eseguire la query e salvarne il risultato:
			
			ResultSet rs = st.executeQuery();
				
			while(rs.next()) {
						
				//Creare un Corso in cui salvare temporaneamente
				//le informazioni della riga del database letta:
				Corso c = new Corso(rs.getString("codins"), 	
									rs.getInt("crediti"),		
									rs.getString("nome"),		
									rs.getInt("pd"));		
				
				
				//Inserire tale Corso nella struttura dati risultante
				//insieme al numero dei suoi iscritti:
				corsiResult.put(c, rs.getInt("num"));
			}
					
			//Chiudere tutti gli elementi:
			conn.close();
			st.close();
			rs.close();
					
			//Ritornare la struttura dati creata:
			return corsiResult; 
							
			} catch (SQLException e) {
					
				System.err.println("Errore nel DAO.");
				e.printStackTrace();
				return null;
			}
		
		
	}

	
		
}
