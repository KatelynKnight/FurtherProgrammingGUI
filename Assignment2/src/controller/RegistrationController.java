package controller;

import java.io.*;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import model.User;



public class RegistrationController {

    @FXML // fx:id="CreateUserbtn"
    private Button CreateUserbtn; // Value injected by FXMLLoader

    @FXML // fx:id="FirstName"
    private TextField FirstName; // Value injected by FXMLLoader

    @FXML // fx:id="LastName"
    private TextField LastName; // Value injected by FXMLLoader

    @FXML // fx:id="closebtn"
    private Button closebtn; // Value injected by FXMLLoader

    @FXML // fx:id="error"
    private Label error; // Value injected by FXMLLoader

    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="selectImage"
    private Label selectImage; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TextField username; // Value injected by FXMLLoader

    private Stage stage;
	private Stage parentStage;
    private Model model;

	private User user;
	private String filepath;



	final FileChooser fc = new FileChooser();

	public RegistrationController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

    @FXML
    public void initialize() {
        closebtn.setOnAction(event -> {
			stage.close();
			parentStage.show();


		});

		CreateUserbtn.setOnAction(Event -> {
			if (!username.getText().isEmpty() && !password.getText().isEmpty()) {

				try {

					user = model.getUserDao().createUser(username.getText(), password.getText() , FirstName.getText(), LastName.getText(),filepath);
					if (user != null) {
						error.setText("Created " + user.getUsername());
						error.setTextFill(Color.GREEN);
					}
				else {
					error.setText("Cannot create user");
					error.setTextFill(Color.RED);
				}
				} catch (SQLException e) {

				}

			} else {
				error.setText("Empty username or password");
				error.setTextFill(Color.RED);
			}


		});



	}

    @FXML
 public void handleImage(MouseEvent event) throws IOException {
	
	fc.setTitle("Please Open a image file");
	
	fc.setInitialDirectory(new File(System.getProperty("user.home")));
	
	fc.getExtensionFilters().clear();
	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png" , "*.jpg","*.gif"));
	
	
	File file =fc.showOpenDialog(null);
	
	if (file != null) {


		FileInputStream in = new FileInputStream(file.getAbsolutePath());
		FileOutputStream ou = new FileOutputStream("./src/temp/"+file.getName());
		BufferedInputStream bin = new BufferedInputStream(in);
		BufferedOutputStream bou =new BufferedOutputStream(ou);

		File newFile = new File("./src/temp/"+file.getName());

		int b=0;
		while(b!=-1){
			b=bin.read();
			bou.write(b);
		}
		bin.close();
		bou.close();



		image.setImage(new Image(newFile.toURI().toString(), 150, 150, false, false));
		filepath = newFile.getAbsolutePath();




	}
	else {
	    error.setText("A file is invalid");
	}
		}

	public void showStage(Pane root) {
		Scene scene = new Scene(root, 400, 678);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Create a new user");
		stage.show();

		selectImage.setOnMouseEntered(Event -> {
			scene.setCursor(Cursor.HAND);
		});

		selectImage.setOnMouseExited(Event -> {
			scene.setCursor(Cursor.DEFAULT);
		});
	}


}



