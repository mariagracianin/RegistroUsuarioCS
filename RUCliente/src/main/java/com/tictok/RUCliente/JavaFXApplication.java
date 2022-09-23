package com.tictok.RUCliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class JavaFXApplication extends Application {

	private Parent root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(Main.getContext()::getBean);
		root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
		primaryStage.setTitle("Login");
		Scene escena = new Scene(root);
		escena.getStylesheets().add(getClass().getResource("CSS.css").toExternalForm());
		primaryStage.setScene(escena);
		primaryStage.show();
	}

	@Override
	public void stop(){
		Main.getContext().close();
	}

}
