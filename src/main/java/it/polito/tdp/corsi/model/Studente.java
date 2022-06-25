package it.polito.tdp.corsi.model;

import java.util.Objects;

public class Studente {

	//ATTRIBUTI:
	//da copiare dalla tabella corrispondente in HeidiSQL
	
	private int matricola; 
	private String cognome; 
	private String nome;
	private String CDS;
	
	
	
	//COSTRUTTORE:
	public Studente(int matricola, String cognome, String nome, String CDS) {
		
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.CDS = CDS;
	}


	
	//METODI:
	
	//Generare di default i metodi:
	//- getter e setter 
	//- hashCode() ed equals() *
	//- toString()
			
	//*:
	//hashCode() ed equals() della sola chiave primaria matricola, 
	//che identifica univocamente i corsi.
	
	
	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCDS() {
		return CDS;
	}

	public void setCDS(String CDS) {
		this.CDS = CDS;
	}


	@Override
	public int hashCode() {
		return Objects.hash(matricola);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		return matricola == other.matricola;
	}


	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", CDS=" + CDS + "]";
	} 
	
	
	
}
