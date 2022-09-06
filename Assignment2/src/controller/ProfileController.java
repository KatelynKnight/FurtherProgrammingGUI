package controller;



import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.io.*;
import java.sql.SQLException;

public class ProfileController {

    @FXML // fx:id="Firstname"
    private TextField Firstname; // Value injected by FXMLLoader

    @FXML // fx:id="Lastname"
    private TextField Lastname; // Value injected by FXMLLoader

    @FXML // fx:id="usernameID"
    private Label usernameID; // Value injected by FXMLLoader

    @FXML // fx:id="Okbtn"
    private Button Okbtn; // Value injected by FXMLLoader

    @FXML // fx:id="CancelBtn"
    private Button CancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ProfilePicture"
    private ImageView ProfilePicture; // Value injected by FXMLLoader

    @FXML // fx:id="message"
    private Label message; // Value injected by FXMLLoader

    @FXML // fx:id="selectImage"
    private Label selectImage; // Value injected by FXMLLoader


    private Stage stage;
    private Model model;

    private Stage parentStage;

    private User user;

    private String filepath;


    final FileChooser fc = new FileChooser();


    public ProfileController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    public void initialize() throws SQLException {
        usernameID.setText(model.getCurrentUser().getUsername().toString());
        Firstname.setText(model.getCurrentUser().getFirstname().toString());
        Lastname.setText(model.getCurrentUser().getLastname().toString());
        ProfilePicture.setImage(new Image(model.getCurrentUser().getImage().toString()));





        CancelBtn.setOnAction(event -> {
            stage.close();
            parentStage.show();


        });

        Okbtn.setOnAction(event -> {
            try {
                user = model.getUserDao().updateUser(Firstname.getText(), Lastname.getText(), usernameID.getText());
                if (user == null) {
                }




            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }




        });
    }
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



            ProfilePicture.setImage(new Image(newFile.toURI().toString(), 150, 150, false, false));
            filepath = newFile.getAbsolutePath();







        }
        else {
            message.setTextFill(Color.RED);
            message.setText("A file is invalid");

        }
    }



    public void showStage(Pane root) {
        Scene scene = new Scene(root, 600, 350);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Sign up");
        stage.show();

        selectImage.setOnMouseEntered(Event -> {
            scene.setCursor(Cursor.HAND);
        });

        selectImage.setOnMouseExited(Event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
    }
}
