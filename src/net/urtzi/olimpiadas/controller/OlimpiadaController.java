package net.urtzi.olimpiadas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import net.urtzi.olimpiadas.models.Deporte;
import net.urtzi.olimpiadas.models.Deportista;
import net.urtzi.olimpiadas.models.Equipo;
import net.urtzi.olimpiadas.models.Evento;
import net.urtzi.olimpiadas.models.Olimpiada;
import net.urtzi.olimpiadas.models.Participacion;
/**
 * Controlador principal de la aplicacion principal.
 */

public class OlimpiadaController implements javafx.fxml.Initializable {

	@FXML // fx:id="anadirButton"
    private Button anadirButton; 

    @FXML // fx:id="borrarButton"
    private Button borrarButton; 
    @FXML // fx:id="replaceCheckBox"
    private CheckBox replaceCheckBox; 
	/**
	 * The GridPane where the data will be, used for modification, addition and removal from the database.
	 */
    @FXML // fx:id="dataManipulationGridPane"
    private GridPane dataManipulationGridPane;
    @FXML // fx:id="deporteTableView"
    private TableView<Deporte> deporteTableView;
    @FXML // fx:id="deportistaTableView"
    private TableView<Deportista> deportistaTableView;

    @FXML // fx:id="equipoTableView"
    private TableView<Equipo> equipoTableView;

    @FXML // fx:id="eventoTableView"
    private TableView<Evento> eventoTableView;

    @FXML // fx:id="olimpiadaTableView"
    private TableView<Olimpiada> olimpiadaTableView;

    @FXML // fx:id="participacionTableView"
    private TableView<Participacion> participacionTableView;

    /**
     * Method inherited from the Initializable interface, allows to execute code when the aplication loads.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	@FXML
    void cargarDeporte(Event event) {

    }

	@FXML
    void cargarDeportista(Event event) {

    }

    @FXML
    void cargarEquipo(Event event) {

    }

    @FXML
    void cargarEvento(Event event) {

    }

    @FXML
    void cargarOlimpiada(Event event) {

    }

    @FXML
    void cargarParticipacion(Event event) {

    }
    

    @FXML
    void anadirEntrada(ActionEvent event) {

    }
	

    
}
