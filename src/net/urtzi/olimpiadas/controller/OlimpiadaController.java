package net.urtzi.olimpiadas.controller;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Labeled;
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
	/**
	 * The GridPane where the data will be, used for modification, addition and
	 * removal from the database.
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
	 * Method inherited from the Initializable interface, allows to execute code
	 * when the aplication loads.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareTablesForDBItems();
		deporteTableView.setItems(gestor.cargarDeportes());
		deporteTableView.setOnMouseClicked(e -> {
			Object selectedItem = deporteTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
			}
		});
		deportistaTableView.setOnMouseClicked(e -> {
			Object selectedItem = deportistaTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
			}
		});
		equipoTableView.setOnMouseClicked(e -> {
			Object selectedItem = equipoTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
			}
		});
		eventoTableView.setOnMouseClicked(e -> {
			Object selectedItem = eventoTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
			}
		});
		participacionTableView.setOnMouseClicked(e -> {
			Object selectedItem = participacionTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
			}
		});
		olimpiadaTableView.setOnMouseClicked(e -> {
			Object selectedItem = olimpiadaTableView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				populateDataManipulationGridPane(selectedItem);
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
	void anadirEntrada(ActionEvent event) throws SQLException {
		Object[] data = new Object[5];
		switch (currentTab) {
		case "deporte":
			data[0] = ((TextField) dataManipulationGridPane.getChildren().get(1)).getText();
			gestor.addItemToDatabase(new Deporte((String) data[0]));
			cargarDeporte(event);
			break;
		case "deportista":
			data[0] = ((TextField) dataManipulationGridPane.getChildren().get(1)).getText();
			data[1] = ((TextField) dataManipulationGridPane.getChildren().get(3)).getText();
			data[2] = ((TextField) dataManipulationGridPane.getChildren().get(5)).getText();
			data[3] = ((TextField) dataManipulationGridPane.getChildren().get(7)).getText();
			gestor.addItemToDatabase(new Deportista((String) data[0], ((String) data[1]).charAt(0),
					Integer.parseInt((String) data[2]), Integer.parseInt((String) data[3])));
			cargarDeportista(event);
			break;
		case "equipo":
			data[0] = ((TextField) dataManipulationGridPane.getChildren().get(1)).getText().toString();
			data[1] = ((TextField) dataManipulationGridPane.getChildren().get(3)).getText().toString();
			gestor.addItemToDatabase(new Equipo((String) data[0], (String) data[1]));
			cargarEquipo(event);
			break;
		case "evento":
			data[0] = ((TextField) dataManipulationGridPane.getChildren().get(1)).getText().toString();
			data[1] = ((ComboBox<String>) dataManipulationGridPane.getChildren().get(3)).getSelectionModel()
					.getSelectedItem();
			data[2] = ((ComboBox<String>) dataManipulationGridPane.getChildren().get(5)).getSelectionModel()
					.getSelectedItem();
			data[1] = gestor.getOlimpiadaByID(
					Integer.parseInt(data[1].toString().substring(0, data[1].toString().indexOf(" ")).trim()));
			data[2] = gestor.getDeporteByID(
					Integer.parseInt(data[2].toString().substring(0, data[2].toString().trim().indexOf(" ")).trim()));
			gestor.addItemToDatabase(new Evento((String) data[0], (Olimpiada) data[1], (Deporte) data[2]));
			cargarEvento(event);
			break;
		case "olimpiada":
			data[0] = ((TextField) dataManipulationGridPane.getChildren().get(1)).getText().toString();
			data[1] = ((TextField) dataManipulationGridPane.getChildren().get(3)).getText().toString();
			data[2] = ((TextField) dataManipulationGridPane.getChildren().get(5)).getText().toString();
			data[3] = ((TextField) dataManipulationGridPane.getChildren().get(7)).getText().toString();
			gestor.addItemToDatabase(new Olimpiada((String) data[0], Integer.parseInt((String) data[1]),
					(String) data[2], (String) data[3]));
			cargarOlimpiada(event);
			break;
		case "participacion":
			data[0] = ((ComboBox<String>) dataManipulationGridPane.getChildren().get(1)).getSelectionModel()
					.getSelectedItem();
			data[1] = ((ComboBox<String>) dataManipulationGridPane.getChildren().get(3)).getSelectionModel()
					.getSelectedItem();
			data[2] = ((ComboBox<String>) dataManipulationGridPane.getChildren().get(5)).getSelectionModel()
					.getSelectedItem();
			data[3] = ((TextField) dataManipulationGridPane.getChildren().get(7)).getText().toString();
			data[4] = ((TextField) dataManipulationGridPane.getChildren().get(9)).getText().toString();
			data[0] = gestor.getDeportistaByID(
					Integer.parseInt(data[0].toString().substring(0, data[1].toString().indexOf(" ")).trim()));
			data[1] = gestor.getEventoByID(
					Integer.parseInt(data[1].toString().substring(0, data[1].toString().indexOf(" ")).trim()));
			data[2] = gestor.getEquipoByID(
					Integer.parseInt(data[2].toString().substring(0, data[2].toString().indexOf(" ")).trim()));
			gestor.addItemToDatabase(new Participacion((Deportista) data[0], (Evento) data[1], (Equipo) data[2],
					Integer.parseInt((String) data[3]), (String) data[4]));
			cargarParticipacion(event);
			break;
		}

	}

	@FXML
	void borrarEntrada(ActionEvent event) throws SQLException {
		Object[] data = new Object[5];
		switch (currentTab) {
		case "deporte":
			Deporte selectedDeporte = deporteTableView.getSelectionModel().getSelectedItem();
			if (selectedDeporte != null)
				gestor.borrarDeporte(selectedDeporte);
			cargarDeporte(event);
			break;
		case "deportista":
			Deportista selectedDeportista = deportistaTableView.getSelectionModel().getSelectedItem();
			if (selectedDeportista != null)
				gestor.borrarDeportista(selectedDeportista);
			cargarDeportista(event);
			break;
		case "equipo":
			Equipo selectedEquipo = equipoTableView.getSelectionModel().getSelectedItem();
			if (selectedEquipo != null)
				gestor.borrarEquipo(selectedEquipo);
			cargarEquipo(event);
			break;
		case "evento":
			Evento selectedEvento = eventoTableView.getSelectionModel().getSelectedItem();
			if (selectedEvento != null)
				gestor.borrarEvento(selectedEvento);
			cargarEvento(event);
			break;
		case "olimpiada":
			Olimpiada selectedOlmpiada = olimpiadaTableView.getSelectionModel().getSelectedItem();
			if (selectedOlmpiada != null)
				gestor.borrarOlimpiada(selectedOlmpiada);
			cargarOlimpiada(event);
			break;
		case "participacion":
			Participacion selectedParticipacion = participacionTableView.getSelectionModel().getSelectedItem();
			if (selectedParticipacion != null)
				gestor.borrarParticipacion(selectedParticipacion);
			cargarParticipacion(event);
			break;
		}

	}

	/**
	 * Prepares all the TableViews to receive Objects of them item type. Looping
	 * through their collumns and checking the name.
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
			String name = tc.getText().replace("ñ", "ni"); // Remember to remove 'ñ' since it gives problems.
			tc.setCellValueFactory(new PropertyValueFactory<>(name));
		}
		for (TableColumn<Participacion, ?> tc : participacionTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(tc.getText()));
		}
	}

	/**
	 * Receiving a table, creates the needed components to insert or modify data
	 * from the table
	 * 
	 * @param table
	 */
	private void buildDataManipulationGridPane(TableView<?> table) {
		dataManipulationGridPane.getChildren().clear();
		dataManipulationValues = new HashMap<>();
		String[] dropDownItems = new String[] { "Olimpiada", "Deporte", "Deportista", "Evento", "Equipo" };
		int i = 0;
		for (TableColumn<?, ?> tc : table.getColumns()) {
			if (stringContainsItemFromList(tc.getText(), dropDownItems)) {
				ObservableList<String> identifiedItems = identifiedItemObservableList(tc);

				dataManipulationValues.put(tc.getText(), new ComboBox<String>(identifiedItems));
			} else {
				dataManipulationValues.put(tc.getText(), new TextField());
			}
			dataManipulationGridPane.addRow(i, new Label(tc.getText()), dataManipulationValues.get(tc.getText()));

			i++;
		}
		dataManipulationGridPane.addRow(5, anadirButton, borrarButton);
	}

	private void populateDataManipulationGridPane(Object item) {
		if (item instanceof Deporte) {
			for (Node node : dataManipulationGridPane.getChildren()) {
				if (node instanceof TextField) {
					TextField textfield = (TextField) node;
					textfield.setText(((Deporte) item).getNombre());
				}
			}
		} else if (item instanceof Deportista) {
			for (int i = 0; i < dataManipulationGridPane.getChildren().size(); i++) {
				Node node = dataManipulationGridPane.getChildren().get(i);
				if (node instanceof TextField) {
					TextField textfield = (TextField) node;
					switch (i) {
					case 1:
						textfield.setText(((Deportista) item).getNombre());
						break;
					case 3:
						textfield.setText(((Deportista) item).getSexo() + "");
						break;
					case 5:
						textfield.setText(((Deportista) item).getPeso() + "");
						break;
					case 7:
						textfield.setText(((Deportista) item).getAltura() + "");
						break;
					}
				}
			}
		} else if (item instanceof Equipo) {
			for (int i = 0; i < dataManipulationGridPane.getChildren().size(); i++) {
				Node node = dataManipulationGridPane.getChildren().get(i);
				if (node instanceof TextField) {
					TextField textfield = (TextField) node;
					switch (i) {
					case 1:
						textfield.setText(((Equipo) item).getNombre());
						break;
					case 3:
						textfield.setText(((Equipo) item).getAbreviatura());
						break;
					}
				}

			}

		} else if (item instanceof Evento) {
			for (int i = 0; i < dataManipulationGridPane.getChildren().size(); i++) {
				Node node = dataManipulationGridPane.getChildren().get(i);
				if (node instanceof TextField textfield) {
					if (i == 1) {
						textfield.setText(((Evento) item).getNombre());
					}
				} else if (node instanceof ComboBox) {
					switch (i) {
					case 3:
						((ComboBox<String>) node).getSelectionModel().select(
								((Evento) item).getDeporte().getId() + " - " + ((Evento) item).getDeporte().toString());
						break;
					case 5:
						((ComboBox<String>) node).getSelectionModel().select(((Evento) item).getOlimpiada().getId()
								+ " - " + ((Evento) item).getOlimpiada().toString());
						break;
					}
				}
			}
		} else if (item instanceof Olimpiada) {
			for (int i = 0; i < dataManipulationGridPane.getChildren().size(); i++) {
				Node node = dataManipulationGridPane.getChildren().get(i);
				if (node instanceof TextField) {
					TextField textfield = (TextField) node;
					switch (i) {
					case 1:
						textfield.setText(((Olimpiada) item).getNombre());
						break;
					case 3:
						textfield.setText(((Olimpiada) item).getAnio() + "");
						break;
					case 5:
						textfield.setText(((Olimpiada) item).getTemporada());
						break;
					case 7:
						textfield.setText(((Olimpiada) item).getCiudad());
						break;

					}
				}
			}
		} else if (item instanceof Participacion) {
			for (int i = 0; i < dataManipulationGridPane.getChildren().size(); i++) {
				Node node = dataManipulationGridPane.getChildren().get(i);
				if (node instanceof TextField) {
					TextField textfield = (TextField) node;
					switch (i) {
					case 7:
						textfield.setText(((Participacion) item).getEdad() + "");
						break;
					case 9:
						textfield.setText(((Participacion) item).getMedalla());
						break;

					}
				} else if (node instanceof ComboBox) {
					switch (i) {
					case 1:
						((ComboBox<String>) node).getSelectionModel()
								.select(((Participacion) item).getDeportista().getId() + " - "
										+ ((Participacion) item).getDeportista().toString());
						break;
					case 3:
						((ComboBox<String>) node).getSelectionModel().select(((Participacion) item).getEvento().getId()
								+ " - " + ((Participacion) item).getEvento().toString());
						break;
					case 5:
						((ComboBox<String>) node).getSelectionModel().select(((Participacion) item).getEquipo().getId()
								+ " - " + ((Participacion) item).getEquipo().toString());
						break;
					}
				}
			}
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
	 * Checks if the given string (inputStr) contains any of the items in the items
	 * list.
	 * 
	 * @param inputStr The strign to be checked.
	 * @param items    A list of strings to search.
	 * @return true if it is contained false if not.
	 */
	private static boolean stringContainsItemFromList(String inputStr, String[] items) {
		return Arrays.stream(items).anyMatch(inputStr::contains);
	}
}
