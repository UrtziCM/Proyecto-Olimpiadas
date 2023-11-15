module Olimpiadas {
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	requires java.sql;
	
	opens net.urtzi.olimpiadas.application to javafx.controls, javafx.base, javafx.fxml, javafx.graphics;
	opens net.urtzi.olimpiadas.controller to javafx.controls, javafx.base, javafx.fxml, javafx.graphics;
	opens net.urtzi.olimpiadas.models to javafx.base;
}