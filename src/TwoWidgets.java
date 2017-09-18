///////////////////////////////////////////////
/// NAME: LAWRENCE CHEN                     ///
/// ASSIGNMENT: HW2, COMBO & TEXT CONTROLS  ///
/// PROFESSOR: DAVID BOLDING                ///
/// DUE DATE: SEPTEMBER 19th                ///
///////////////////////////////////////////////

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class TwoWidgets extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // Stages are analagous to windows - pair/similar
    public void start(Stage primaryStage) {

        NumberInformationWidget niw = new NumberInformationWidget();

        primaryStage.setTitle("Homework 2 : Number Information Widget");

        primaryStage.setScene(new Scene(niw, 600, 400));
        primaryStage.show();
    }
}
