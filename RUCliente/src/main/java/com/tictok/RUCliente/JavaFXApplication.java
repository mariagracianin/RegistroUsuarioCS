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
		root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("vista1_archivo.fxml"));
		primaryStage.setTitle("Empresa");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	@Override
	public void stop(){
		Main.getContext().close();
	}

}
