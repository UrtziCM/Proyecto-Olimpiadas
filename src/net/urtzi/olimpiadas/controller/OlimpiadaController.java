package net.urtzi.olimpiadas.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private DBManager gestor = new DBManager();
    private HashMap<String, Node> dataManipulationValues;
    private String currentTab = "deporte";

    /**
     * Method inherited from the Initializable interface, allows to execute code when the aplication loads.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareTablesForDBItems();
		deporteTableView.setItems(gestor.cargarDeportes());
		deporteTableView.setOnMouseClicked(e -> {
			if (deporteTableView.getSelectionModel().getSelectedItem() == null) return;
			switch (currentTab) {
				case "deporte":
					populateDataManipulationGridPane();
					break;
				case "deportista":
					populateDataManipulationGridPane();
					break;
				case "equipo":
					populateDataManipulationGridPane();
					break;
				case "evento":
					populateDataManipulationGridPane();
					break;
				case "olimpiada":
					populateDataManipulationGridPane();
					break;
				case "participacion":
					populateDataManipulationGridPane();
					break;
				default:
					return FXCollections.observableArrayList();
			}
			
		});
	}
	
	@FXML
    void cargarDeporte(Event event) {
		currentTab = "deporte";
		deporteTableView.setItems(gestor.cargarDeportes());
		buildDataManipulationGridPane(deporteTableView);
    }

	    @FXML
    void cargarDeportista(Event event) {
	    	currentTab = "deportista";
    	deportistaTableView.setItems(gestor.cargarDeportistas());
    	buildDataManipulationGridPane(deportistaTableView);
    }

    @FXML
    void cargarEquipo(Event event) {
    	currentTab = "equipo";
    	equipoTableView.setItems(gestor.cargarEquipos());
    	buildDataManipulationGridPane(equipoTableView);
    }

    @FXML
    void cargarEvento(Event event) {
    	currentTab = "evento";
    	eventoTableView.setItems(gestor.cargarEventos());
    	buildDataManipulationGridPane(eventoTableView);
    }

    @FXML
    void cargarOlimpiada(Event event) {
    	currentTab = "olimpiada";
    	olimpiadaTableView.setItems(gestor.cargarOlimpiadas());
    	buildDataManipulationGridPane(olimpiadaTableView);
    }

    @FXML
    void cargarParticipacion(Event event) {
    	currentTab = "participacion";
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
    /**
     * Receiving a table, creates the needed components to insert or modify data from the table
     * @param table
     */
    private void buildDataManipulationGridPane(TableView<?> table) {
		dataManipulationGridPane.getChildren().clear();
		dataManipulationValues = new HashMap<>();
		String[] dropDownItems = new String[] {
			"Olimpiada",
			"Deporte",
			"Deportista",
			"Evento",
			"Equipo"
		};
    	int i = 0;
		for (TableColumn<?, ?> tc : table.getColumns()) {
			if (stringContainsItemFromList(tc.getText(), dropDownItems)) {
				ObservableList<String> identifiedItems = identifiedItemObservableList(tc);
				
				dataManipulationValues.put(tc.getText(), new ComboBox<String>(identifiedItems));
			} else {
				dataManipulationValues.put(tc.getText(), new TextField());
			}
			dataManipulationGridPane.addRow(i, new Label(tc.getText()),dataManipulationValues.get(tc.getText()) );
			
			i++;
		}
		dataManipulationGridPane.addRow(5, anadirButton, borrarButton);
		dataManipulationGridPane.addRow(6, replaceCheckBox);
		GridPane.setColumnSpan(replaceCheckBox, 2);
	}
    
    private void populateDataManipulationGridPane(Object item) {
    	if (item instanceof Deporte) {
			for (Node node : dataManipulationGridPane.getChildren()) {
				if (node.getClass() == TextField.class) {
					TextField textfield = (TextField) node;
					textfield.setText(((Deporte)item).getNombre());
				}
			}
		} else if (item instanceof Deportista) {
			addDeportista((Deportista) item);
		} else if (item instanceof Equipo) {
			addEquipo((Equipo) item);
		} else if (item instanceof Evento){
			addEvento((Evento) item);
		}else if (item instanceof Olimpiada){
			addOlimpiada((Olimpiada)item);
		}else if (item instanceof Participacion) {
			addParticipacion((Participacion) item);
		}
    }

	/**
	 * @param tc The tableColumn to be identified
	 * @return an ObservableList ith the id + the tostring of the item.
	 */
	private ObservableList<String> identifiedItemObservableList(TableColumn<?, ?> tc) {
		ObservableList<String> identifiedItems = FXCollections.observableArrayList();
		switch (tc.getText().toLowerCase()) {
		case "deporte":
			for (Deporte item : gestor.cargarDeportes()) {
				identifiedItems.add(item.getId() + " - " + item.toString());
			}
			break;
		case "deportista":
			for (Deportista item : gestor.cargarDeportistas()) {
				identifiedItems.add(item.getId() + " - " + item.toString());
			}
			break;
		case "equipo":
			for (Equipo item : gestor.cargarEquipos()) {
				identifiedItems.add(item.getId() + " - " + item.toString());
			}
			break;
		case "evento":
			for (Evento item : gestor.cargarEventos()) {
				identifiedItems.add(item.getId() + " - " + item.toString());
			}
			break;
		case "olimpiada":
			for (Olimpiada item : gestor.cargarOlimpiadas()) {
				identifiedItems.add(item.getId() + " - " + item.toString());
			}					
			break;
		default:
			identifiedItems = null;
		}
		return identifiedItems;
	}
    /**
     * Checks if the given string (inputStr) contains any of the items in the items list.
     * @param inputStr The strign to be checked.
     * @param items A list of strings to check.
     * @return true if it is contained false if not.
     */
    private static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }
}
