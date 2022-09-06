import java.io.IOException;
import java.sql.SQLException;

import controller.LoginController;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;

import model.Model;

public class Main extends Application {
	private Model model;


	public void init() throws Exception {
		model = new Model();
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			model.setup();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));

			Callback<Class<?>, Object> controllerFactory = param -> {
				return new LoginController(stage, model);
			};

			loader.setControllerFactory(controllerFactory);

			Pane root = loader.load();

			LoginController loginController = loader.getController();
			loginController.showStage(root);

		} catch (IOException | SQLException | RuntimeException e) {
			Scene scene = new Scene(new Label(e.getMessage()), 200, 100);
			stage.setTitle("Error");
			stage.setScene(scene);
			stage.show();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}

