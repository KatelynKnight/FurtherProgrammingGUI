package controller;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;
import model.User;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML // fx:id="Close"
    private Button Close; // Value injected by FXMLLoader

    @FXML // fx:id="ErrorMessage"
    private Label ErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="LInkToRegistration"
    private Hyperlink LInkToRegistration; // Value injected by FXMLLoader

    @FXML // fx:id="Password"
    private PasswordField Password; // Value injected by FXMLLoader

    @FXML // fx:id="SignIn"
    private Button SignIn; // Value injected by FXMLLoader

    @FXML // fx:id="Username"
    private TextField Username; // Value injected by FXMLLoader

    private Stage stage;
    private Model model;


	public LoginController(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
	}



	@FXML
	public void initialize() {
		SignIn.setOnAction(event -> {

			if (!Username.getText().isEmpty() && !Password.getText().isEmpty()) {
				User user;
				try {
					user = model.getUserDao().getUser(Username.getText(), Password.getText());
					if (user != null) {
						model.setCurrentUser(user);
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));

							Callback<Class<?>, Object> controllerFactory = param -> {
								return new SmartCanvasController(stage, model);
							};

							loader.setControllerFactory(controllerFactory);
							Pane root = loader.load();

							SmartCanvasController smartCanvasController = loader.getController();
							smartCanvasController.showStage(root, 600, 600);

							stage.close();
						} catch (IOException e) {

						}
					} else {
						ErrorMessage.setText("Wrong username or password");
						ErrorMessage.setTextFill(Color.RED);
					}

				
				}
				catch (SQLException e) {
		            e.printStackTrace();
		        }
				
			} else {
				ErrorMessage.setText("Empty username or password");
			}
			Username.clear();
			Password.clear();
		});
		Close.setOnAction(Event -> {
			Platform.exit();



		});
		LInkToRegistration.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registration.fxml"));

				Callback<Class<?>, Object> controllerFactory = param -> {
					return new RegistrationController(stage, model);
				};

				loader.setControllerFactory(controllerFactory);
				Pane root = loader.load();

				RegistrationController registrationController = loader.getController();
				registrationController.showStage(root);

				stage.close();
			} catch (IOException e) {

			}});

			}








	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome to Smart Canvas");
		stage.show();
	}
    
    

}
