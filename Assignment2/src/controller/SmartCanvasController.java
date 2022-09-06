package controller;

/**
 * Sample Skeleton for 'SmartCanvas.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SmartCanvasController {

    @FXML // fx:id="insertCircle"
    private Button insertCircle; // Value injected by FXMLLoader

    @FXML // fx:id="clearCanvas"
    private MenuItem clearCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="insertImage"
    private Button insertImage; // Value injected by FXMLLoader

    @FXML // fx:id="saveAs"
    private MenuItem saveAs; // Value injected by FXMLLoader

    @FXML // fx:id="insertCanvas"
    private Button insertCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="updateCanvas"
    private Button updateCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="about"
    private MenuItem about; // Value injected by FXMLLoader

    @FXML // fx:id="delete"
    private MenuItem delete; // Value injected by FXMLLoader

    @FXML // fx:id="zoomSlider"
    private Slider zoomSlider; // Value injected by FXMLLoader

    @FXML // fx:id="insertText"
    private Button insertText; // Value injected by FXMLLoader

    @FXML // fx:id="insertRectangle"
    private Button insertRectangle; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader

    @FXML // fx:id="displayProfilePicture"
    private ImageView displayProfilePicture; // Value injected by FXMLLoader

    @FXML // fx:id="newCanvas"
    private MenuItem newCanvas; // Value injected by FXMLLoader

    @FXML // fx:id="topMenu"
    private MenuBar topMenu; // Value injected by FXMLLoader
    @FXML // fx:id="canvas"
    private Pane canvas; // Value injected by FXMLLoader
    @FXML
    void bcb4b4(ActionEvent event) {

    }

    @FXML // fx:id="userName"
    private Label userName; // Value injected by FXMLLoader

    private Stage stage;
    private Stage parentStage;
    private Model model;
    private Pane pane;

    private double originX;
    private double originY;

    final FileChooser fc = new FileChooser();

    public SmartCanvasController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }





    @FXML
    public void initialize() {
        displayProfilePicture.setImage(new Image(model.getCurrentUser().getImage().toString()));
        userName.setText(model.getCurrentUser().getFirstname() + " "+  model.getCurrentUser().getLastname());

        logout.setOnAction(event -> {
            stage.close();
            parentStage.show();
            Pane canvas = new Pane();


        });
        newCanvas.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewCanvas.fxml"));

                Callback<Class<?>, Object> controllerFactory = param -> {
                    return new NewCanvasController(stage, model);
                };

                loader.setControllerFactory(controllerFactory);
                Pane root = loader.load();

                NewCanvasController newCanvasController = loader.getController();
                newCanvasController.showStage(root);

            } catch (IOException e) {

            }


        });
        profile.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserProfile.fxml"));

                Callback<Class<?>, Object> controllerFactory = param -> {
                    return new ProfileController(stage, model);
                };

                loader.setControllerFactory(controllerFactory);
                Pane root = loader.load();

                ProfileController profileController = loader.getController();
                profileController.showStage(root);

            } catch (IOException e) {

            }


        });
    }

    public void handleImage(ActionEvent event) throws IOException {
        ImageView image = new ImageView();
        image.setX(200);
        image.setY(200);


        canvas.getChildren().addAll(image);
        fc.setTitle("Please Open a image file");

        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png" , "*.jpg","*.gif"));


        File file =fc.showOpenDialog(null);
        if (file != null) {
            image.setImage(new Image(file.toURI().toString(), 150, 150, false, false));

        }

        image.setOnMousePressed(mouseEvent -> {

            originX = mouseEvent.getX();
            originY = mouseEvent.getY();


        });

        image.setOnMouseDragged(mouseEvent -> {

            double dragPointX = mouseEvent.getX();
            double dragPointY = mouseEvent.getY();

            image.setX(dragPointX);
            image.setY(dragPointY);

        });

        }

    public void handleCircle(ActionEvent event) throws IOException {



            Circle circle = new Circle(30, Color.BLUE);
            circle.setCenterX(300);
            circle.setCenterY(300);

            canvas.getChildren().addAll(circle);

            circle.setOnMousePressed(mouseEvent -> {

                originX = mouseEvent.getX();
                originY = mouseEvent.getY();


            });

            circle.setOnMouseDragged(mouseEvent -> {
                double dragPointX = mouseEvent.getX();
                double dragPointY = mouseEvent.getY();

                circle.setCenterX(dragPointX);
                circle.setCenterY(dragPointY);

            });


    }

    public  void handleRectangle(ActionEvent event) throws IOException{
        Rectangle rectangle = new Rectangle(100,100);
        rectangle.setX(250);
        rectangle.setY(250);
        canvas.getChildren().addAll(rectangle);

        rectangle.setOnMousePressed(mouseEvent -> {

            originX = mouseEvent.getX();
            originY = mouseEvent.getY();


        });

        rectangle.setOnMouseDragged(mouseEvent -> {
            double dragPointX = mouseEvent.getX();
            double dragPointY = mouseEvent.getY();

            rectangle.setX(dragPointX);
            rectangle.setY(dragPointY);

        });


    }

   public void handleText(ActionEvent event) throws IOException{
       Label label = new Label("Text");
       label.setLayoutX(250);
       label.setLayoutY(250);
       canvas.getChildren().addAll(label);

       label.setOnMousePressed(mouseEvent -> {

           originX = mouseEvent.getX();
           originY = mouseEvent.getY();


       });

       label.setOnMouseDragged(mouseEvent -> {
           double dragPointX = mouseEvent.getX();
           double dragPointY = mouseEvent.getY();

           label.setLayoutX(dragPointX);
           label.setLayoutY(dragPointY);

       });

   }
    public void showStage(Pane root , double width  , double height) {
        Scene scene = new Scene(root, 924, 617);
        canvas.setPrefSize(width, height);
        canvas.setStyle("-fx-background-color: white;");
        if (canvas.contains(width,height)){
            insertCircle.setDisable(true);
            insertImage.setDisable(true);
            insertRectangle.setDisable(true);
            updateCanvas.setDisable(true);
            insertText.setDisable(true);

        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("SmartCanvas");
        stage.show();
    }


}
