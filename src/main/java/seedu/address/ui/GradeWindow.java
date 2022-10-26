package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.auth.AuthHandler;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Controller for a help page
 */
public class GradeWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(GradeWindow.class);
    private static final String FXML = "GradeWindow.fxml";
    private String assessmentString;
    private int index = 0;
    List<Person> personList;

    @FXML
    private Button submitButton;

    @FXML
    private Label assessmentName;
    @FXML
    private Label assessmentWeightage;
    @FXML
    private Label studentClass;
    @FXML
    private Label studentName;
    @FXML
    private Label assessmentSubject;
    @FXML
    private Label assessmentTotalScore;



    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public GradeWindow(Stage root) {
        super(FXML, root);
    }



    /**
     * Creates a new HelpWindow.
     */
    public GradeWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show(List<Person> personList, String assessmentString) {
        logger.fine("Showing help page about the application.");
        this.personList = personList;
        this.assessmentString = assessmentString;
        getRoot().show();
        getRoot().centerOnScreen();
        updateUI();

    }
    public void updateUI() {
        if (index > personList.size()) {
            return;
        }
        //parse assesment string
        String[] parsedString = assessmentString.split("_");
        String subject = parsedString[0].trim();
        String name = parsedString[1].trim();;
        String totalScore = parsedString[2].trim();;
        String weightage = parsedString[3].trim();;
        Person currentPerson = personList.get(index);
        assessmentSubject.setText("Subject: " + subject);
        assessmentName.setText("Assessment: " + name);
        assessmentWeightage.setText("Weightage: " + weightage);
        assessmentTotalScore.setText("Total Score: " + totalScore);
        studentName.setText("Student Name: " + currentPerson.getName().toString());
        studentClass.setText("Student Class: " + "TBC");
        return;
    }

    public void updateGradesForCurrentStudent(String mark) {

    }
    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    @FXML
    public void enterGradeForStudent() {
        System.out.println("Grade entered for: example");
        index += 1;
        updateUI();
    }

}
