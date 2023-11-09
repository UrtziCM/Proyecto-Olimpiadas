package net.urtzi.olimpiadas.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
/**
 * Controlador principal de la aplicacion principal.
 */
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
import net.urtzi.olimpiadas.controller.databasemanager.DBManager;
import net.urtzi.olimpiadas.models.Deporte;
import net.urtzi.olimpiadas.models.Deportista;
import net.urtzi.olimpiadas.models.Equipo;
import net.urtzi.olimpiadas.models.Evento;
import net.urtzi.olimpiadas.models.Olimpiada;
import net.urtzi.olimpiadas.models.Participacion;

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
    private DBManager gestor = new DBManager();

    /**
     * Method inherited from the Initializable interface, allows to execute code when the aplication loads.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareTablesForDBItems();
		deporteTableView.setItems(gestor.cargarDeportes());
	}
	
	@FXML
    void cargarDeporte(Event event) {
		deporteTableView.setItems(gestor.cargarDeportes());
		buildDataManipulationGridPane(deporteTableView);
    }

	    @FXML
    void cargarDeportista(Event event) {
    	deportistaTableView.setItems(gestor.cargarDeportistas());
    	buildDataManipulationGridPane(deportistaTableView);
    }

    @FXML
    void cargarEquipo(Event event) {
    	equipoTableView.setItems(gestor.cargarEquipos());
    	buildDataManipulationGridPane(equipoTableView);
    }

    @FXML
    void cargarEvento(Event event) {
    	eventoTableView.setItems(gestor.cargarEventos());
    	buildDataManipulationGridPane(eventoTableView);
    }

    @FXML
    void cargarOlimpiada(Event event) {
    	olimpiadaTableView.setItems(gestor.cargarOlimpiadas());
    	buildDataManipulationGridPane(olimpiadaTableView);
    }

    @FXML
    void cargarParticipacion(Event event) {
    	participacionTableView.setItems(gestor.cargarParticipaciones());
    	buildDataManipulationGridPane(participacionTableView);
    }
    

    @FXML
    void anadirEntrada(ActionEvent event) {
    	gestor.addItemToDatabase(null);
    }
	

    /**
     * Prepares all the TableViews to receive Objects of them item type. Looping through their collumns and checking the name.
     */
    private void prepareTablesForDBItems() {
		for (TableColumn<Deporte, ?> tc : deporteTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
		for (TableColumn<Deportista, ?> tc : deportistaTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
		for (TableColumn<Equipo, ?> tc : equipoTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
		for (TableColumn<Evento, ?> tc : eventoTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
		for (TableColumn<Olimpiada, ?> tc : olimpiadaTableView.getColumns()) {
			String name = tc.getText().replace("ñ","ni"); // Remember to remove 'ñ' since it gives problems.
			tc.setCellValueFactory(new PropertyValueFactory<>(name));
		}
		for (TableColumn<Participacion, ?> tc : participacionTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
	}
    
    private void buildDataManipulationGridPane(TableView<?> table) {
		dataManipulationGridPane.getChildren().clear();
    	int i = 0;
		for (TableColumn<?, ?> tc : table.getColumns()) {
			dataManipulationGridPane.addRow(i, new Label(tc.getText()), new TextField());
			i++;
		}
		dataManipulationGridPane.addRow(5, anadirButton, borrarButton);
		dataManipulationGridPane.addRow(6, replaceCheckBox);
		GridPane.setColumnSpan(replaceCheckBox, 2);
	}
}
