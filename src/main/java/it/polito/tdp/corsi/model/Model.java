package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	//ATTRIBUTI:
	private CorsoDAO corsoDAO; 
	private StudenteDAO studenteDAO;
	
	
	
	//COSTRUTTORE:
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	
	
	//METODI:
	public List<Corso> getCorsiByPeriodo(int periodo){
		return this.corsoDAO.getCorsiByPeriodo(periodo);	
	}
	
	public Map<Corso,Integer> getIscritti(int periodo){
		return this.corsoDAO.getIscritti(periodo);
	}
	
	
	public List<Studente> getStudentiByCorso(String codins){
		return this.studenteDAO.getStudentiByCorso(codins);
	}
	
	public Map<String,Integer> getDivisione(String codins){
		return this.studenteDAO.getDivisione(codins);
	}
	
	
	
}
