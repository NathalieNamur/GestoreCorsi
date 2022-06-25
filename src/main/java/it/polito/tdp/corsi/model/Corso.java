package it.polito.tdp.corsi.model;

import java.util.Objects;

public class Corso {
	
	//ATTRIBUTI:
	//da copiare dalla tabella corrispondente in HeidiSQL
	
	private String codins; 
	private int crediti;
	private String nome; 
	private int pd;
	
	
	
	//COSTRUTTORE:
	public Corso(String codins, int crediti, String nome, int pd) {
		
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	

	//METODI:
	
	//Generare di default i metodi:
	//- getter e setter 
	//- hashCode() ed equals() *
	//- toString()
	
	//*:
	//hashCode() ed equals() della sola chiave primaria (codins), 
	//che identifica univocamente i corsi.
	
	
	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}


	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(codins);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		return Objects.equals(codins, other.codins);
	}


	@Override
	public String toString() {
		return "Corso [codins=" + codins + ", crediti=" + crediti + ", nome=" + nome + ", pd=" + pd + "]";
	} 

	
	
}
