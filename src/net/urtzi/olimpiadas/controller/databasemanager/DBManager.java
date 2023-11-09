package net.urtzi.olimpiadas.controller.databasemanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.urtzi.olimpiadas.models.*;


/**
 * Database manager class uses a ConnectionDB to access a database and retrieve data.
 * @see net.urtzi.olimpiadas.controller.database.ConnectionDB
 */
public class DBManager {
	private ConnectionDB conexion;

	/**
	 * Retrieves the data from the Deporte table in the database and returns an
	 * ObservableList with it.
	 * @return ObservableList with the Deporte objects from the database.
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */
	public ObservableList<Deporte> cargarDeportes() {

		ObservableList<Deporte> deportes = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT * FROM Deporte";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_deporte");
				String nombre = rs.getString("nombre");

				Deporte d = new Deporte(id, nombre);
				deportes.add(d);
			}

			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de deportes desde la base de datos");
			System.out.println(e);
		}
		return deportes;
	}

	/**
	 * Uses a connection to the database to add a Deporte obect to the database
	 * @param newDeporte the Deporte to be added
	 * @throws SQLException in case the Deporte already exists or the connection raises errors.
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */
	public void addDeporte(Deporte newDeporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement pstm = conexion.getConexion().createStatement();
		String sqlAddDeporte = "INSERT INTO Deporte VALUES(" + newDeporte.getId() + "," + newDeporte.getNombre() + ")";
		pstm.executeUpdate(sqlAddDeporte);
		conexion.closeConexion();
	}
	/**
	 * Uses a connection to the database to delete a Deporte object from the database
	 * @param deporte the Deporte to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */
	public void borrarDeporte(Deporte deporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Deporte WHERE id=" + deporte.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	/**
	 * Modifies the oldDeporte entry from the database with the values fo newDeporte-
	 * @param oldDeporte the instance to be modified
	 * @param newDeporte the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the connection.
	 */

	public void modificarDeporte(Deporte oldDeporte, Deporte newDeporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET nombre='" + newDeporte.getNombre() + "'," + "id_deporte=" + newDeporte.getId() + " WHERE id=" + oldDeporte.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	/**
	 * Retrieves the data from the Deportista table in the database and returns an
	 * ObservableList all entries in it.
	 * @return ObservableList with the Deportista objects from the database.
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public ObservableList<Deportista> cargarDeportistas() {
		ObservableList<Deportista> deportistas = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT * FROM Deportista";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_deportista");
				String nombre = rs.getString("nombre");
				String sexo = rs.getString("sexo");
				int peso = rs.getInt("peso");
				int altura = rs.getInt("altura");
				Deportista d = new Deportista(id, nombre, sexo.charAt(0), peso, altura);
				deportistas.add(d);
			}

			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de deportistas desde la base de datos");
		}
		return deportistas;
	}
	/**
	 * Adds a new Deportista into the database, in case it does exist or the connection fails, returns false
	 * @param newDeportista the Deportista to be added into the database.
	 * @throws SQLException if the Deportista does exist or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public void addDeportista(Deportista newDeportista) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddDeportista = "INSERT INTO Deportista VALUES(" + newDeportista.getId() + "," + newDeportista.getNombre() + "," + newDeportista.getSexo()+ "," + newDeportista.getPeso()+ "," + newDeportista.getAltura() + ")";
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddDeportista);
		pstm.executeUpdate(sqlAddDeportista);
		conexion.closeConexion();
	}
	/**
	 * Modifies the oldDeportista entry from the database with the values for the newDeportista.
	 * @param oldDeportista the instance to be modified
	 * @param newDeportista the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the connection.
	 */

	public void modificarDeportista(Deportista oldDeportista, Deportista newDeportista) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " 
					+ "SET id_deportista=" + newDeportista.getId() + "," 
					+ "nombre='" + newDeportista.getNombre() + "',"
					+ "sexo='" + newDeportista.getSexo() + "',"
					+ "peso=" + newDeportista.getPeso()+ ","
					+ "altura=" + newDeportista.getAltura() 
					+ " WHERE id=" + oldDeportista.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	/**
	 * Retrieves the data from the Deportista table in the database and returns an
	 * ObservableList all entries in it.
	 * @return ObservableList with the Deportista objects from the database.
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public ObservableList<Equipo> cargarEquipos() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT * FROM Equipo";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_equipo");
				String nombre = rs.getString("nombre");
				String abreviatura = rs.getString("iniciales");

				Equipo e = new Equipo(id, nombre, abreviatura);
				equipos.add(e);
			}

			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de equipos desde la base de datos");
		}
		return equipos;
	}
	/**
	 * Adds a new Equipo into the database, in case it does exist or the connection fails, returns false
	 * @param newDeportista the Equipo to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Equipo
	 */
	public boolean addEquipo(Equipo newEquipo) {
		try {
			conexion = new ConnectionDB();
			String sqlAddEquipo = "INSERT INTO Equipo VALUES(" + newEquipo.getId() + "," + newEquipo.getNombre() + "," + newEquipo.getAbreviatura() + ")";
			PreparedStatement pstm = conexion.getConexion().prepareStatement(sqlAddEquipo);
			pstm.executeUpdate(sqlAddEquipo);
			conexion.closeConexion();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	/**
	 * Modifies the oldEquipo entry from the database with the values for the newEquipo.
	 * @param oldEquipo the instance to be modified
	 * @param newEquipo the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the connection.
	 */

	public void modificarEquipo(Equipo oldEquipo, Equipo newEquipo) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " 
					+ "SET id_equipo=" + newEquipo.getId() + "," 
					+ "nombre='" + newEquipo.getNombre() + "',"
					+ "iniciales='" + newEquipo.getAbreviatura()
					+ " WHERE id=" + oldEquipo.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	

	/**
	 * Retrieves all the events from the database's Evento table as an ObservableList
	 * @return ObservableList with all the events.
	 * @see net.urtzi.olimpiadas.model.Evento
	 */
	public ObservableList<Evento> cargarEventos() {

		ObservableList<Evento> eventos = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT ev.*, ol.nombre as 'nombre_olimpiada',ol.anio,ol.temporada,ol.ciudad, de.nombre AS 'nombre_deporte' FROM Evento ev, Olimpiada ol, Deporte de WHERE ol.id_olimpiada = ev.id_olimpiada AND de.id_deporte = ev.id_deporte";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_evento");
				String nombre = rs.getString("nombre");
				Olimpiada ol = new Olimpiada(rs.getInt("id_olimpiada"), rs.getString("nombre_olimpiada"),
						rs.getInt("anio"), rs.getString("temporada"), rs.getString("ciudad"));
				Deporte de = new Deporte(rs.getInt("id_deporte"), rs.getString("nombre_deporte"));
				Evento e = new Evento(id, nombre, ol, de);
				eventos.add(e);
			}

			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de eventos desde la base de datos");
		}
		return eventos;
	}

	/**
	 * Retrieves the data from the Olimpiada table in the database and returns an
	 * ObservableList all entries in it.
	 * @return ObservableList with the Olimpiada objects from the database.
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */
	public ObservableList<Olimpiada> cargarOlimpiadas() {
		ObservableList<Olimpiada> olimpiadas = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String consulta = "SELECT * FROM Olimpiada";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_olimpiada");
				String nombre = rs.getString("nombre");
				int anio = rs.getInt("anio");
				String temporada = rs.getString("temporada");
				String ciudad = rs.getString("ciudad");
				Olimpiada d = new Olimpiada(id, nombre, anio, temporada, ciudad);
				olimpiadas.add(d);
			}

			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de olimpiadas desde la base de datos");
		}
		return olimpiadas;
	}
	/**
	 * Adds a new Olimpiada into the database, in case it does exist or the connection fails, returns false
	 * @param newOlimpiada the Olimpiada to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */
	public void addOlimpiada(Olimpiada newOlimpiada) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddOlimpiada = "INSERT INTO Olimpiada VALUES(" + newOlimpiada.getId() + "," + newOlimpiada.getNombre() + "," + newOlimpiada.getAnio() + "," + newOlimpiada.getTemporada() + "," + newOlimpiada.getCiudad() + ")";
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddOlimpiada);
		pstm.executeUpdate(sqlAddOlimpiada);
		conexion.closeConexion();
	}

	/**
	 * Retrieves the data from the Participacion table in the database and returns
	 * an ObservableList all entries in it.
	 * @return ObservableList with the Participacion objects from the database.
	 * @see net.urtzi.olimpiadas.models.Participacion
	 */
	public ObservableList<Participacion> cargarParticipaciones() {
		ObservableList<Participacion> participaciones = FXCollections.observableArrayList();
		try {
			conexion = new ConnectionDB();
			String joderVayaConsulta = "SELECT pa.*, de.nombre AS 'nombre_deportista', de.sexo, de.peso, de.altura, ev.id_evento, ev.nombre AS 'nombre_evento', ev.id_olimpiada AS 'id_olimpiada_evento', ev.id_deporte AS 'id_deporte_evento', eq.id_equipo, eq.nombre AS 'nombre_equipo', eq.iniciales, ol.id_olimpiada, ol.nombre AS 'nombre_olimpiada', ol.anio, ol.temporada, ol.ciudad, dep.id_deporte, dep.nombre AS 'nombre_deporte' FROM Participacion pa, Deportista de, Evento ev, Equipo eq, Olimpiada ol, Deporte dep WHERE pa.id_deportista = de.id_deportista AND pa.id_evento = ev.id_evento AND pa.id_equipo = eq.id_equipo AND ev.id_olimpiada = ol.id_olimpiada AND dep.id_deporte = ev.id_deporte; ";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(joderVayaConsulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_deporte");

				Deportista dep = new Deportista(rs.getInt("id_deportista"), rs.getString("nombre_deportista"),
						rs.getString("sexo").charAt(0), rs.getInt("peso"), rs.getInt("altura"));
				Olimpiada ol = new Olimpiada(rs.getInt("id_olimpiada"), rs.getString("nombre_olimpiada"),
						rs.getInt("anio"), rs.getString("temporada"), rs.getString("ciudad"));
				Deporte depo = new Deporte(rs.getString("nombre_deporte"));
				Evento ev = new Evento(rs.getString("nombre_evento"), ol, depo);
				Equipo eq = new Equipo(rs.getString("nombre_equipo"), rs.getString("iniciales"));
				int edad = rs.getInt("edad");
				String medalla = rs.getString("medalla");
				Participacion p = new Participacion(dep, ev, eq, edad, medalla);
				participaciones.add(p);
			}
			rs.close();
			conexion.closeConexion();

		} catch (SQLException e) {
			System.out.println("Error en la carga de participaciones desde la base de datos");
			System.out.println(e);
		}
		return participaciones;
	}
	/**
	 * Adds a new Participacion into the database, in case it does exist or the connection fails, throws and exception.
	 * @param newOlimpiada the Participacion to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Participacion
	 */
	public void addOlimpiada(Participacion newParticipacion) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddParticipacion = "INSERT INTO Participacion VALUES(" + newParticipacion.getDeportista().getId() + "," + newParticipacion.getEvento().getId() + "," + newParticipacion.getEquipo().getId() + "," + newParticipacion.getEdad() + "," + newParticipacion.getMedalla()+ ")";
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddParticipacion);
		pstm.executeUpdate(sqlAddParticipacion);
		conexion.closeConexion();
	}

}