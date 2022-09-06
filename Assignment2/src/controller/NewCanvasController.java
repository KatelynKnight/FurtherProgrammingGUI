//package controller;
//
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import model.Model;
//
//public class NewCanvasController {
//
//    @FXML // fx:id="cancelButton"
//    private Button cancelButton; // Value injected by FXMLLoader
//
//    @FXML // fx:id="heightInput"
//    private TextField heightInput; // Value injected by FXMLLoader
//
//    @FXML // fx:id="okButton"
//    private Button okButton; // Value injected by FXMLLoader
//
//    @FXML // fx:id="widthInput"
//    private TextField widthInput; // Value injected by FXMLLoader
//
//
//    @FXML // fx:id="message"
//    private Label message; // Value injected by FXMLLoader
//
//    private Stage stage;
//    private Stage parentStage;
//    private Model model;
//    private Pane pane;
//
//    public NewCanvasController(Stage parentStage, Model model) {
//        this.stage = new Stage();
//        this.parentStage = parentStage;
//        this.model = model;
//    }
//
//    @FXML
//    public void initialize() {
//        cancelButton.setOnAction(event -> {
//            stage.close();
//            parentStage.show();
//
//
//        });
//        okButton.setOnAction(event -> {
//
//
//            String widthText = widthInput.getText();
//            String heightText = heightInput.getText();
//
//            if (widthText.equals("") || (heightText.equals(""))) {
//                System.out.println("You need to enter width and height");
//            } else {
//
//                try {
//                    double width = Double.parseDouble(widthText);
//                    double height = Double.parseDouble(heightText);
//                    if (width <= 0 || height <= 0) {
//                        System.out.println("You must enter a positive number.");
//                        widthInput.clear();
//                        heightInput.clear();
//                    } else {
//                        System.out.println("Canvas will be " + width + " long and " + height + " tall");
//
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("You must enter a number");
//                    widthInput.clear();
//                    heightInput.clear();
//                }
//            }
//
//        });
//
//
//    }
//
//    public void showStage(Pane root) {
//        Scene scene = new Scene(root, 360, 250);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Create new canvas");
//        stage.show();
//    }
//}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Model;

import java.io.IOException;

public class NewCanvasController {

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="heightInput"
    private TextField heightInput; // Value injected by FXMLLoader

    @FXML // fx:id="okButton"
    private Button okButton; // Value injected by FXMLLoader

    @FXML // fx:id="widthInput"
    private TextField widthInput; // Value injected by FXMLLoader


    @FXML // fx:id="message"
    private Label message; // Value injected by FXMLLoader

    private Stage stage;
    private Stage parentStage;
    private Model model;

    private double width;
    private double height;

    public NewCanvasController(Stage parentStage, Model model) {
        this.stage = new Stage();
        this.parentStage = parentStage;
        this.model = model;
    }

    @FXML
    public void initialize() {
        cancelButton.setOnAction(event -> {
            stage.close();
            parentStage.show();


        });
        okButton.setOnAction(event -> {


            String widthText = widthInput.getText();
            String heightText = heightInput.getText();

            if (widthText.equals("") || (heightText.equals(""))) {
                message.setText("You need to enter width and height");
                message.setTextFill(Color.RED);


            } else {

                try {
                    double width = Double.parseDouble(widthText);
                    double height = Double.parseDouble(heightText);
                    if (width <= 0 || height <= 0) {
                        message.setText("You must enter a positive number.");
                        message.setTextFill(Color.RED);

                        widthInput.clear();
                        heightInput.clear();
                    } else {
                        setWidth(width);
                        setHeight(height);

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SmartCanvas.fxml"));

                            Callback<Class<?>, Object> controllerFactory = param -> {
                                return new SmartCanvasController(stage, model);
                            };

                            loader.setControllerFactory(controllerFactory);
                            Pane root = loader.load();

                            SmartCanvasController smartCanvasController =  loader.getController();
                            smartCanvasController.showStage(root , getWidth() ,getHeight());
                            stage.close();
                            parentStage.close();

                        } catch (IOException e) {

                        }

                    }
                } catch (NumberFormatException e) {
                    message.setText("You must enter a number");
                    message.setTextFill(Color.RED);
                    widthInput.clear();
                    heightInput.clear();
                }
            }

        });


    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void showStage(Pane root) {
        Scene scene = new Scene(root, 360, 250);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Create new canvas");
        stage.show();
    }
}