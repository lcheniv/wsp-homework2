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

import com.sun.javafx.text.TextLine;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class NumberInformationWidget extends GridPane {

    public NumberInformationWidget(){

        //-----------------NUMBER INFORMATION WIDGET-------------------------------------------------------------------

        // Blank widget, super class of all classes
        Region r = new Region();

        // CREATING REGIONS TO CREATE SPACING AND SEPERATIONS
        add(r, 3, 0);

        // To allow user to enter single-line text entry
        //TextLine inputNumber = new TextLine(0, 50, , 10, 2, 2, 0, 2);
        TextField inputNumber = new TextField("Type a number here . . .");
        add(inputNumber, 0,0);

        // COMBOX BOX w/ EVEN & PRIME
        ComboBox<String> options = new ComboBox<String>();
        options.getItems().addAll("Even", "Prime");
        add(options, 1,0 );

        // SINGLE READ-ONLY TEXT AREA
        TextArea output = new TextArea();
        add(output, 0, 1);
        output.setDisable(true);

        // RUN BUTTON
        Button checkNumber = new Button("Run");
        add(checkNumber, 2, 0);
        checkNumber.setOnAction(
                event -> {

                    if(options.getValue().equals("Even")) {

                        System.out.println("Even test");

                    } else if(options.getValue().equals("Prime")) {

                        System.out.println("Prime test");

                    } else {

                        System.out.println("Please select an option from the dropdown menu . . .");

                    }
                }


        );













    }

}
