/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML 
    private TextField txtPeriodo; 

    @FXML
    private TextField txtCorso;

    @FXML 
    private Button btnCorsiPerPeriodo; 

    @FXML 
    private Button btnNumeroStudenti; 

    @FXML
    private Button btnStudenti;

    @FXML 
    private Button btnDivisioneStudenti;

    @FXML 
    private TextArea txtRisultato;

    
    
    //I:
    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	
    	String periodo = txtPeriodo.getText();
    	int periodoNumerico;
    	
    	try {
    		periodoNumerico = Integer.parseInt(periodo);
    		
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("ERRORE: Inserisci un periodo numerico.");
    		return;
    	}
    	
    	if(periodoNumerico<1 || periodoNumerico>2) {
    		txtRisultato.setText("ERRORE: Inserisci come periodo 1 o 2.");
    		return;
    	}
    	
    	
    	List<Corso> corsi = model.getCorsiByPeriodo(periodoNumerico);
    	
    	for(Corso c : corsi)
    		txtRisultato.appendText(c + "\n"); 	
    	
    }

    
    //II:
    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	
    	String periodo = txtPeriodo.getText();
    	int periodoNumerico;
    	
    	try {
    		periodoNumerico = Integer.parseInt(periodo);
    		
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("ERRORE: Inserisci un periodo numerico.");
    		return;
    	}
    	
    	if(periodoNumerico<1 || periodoNumerico>2) {
    		txtRisultato.setText("ERRORE: Inserisci come periodo 1 o 2.");
    		return;
    	}
    	
    	
    	Map<Corso,Integer> iscritti = model.getIscritti(periodoNumerico);
    	
    	for(Corso c : iscritti.keySet())
    		txtRisultato.appendText(c + " " + iscritti.get(c) + "\n");
  	
    }

    
    //IV:
    @FXML
    void stampaDivisione(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	
    	String codins = txtCorso.getText();
    	
    	if(codins == null || codins.equals("")) {
    		txtRisultato.appendText("ERRORE: Inserisci un codice corso.");
    		return; 
    	}	
    	
    	/**Controllare inoltre che il corso esista*/
    	
    	
    	Map<String,Integer> divisione = model.getDivisione(codins);
    	
    	for(String str : divisione.keySet())
    		txtRisultato.appendText(str + " " + divisione.get(str) + "\n");
  
    }

    
    //III:
    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	
    	String codins = txtCorso.getText();
    	
    	if(codins == null || codins.equals("")) {
    		txtRisultato.appendText("ERRORE: Inserisci un codice corso.");
    		return; 
    	}	
    	
    	/**Controllare inoltre che il corso esista*/
    	
    	
    	for (Studente s : model.getStudentiByCorso(codins)) 
    		txtRisultato.appendText(s + "\n");
    }

    
    
    @FXML
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
    
}