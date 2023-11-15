package net.urtzi.olimpiadas.controller.databasemanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.urtzi.olimpiadas.models.*;

/**
 * Database manager class uses a ConnectionDB to access a database and retrieve
 * data.
 * 
 * @see net.urtzi.olimpiadas.controller.database.ConnectionDB
 */
public class DBManager {
	private ConnectionDB conexion;

	/**
	 * Retrieves the data from the Deporte table in the database and returns an
	 * ObservableList with it.
	 * 
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
		}
		return deportes;
	}

	/**
	 * Uses a connection to the database to add a Deporte obect to the database
	 * 
	 * @param newDeporte the Deporte to be added
	 * @throws SQLException in case the Deporte already exists or the connection
	 *                      raises errors.
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */
	public void addDeporte(Deporte newDeporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement pstm = conexion.getConexion().createStatement();
		String sqlAddDeporte;
		if (newDeporte.getId() != -1) {
			sqlAddDeporte = "REPLACE INTO Deporte VALUES(" + newDeporte.getId() + "," + newDeporte.getNombre() + ")";
		} else {
			sqlAddDeporte = "REPLACE INTO Deporte(nombre) VALUES('" + newDeporte.getNombre() + "')";
		}
		pstm.executeUpdate(sqlAddDeporte);
		conexion.closeConexion();
	}

	/**
	 * Uses a connection to the database to delete a Deporte object from the
	 * database
	 * 
	 * @param deporte the Deporte to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has
	 *                      errors .
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */
	public void borrarDeporte(Deporte deporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Deporte WHERE id_deporte=" + deporte.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	/**
	 * Modifies the oldDeporte entry from the database with the values fo
	 * newDeporte-
	 * 
	 * @param oldDeporte the instance to be modified
	 * @param newDeporte the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */

	public void modificarDeporte(Deporte oldDeporte, Deporte newDeporte) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET nombre='" + newDeporte.getNombre() + "'," + "id_deporte="
				+ newDeporte.getId() + " WHERE id_deporte=" + oldDeporte.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Returns a Deporte Object with the data from the database where the item has the given ID
	 * 
	 * @param id the ID of the item in the database
	 * @see net.urtzi.olimpiadas.models.Deporte
	 */

	public Deporte getDeporteByID(int id) throws SQLException {
		conexion = new ConnectionDB();
		Deporte dep = null;
		Statement stmt = conexion.getConexion().createStatement();
		String sql = String.format("SELECT * FROM Deporte WHERE id_deporte = %d", id);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			dep = new Deporte(rs.getInt(1),rs.getString("nombre"));
		}
		conexion.closeConexion();
		return dep;
	}

	/**
	 * Retrieves the data from the Deportista table in the database and returns an
	 * ObservableList all entries in it.
	 * 
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
	 * Adds a new Deportista into the database, in case it does exist or the
	 * connection fails, returns false
	 * 
	 * @param newDeportista the Deportista to be added into the database.
	 * @throws SQLException if the Deportista does exist or the connection raised
	 *                      errors.
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public void addDeportista(Deportista newDeportista) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddDeportista;
		if (newDeportista.getId() != -1) {
			sqlAddDeportista = "REPLACE INTO Deportista VALUES(" + newDeportista.getId() + ",'"
					+ newDeportista.getNombre() + "','" + newDeportista.getSexo() + "'," + newDeportista.getPeso() + ","
					+ newDeportista.getAltura() + ")";
		} else {
			sqlAddDeportista = "REPLACE INTO Deportista (nombre,sexo,peso,altura) VALUES('" + newDeportista.getNombre()
					+ "','" + newDeportista.getSexo() + "'," + newDeportista.getPeso() + "," + newDeportista.getAltura()
					+ ")";
		}
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddDeportista);
		pstm.executeUpdate(sqlAddDeportista);
		conexion.closeConexion();
	}

	/**
	 * Modifies the oldDeportista entry from the database with the values for the
	 * newDeportista.
	 * 
	 * @param oldDeportista the instance to be modified
	 * @param newDeportista the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */

	public void modificarDeportista(Deportista oldDeportista, Deportista newDeportista) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET id_deportista=" + newDeportista.getId() + "," + "nombre='"
				+ newDeportista.getNombre() + "'," + "sexo='" + newDeportista.getSexo() + "'," + "peso="
				+ newDeportista.getPeso() + "," + "altura=" + newDeportista.getAltura() + " WHERE id_deportista="
				+ oldDeportista.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Uses a connection to the database to delete a Deportista object from the
	 * database
	 * 
	 * @param deportista the Deportista to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public void borrarDeportista(Deportista deportista) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Deportista WHERE id_deportista=" + deportista.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Returns a Deportista Object with the data from the database where the item has the given ID
	 * 
	 * @param id the ID of the item in the database
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */

	public Deportista getDeportistaByID(int id) throws SQLException {
		conexion = new ConnectionDB();
		Deportista dep = null;
		Statement stmt = conexion.getConexion().createStatement();
		String sql = String.format("SELECT * FROM Deportista WHERE id_deportista = %d", id);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			dep = new Deportista(rs.getInt(1),rs.getString(2),rs.getString(3).charAt(0),rs.getInt(4),rs.getInt(5));
		}
		conexion.closeConexion();
		return dep;
	}

	/**
	 * Retrieves the data from the Deportista table in the database and returns an
	 * ObservableList all entries in it.
	 * 
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
	 * Adds a new Equipo into the database, in case it does exist or the connection
	 * fails, returns false
	 * 
	 * @param newDeportista the Equipo to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Equipo
	 */
	public boolean addEquipo(Equipo newEquipo) {
		try {
			conexion = new ConnectionDB();
			String sqlAddEquipo;
			if (newEquipo.getId() != -1) {
				sqlAddEquipo = "REPLACE INTO Equipo VALUES(" + newEquipo.getId() + ",'" + newEquipo.getNombre() + "','"
						+ newEquipo.getAbreviatura() + "')";
			} else {
				sqlAddEquipo = "REPLACE INTO Equipo(nombre,abreviatura) VALUES('" + newEquipo.getNombre() + "','"
						+ newEquipo.getAbreviatura() + "')";
			}
			PreparedStatement pstm = conexion.getConexion().prepareStatement(sqlAddEquipo);
			pstm.executeUpdate(sqlAddEquipo);
			conexion.closeConexion();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Modifies the oldEquipo entry from the database with the values for the
	 * newEquipo.
	 * 
	 * @param oldEquipo the instance to be modified
	 * @param newEquipo the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Equipo
	 */

	public void modificarEquipo(Equipo oldEquipo, Equipo newEquipo) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET id_equipo=" + newEquipo.getId() + "," + "nombre='" + newEquipo.getNombre()
				+ "'," + "iniciales='" + newEquipo.getAbreviatura() + "' " + "WHERE id_equipo=" + oldEquipo.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Uses a connection to the database to delete a Equipo object from the
	 * database
	 * 
	 * @param equipo the Equipo to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Deportista
	 */
	public void borrarEquipo(Equipo equipo) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Equipo WHERE id_equipo=" + equipo.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Returns a Equipo Object with the data from the database where the item has the given ID
	 * 
	 * @param id the ID of the item in the database
	 * @see net.urtzi.olimpiadas.models.Equipo
	 */

	public Equipo getEquipoByID(int id) throws SQLException {
		conexion = new ConnectionDB();
		Equipo equ = null;
		Statement stmt = conexion.getConexion().createStatement();
		String sql = String.format("SELECT * FROM Equipo WHERE id_equipo = %d", id);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			equ = new Equipo(rs.getInt(1), rs.getString("nombre"), rs.getString("iniciales"));
		}
		conexion.closeConexion();
		return equ;
	}

	/**
	 * Retrieves all the events from the database's Evento table as an
	 * ObservableList
	 * 
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
	 * Adds a new Evento into the database, in case it does exist throws an
	 * exception.
	 * 
	 * @param newEvento the Evento to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Evento
	 */
	public void addEvento(Evento newEvento) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddEvento;
		if (newEvento.getId() != -1) {
			sqlAddEvento = "REPLACE INTO Evento VALUES(" + newEvento.getId() + ",'" + newEvento.getNombre() + "',"
					+ newEvento.getOlimpiada().getId() + "," + newEvento.getDeporte().getId() + ")";
		} else {
			sqlAddEvento = "REPLACE INTO Evento (nombre,id_olimpiada,id_deporte) VALUES('" + newEvento.getNombre() + "',"
					+ newEvento.getOlimpiada().getId() + "," + newEvento.getDeporte().getId() + ")";
		}
		PreparedStatement pstm = conexion.getConexion().prepareStatement(sqlAddEvento);
		pstm.executeUpdate(sqlAddEvento);
		conexion.closeConexion();
	}

	/**
	 * Modifies the oldEvento entry from the database with the values for the
	 * newEvento.
	 * 
	 * @param oldEvento the instance to be modified
	 * @param newEvento the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Evento
	 */

	public void modificarEvento(Evento oldEvento, Evento newEvento) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET id_equipo=" + newEvento.getId() + "," + "nombre='" + newEvento.getNombre()
				+ "'," + "id_olimpiada=" + newEvento.getOlimpiada().getId() + "," + "id_deporte="
				+ newEvento.getDeporte().getId() + " " + "WHERE id_evento=" + oldEvento.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Uses a connection to the database to delete a Evento object from the
	 * database
	 * 
	 * @param evento the Evento to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Evento
	 */
	public void borrarEvento(Evento evento) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Evento WHERE id_evento=" + evento.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Returns a Evento Object with the data from the database where the item has the given ID
	 * 
	 * @param id the ID of the item in the database
	 * @see net.urtzi.olimpiadas.models.Equipo
	 */

	public Evento getEventoByID(int id) throws SQLException {
		conexion = new ConnectionDB();
		Evento eve = null;
		Statement stmt = conexion.getConexion().createStatement();
		String sql = String.format("SELECT * FROM Evento WHERE id_evento = %d", id);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			eve = new Evento(rs.getInt("id_evento"), rs.getString("nombre"), getOlimpiadaByID(rs.getInt("id_olimpiada")), getDeporteByID(rs.getInt("id_deporte")));
		}
		conexion.closeConexion();
		return eve;
	}

	/**
	 * Retrieves the data from the Olimpiada table in the database and returns an
	 * ObservableList all entries in it.
	 * 
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
	 * Adds a new Olimpiada into the database, in case it does exist or the
	 * connection fails, returns false
	 * 
	 * @param newOlimpiada the Olimpiada to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */
	public void addOlimpiada(Olimpiada newOlimpiada) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddOlimpiada;
		if (newOlimpiada.getId() != -1)
			sqlAddOlimpiada = "REPLACE INTO Olimpiada VALUES(" + newOlimpiada.getId() + ",'" + newOlimpiada.getNombre()
					+ "'," + newOlimpiada.getAnio() + ",'" + newOlimpiada.getTemporada() + "','"
					+ newOlimpiada.getCiudad() + "')";
		else
			sqlAddOlimpiada = "REPLACE INTO Olimpiada(nombre,anio,temporada,ciudad) VALUES('" + newOlimpiada.getNombre()
					+ "'," + newOlimpiada.getAnio() + ",'" + newOlimpiada.getTemporada() + "','"
					+ newOlimpiada.getCiudad() + "')";
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddOlimpiada);
		pstm.executeUpdate(sqlAddOlimpiada);
		conexion.closeConexion();
	}

	/**
	 * Modifies the oldOlimpiada entry from the database with the values for the
	 * newOlimpiada.
	 * 
	 * @param oldOlimpiada the instance to be modified
	 * @param newOlimpiada the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */

	public void modificarOlimpiada(Olimpiada oldOlimpiada, Olimpiada newOlimpiada) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET id_olimpiada=" + newOlimpiada.getId() + "," + "nombre='"
				+ newOlimpiada.getNombre() + "'," + "anio=" + newOlimpiada.getAnio() + "'," + "temporada='"
				+ newOlimpiada.getTemporada() + "'," + "ciudad='" + newOlimpiada.getCiudad() + "',"
				+ "WHERE id_olimpiada=" + oldOlimpiada.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	/**
	 * Uses a connection to the database to delete a Olimpiada object from the
	 * database
	 * 
	 * @param olimpiada the Olimpiada to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */
	public void borrarOlimpiada(Olimpiada olimpiada) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Olimpiada WHERE id_olimpiada=" + olimpiada.getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}
	
	public Olimpiada getOlimpiadaByID(int id) throws SQLException {
		conexion = new ConnectionDB();
		Olimpiada oli = null;
		Statement stmt = conexion.getConexion().createStatement();
		String sql = String.format("SELECT * FROM Olimpiada WHERE id_olimpiada = %d", id);
		System.out.println(sql);
		stmt.executeQuery(sql);
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			oli = new Olimpiada(rs.getInt("id_olimpiada"),rs.getString("nombre"), rs.getInt("anio"), rs.getString("temporada"), rs.getString("ciudad"));
		}
		conexion.closeConexion();
		return oli;
	}

	/**
	 * Retrieves the data from the Participacion table in the database and returns
	 * an ObservableList all entries in it.
	 * 
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
				Deportista dep = new Deportista(rs.getInt("id_deportista"), rs.getString("nombre_deportista"),
						rs.getString("sexo").charAt(0), rs.getInt("peso"), rs.getInt("altura"));
				Olimpiada ol = new Olimpiada(rs.getInt("id_olimpiada"), rs.getString("nombre_olimpiada"),
						rs.getInt("anio"), rs.getString("temporada"), rs.getString("ciudad"));
				Deporte depo = new Deporte(rs.getInt("id_deporte"),rs.getString("nombre_deporte"));
				Evento ev = new Evento(rs.getInt("id_evento"),rs.getString("nombre_evento"), ol, depo);
				Equipo eq = new Equipo(rs.getInt("id_equipo"),rs.getString("nombre_equipo"), rs.getString("iniciales"));
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
	 * Adds a new Participacion into the database, in case it does exist or the
	 * connection fails, throws and exception.
	 * 
	 * @param newOlimpiada the Participacion to be added into the database.
	 * @throws SQLException if it already exists or the connection raised errors.
	 * @see net.urtzi.olimpiadas.models.Participacion
	 */
	public void addParticipacion(Participacion newParticipacion) throws SQLException {
		conexion = new ConnectionDB();
		String sqlAddParticipacion = "REPLACE INTO Participacion VALUES(" + newParticipacion.getDeportista().getId()
				+ "," + newParticipacion.getEvento().getId() + "," + newParticipacion.getEquipo().getId() + ","
				+ newParticipacion.getEdad() + ",'" + newParticipacion.getMedalla() + "')";
		Statement pstm = conexion.getConexion().prepareStatement(sqlAddParticipacion);
		pstm.executeUpdate(sqlAddParticipacion);
		conexion.closeConexion();
	}
	
	/**
	 * Uses a connection to the database to delete a Participacion object from the
	 * database
	 * 
	 * @param participacion the Participacion to be erased
	 * @throws SQLException if the connection raises errors or the SQL query has errors .
	 * @see net.urtzi.olimpiadas.models.Olimpiada
	 */
	public void borrarParticipacion(Participacion participacion) throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "DELETE FROM Participacion WHERE id_deportista=%d AND id_evento=%d AND id_equipo=%d AND edad=%d AND medalla='%s'";
		sql = String.format(sql, participacion.getDeportista().getId(), participacion.getEvento().getId(), participacion.getEquipo().getId(), participacion.getEdad(),participacion.getMedalla());
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	/**
	 * Modifies the oldParticipacion entry from the database with the values for the
	 * newParticipacion.
	 * 
	 * @param oldParticipacion the instance to be modified
	 * @param newParticipacion the new version of the object
	 * @throws SQLException if something goes wrong with the SQL syntax or the
	 *                      connection.
	 * @see net.urtzi.olimpiadas.models.Participacion
	 */
	public void modificarParticipacion(Participacion oldParticipacion, Participacion newParticipacion)
			throws SQLException {
		conexion = new ConnectionDB();
		Statement stmt = conexion.getConexion().createStatement();
		String sql = "UPDATE Deporte " + "SET id_deportista=" + newParticipacion.getDeportista().getId() + ","
				+ "id_evento=" + newParticipacion.getEvento().getId() + "," + "id_equipo="
				+ newParticipacion.getEquipo().getId() + "," + "temporada=" + newParticipacion.getEdad() + ","
				+ "ciudad='" + newParticipacion.getMedalla() + "' " + "WHERE id_deportista="
				+ oldParticipacion.getDeportista().getId() + " AND " + "id_evento="
				+ oldParticipacion.getEvento().getId() + " AND " + "id_equipo=" + oldParticipacion.getEquipo().getId();
		stmt.executeUpdate(sql);
		conexion.closeConexion();
	}

	public void addItemToDatabase(Object item) {
		try {
			if (item instanceof Deporte) {
				addDeporte((Deporte) item);
			} else if (item instanceof Deportista) {
				addDeportista((Deportista) item);
			} else if (item instanceof Equipo) {
				addEquipo((Equipo) item);
			} else if (item instanceof Evento) {
				addEvento((Evento) item);
			} else if (item instanceof Olimpiada) {
				addOlimpiada((Olimpiada) item);
			} else if (item instanceof Participacion) {
				addParticipacion((Participacion) item);
			}
		} catch (SQLException ex) {
			System.out.println("Error en la adicion de datos a la base de datos.");
			ex.printStackTrace();
		}
	}

	/**
	 * Returns an ObservableList with all the items in the given database table.
	 * 
	 * @param tableName The name of the table to get the items from.
	 */
	public ObservableList<?> loadFromDatabase(String tableName) {
		tableName = tableName.toLowerCase();
		switch (tableName) {
		case "deporte":
			return cargarDeportes();
		case "deportista":
			return cargarDeportistas();
		case "equipo":
			return cargarEquipos();
		case "evento":
			return cargarEventos();
		case "olimpiada":
			return cargarOlimpiadas();
		case "participacion":
			return cargarParticipaciones();
		default:
			return FXCollections.observableArrayList();
		}
	}

}