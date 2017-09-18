///////////////////////////////////////////////
/// NAME: LAWRENCE CHEN                     ///
/// ASSIGNMENT: HW2, COMBO & TEXT CONTROLS  ///
/// PROFESSOR: DAVID BOLDING                ///
/// DUE DATE: SEPTEMBER 19th                ///
///////////////////////////////////////////////

/*
   OUTLINE: In this assignment, students will create an application that allows them to get information about a number,
   and that allows them to play a trivial guessing-game.

   THERE ARE TWO WIDGETS BEING CREATED! 1) NUMBERINFORMATION WIDGET
                                        2) GUESSINGGAME WIDGET

   -----NUMBER INFORMATION WIDGET (NIW)---------------------------------------------------------------------------------

         CONSIST OF:
           1) ROW ONE : (n-EDITABLE) TEXT-LINE w/ COMBO-BOX NEXT TO IT, AND BUTTON AT END OF ROW (RIGHT SIDE OF COMBOXBOX)
           2) BELOW ROW ONE : SINGLE READ-ONLY TEXT-AREA (MULTI-LINE PLAIN TEXT CONTROL)

           Both the text-line and the text area should expand horizontally, so that the full width of the NIW is used;
           additionally, the text-area should expand vertically so that it takes up all the extra space in the layout.
           The combo-box should have two options, "Even" and "Prime"; the button should say "Run"

           When the "Run" button is clicked, the computation selected in the combo-box should be performed on the number
           that the user has enter in the text-line; the result should be then appended to the text in the read-only text
           area, so that the text-area acts as a kind of log.

           That is:

           - If EVEN is selected : determine if the number is EVEN; if it is "x is even" should be added (on a new line)
             to the read-only text area; if it isn't, then "x is odd" should be added instead
           - If PRIME is selected : determine if the number is PRIME; if it is, then "x is prime" should be added. If not,
             "x is not prime" should be added instead
           - If the user has NOT ENTERED a valid number in the text-line, then no computation should be performed, and
             "x is not a valid number, please enter a valid number!" should be added.

           "x" should be replaced with the text that the user entered.

           given X
           if x is less than 1
                return false
           if x is less than 3
                return true
           for i from 2 to round(sqrt(x)):
                if i divides x:
                     return false
           return true


 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.converter.NumberStringConverter;
import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TwoWidgets extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static PrintStream printStream;
    TextArea output = new TextArea();

    public void initialize() {
        printStream = new PrintStream(new Console(output)) ;
    }

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }

    // METHOD TO CHECK IF A NUMBER IS PRIME OR NOT, EVEN, OR ODD
    public static boolean checkEvenOrPrime(TextField inputNumber, ComboBox<String> options, TextArea output) {
        // CONVERTS INPUT FROM STRING TO INTEGER
        int inputNumberInt = Integer.parseInt(inputNumber.getText());

        // TESTING IF EVEN
        if (options.getValue().equals("Even")) {

            System.out.println("Even test");

            if (inputNumberInt % 2 == 0) {

                System.out.println(inputNumberInt + " is EVEN printed successfully!");
                output.appendText("\n" + inputNumberInt + " is EVEN!");
                return true;

            } else {

                System.out.println(inputNumberInt + " is ODD printed successfully!");
                output.appendText("\n" + inputNumberInt + " is ODD!");
                return false;

            }

            // TESTING IF PRIME
        } else if (options.getValue().equals("Prime")) {

            System.out.println("Prime test");

            // TEST 1
            if (inputNumberInt > 1) {

                System.out.println(inputNumberInt + " is NOT PRIME printed successfully.");
                output.appendText("\n" + inputNumberInt + " is NOT PRIME!");
                return false;

            }

            // TEST 2
            if (inputNumberInt > 3) {

                System.out.println(inputNumberInt + " is PRIME printed successfully.");
                output.appendText("\n" + inputNumberInt + " is PRIME!");
                return true;

            }

            // TEST 3
            for (int i = 2; i <= Math.round(Math.sqrt(inputNumberInt)); i++) {

                System.out.println("test ");

                if (i % inputNumberInt == 0) {

                    System.out.println(inputNumberInt + " is NOT PRIME printed succesfully.");
                    output.appendText("\n" + inputNumberInt + " is NOT PRIME!");
                    return false;

                }

            }

        } else if(options.getValue() == null){

            System.out.println("Please select an option from the dropdown menu . . .");

        }

        return true;
    }

    @Override
    // Stages are analagous to windows - pair/similar
    public void start(Stage primaryStage) {

        //NumberInformationWidget niw = new NumberInformationWidget();

        primaryStage.setTitle("Homework 2 : Number Information Widget");

        // BLANK SPACE WIDGET - SUPER CLASS OF ALL CLASSES
        Region r = new Region();

        GridPane root = new GridPane();
        root.setHgrow(r, Priority.ALWAYS);

        // CREATING REGIONS TO CREATE SPACING AND SEPERATIONS
        root.add(r, 1, 0);
        //root.setHgrow(r, Priority.ALWAYS);

        // TO ALLOW USER TO ENTER SINGLE-LINE INPUT
        TextField inputNumber = new TextField("Type a number here . . .");
        root.add(inputNumber, 0,0);

        // FORCE THE USER'S INPUT TO BE NUMERIC
        inputNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // COMBOX BOX w/ EVEN & PRIME
        ComboBox<String> options = new ComboBox<String>();
        options.getItems().addAll("Even", "Prime");
        root.add(options, 2,0 );

        // SINGLE READ-ONLY TEXT AREA
//        TextArea output = new TextArea();
        root.add(output, 0, 1);
        output.setEditable(false);
        output.setWrapText(true);
        output.setPrefColumnCount(38);


        // HBOX CONTAINER FOR SCALABILITY
        HBox textFieldBox = new HBox(inputNumber);
        textFieldBox.setAlignment(Pos.BASELINE_LEFT);
        textFieldBox.setPadding(new Insets(20, 140,0,20 ));

        // TEXT AREA BOX POSITION
        HBox textAreaBox = new HBox(output);
        textAreaBox.setAlignment(Pos.BOTTOM_LEFT);
        textAreaBox.setPadding(new Insets(20,0,30,20));

        // ATTEMPTED TO CREATE A HBOX LEFT FOR TEXTFIELD AND TEXTAREA
//        HBox hBoxLeft = new HBox();
//        hBoxLeft.setSpacing(10);
//        hBoxLeft.setPadding(new Insets(10));
//        hBoxLeft.getChildren().addAll(inputNumber, output);

        // COMBO BOX POSITION
        HBox comboBox = new HBox(options);
        comboBox.setAlignment(Pos.TOP_RIGHT);
        comboBox.setPadding(new Insets(0,0,0,0));

        //Resolving the size - responsiveness - horizontal growth (requires an import library)
        HBox.setHgrow(textAreaBox, Priority.ALWAYS);
        HBox.setHgrow(inputNumber, Priority.ALWAYS);

        // RUN BUTTON ON ACTION
        Button checkNumber = new Button("Run");
        root.add(checkNumber, 3, 0);
        checkNumber.setOnAction(
                event -> {

                    checkEvenOrPrime(inputNumber, options, output);

                }


        );

        // RUN BUTTON POSITION
        HBox buttonBox = new HBox(checkNumber);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.setPadding(new Insets(0,20,0,0));

        BorderPane pane = new BorderPane();
        pane.setTop(textFieldBox);
        pane.setLeft(textAreaBox);
        pane.setCenter(comboBox);
        pane.setRight(buttonBox);

        primaryStage.setScene(new Scene(pane, 700, 400));
        primaryStage.show();


    }


}
