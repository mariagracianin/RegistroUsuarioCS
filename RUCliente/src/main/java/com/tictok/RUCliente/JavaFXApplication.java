package com.tictok.RUCliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;

public class JavaFXApplication extends Application {

	private Parent root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(Main.getContext()::getBean);
		root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("login.fxml"));
		primaryStage.setTitle("GETFIT");
		Scene escena = new Scene(root);
		escena.getStylesheets().add("/com/tictok/RUCliente/loginStyle.css");
		primaryStage.setScene(escena);
		primaryStage.getIcons().add(new Image(JavaFXApplication.class.getResourceAsStream("logo.png")));
		primaryStage.show();
		primaryStage.centerOnScreen();
	}

	@Override
	public void stop(){
		Main.getContext().close();
	}

}
