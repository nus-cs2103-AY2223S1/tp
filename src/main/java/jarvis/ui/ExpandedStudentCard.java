package jarvis.ui;

import jarvis.model.GradeProfile;
import jarvis.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays the full information of a {@code Student}.
 */
public class ExpandedStudentCard extends UiPart<Region> {

    private static final String FXML = "ExpandedStudentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label studentName;
    @FXML
    private Label id;
    @FXML
    private Label matricNum;

    // TableView to display grades
    @FXML
    private TableView<GradeProfile> gradeProfile;
    @FXML
    private TableColumn<GradeProfile, String> mc1;
    @FXML
    private TableColumn<GradeProfile, String> mc2;
    @FXML
    private TableColumn<GradeProfile, String> ra1;
    @FXML
    private TableColumn<GradeProfile, String> ra2;
    @FXML
    private TableColumn<GradeProfile, String> midterm;
    @FXML
    private TableColumn<GradeProfile, String> practicalAssessment;
    @FXML
    private TableColumn<GradeProfile, String> finalAssessment;
    @FXML
    private TableColumn<GradeProfile, String> studioAttendance;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ExpandedStudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        studentName.setText(student.getName().fullName);
        matricNum.setText(student.getMatricNum().value);

        ObservableList<GradeProfile> list = FXCollections.observableArrayList(student.getGradeProfile());
        mc1.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("mc1"));
        mc2.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("mc2"));
        ra1.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("ra1"));
        ra2.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("ra2"));
        midterm.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("midterm"));
        practicalAssessment.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("practicalAssessment"));
        finalAssessment.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("finalAssessment"));
        studioAttendance.setCellValueFactory(new PropertyValueFactory<GradeProfile, String>("studioAttendance"));

        gradeProfile.setItems(list);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpandedStudentCard)) {
            return false;
        }

        // state check
        ExpandedStudentCard card = (ExpandedStudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
