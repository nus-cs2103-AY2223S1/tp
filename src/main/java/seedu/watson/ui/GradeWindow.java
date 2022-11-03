package seedu.watson.ui;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.watson.commons.core.LogsCenter;
import seedu.watson.logic.Logic;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.subject.Assessment;
import seedu.watson.model.student.subject.Subject;
import seedu.watson.storage.Storage;

/**
 * Controller for a help page
 */
public class GradeWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(GradeWindow.class);
    private static final String FXML = "GradeWindow.fxml";
    private String assessmentString;
    private int index = 0;
    private List<Student> studentList;
    private final Logic logic;
    private final Storage storage;

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

    @FXML
    private TextField enteredScore;

    @FXML
    private Label errorMessage;



    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public GradeWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        this.storage = logic.getStorage();
        enteredScore.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    enterGradeForStudent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    /**
     * Creates a new HelpWindow.
     */
    public GradeWindow(Logic logic) {
        this(new Stage(), logic);
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
    public void show(List<Student> studentList, String assessmentString) {
        logger.fine("Showing the grading page.");
        this.studentList = studentList;
        this.assessmentString = assessmentString;
        startGrading();
        updateUiToNextStudent();
    }

    /**
     * updates UI Labels to the next student to be updated
     */
    public void updateUiToNextStudent() {
        if (index > studentList.size() - 1) {
            closeWindowAndResetIndex();
            return;
        }
        errorMessage.setText("");
        // parse assessment string
        String[] parsedString = assessmentString.split("_");
        String subject = parsedString[0].trim().toUpperCase();
        String name = parsedString[1].trim().toUpperCase();
        String totalScore = parsedString[2].trim();
        String weightage = parsedString[3].trim();
        Student currentStudent = studentList.get(index);
        assessmentSubject.setText("Subject: " + subject);
        assessmentName.setText("Assessment: " + name);
        assessmentWeightage.setText("Weightage: " + weightage);
        assessmentTotalScore.setText("Total Score: " + totalScore);
        studentName.setText("Student Name: " + currentStudent.getName().toString());
        studentClass.setText("Student Class: " + currentStudent.getStudentClass());
    }

    /**
     * Updates Grades of current student in focus
     * @param mark score received for the assignment
     */
    public void updateGradesForCurrentStudent(String mark) {
        try {
            String[] parsedString = assessmentString.split("_");
            String subjectName = parsedString[0].trim().toUpperCase();
            String name = parsedString[1].trim().toUpperCase();
            double score = Double.parseDouble(mark);
            double totalScore = Double.parseDouble(parsedString[2].trim());
            double weightage = Double.parseDouble(parsedString[3].trim());
            double difficulty = Double.parseDouble(parsedString[4].trim());
            Assessment newAssessment = new Assessment(name, score, totalScore, weightage, difficulty);
            Student currentStudent = studentList.get(index);
            Subject subject = currentStudent.getSubjectHandler().getSubject(subjectName);
            subject.updateGradeAssessment(newAssessment);
            logic.getModel().setPerson(currentStudent, currentStudent);
            storage.saveDatabase(logic.getModel().getDatabase());
            updateIndexToNextStudent();
            updateUiToNextStudent();
        } catch (NumberFormatException e) {
            errorMessage.setText("Please enter a valid number");
        } catch (IllegalArgumentException e) {
            errorMessage.setText(e.getMessage());
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        } catch (NullPointerException e) {
            errorMessage.setText("Please enter a number");
        }
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
    public void updateIndexToNextStudent() {
        index += 1;
    }

    /**
     * Starts the grading process
     */
    public void startGrading() {
        getRoot().show();
        getRoot().centerOnScreen();
        //reset index to 0
        index = 0;
    }
    /**
     * Closes the GradeWindow and resets the index to 0
     */
    public void closeWindowAndResetIndex() {
        hide();
    }
    /**
     * Method to handle when a grade is entered for a student
     */
    @FXML
    public void enterGradeForStudent() throws IOException {
        updateGradesForCurrentStudent(enteredScore.getText());
        enteredScore.clear();
    }
}
