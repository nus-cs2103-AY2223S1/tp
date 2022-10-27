package seedu.taassist.ui;

import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

/**
 * An UI component that displays information of a {@code StudentView}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final StudentView studentView;
    public final int index;
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private TitledPane titledPane;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane classes;

    /**
     * Creates a {@code StudentCard} with the given {@code StudentView} and index to display.
     */
    public StudentCard(StudentView studentView, int index) {
        super(FXML);

        this.studentView = studentView;
        this.index = index;

        Student student = studentView.getStudent();

        // Set title of the titled pane.
        HBox title = getTitleHBox(index + ". " + student.getName().fullName);
        handleGradeLabel(title);
        title.prefWidthProperty().bind(titledPane.widthProperty());

        titledPane.setGraphic(title);
        titledPane.setExpanded(false);

        // Show other fields in the titled pane content.
        student.getModuleClasses().stream()
            .sorted(Comparator.comparing(moduleClass -> moduleClass.getClassName()))
            .forEach(moduleClass -> classes.getChildren().add(new Label(moduleClass.getClassName())));
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
    }

    /**
     * If the {@code studentView} has a session and the session has been graded,
     * then appends a label containing the grade to {@code title}. If the session
     * is not graded then the {@code titledPane} is given a CSS class "not-graded".
     * If there is no session associated with the student, then nothing happens.
     */
    private void handleGradeLabel(HBox title) {
        if (!studentView.hasSession()) {
            return;
        }
        Optional<SessionData> sessionData = studentView.getSessionData();
        if (sessionData.isEmpty()) {
            titledPane.getStyleClass().add("not-graded");
        } else {
            double grade = sessionData.get().getGrade();
            title.getChildren().addAll(
                getSeparator(),
                new Label(String.valueOf(grade))
            );
        }
    }

    /**
     * Returns a HBox containing the label.
     */
    private HBox getTitleHBox(String label) {
        HBox title = new HBox();
        title.setSpacing(10);
        title.setPadding(new Insets(0, 10, 0, 0));
        HBox.setHgrow(title, Priority.ALWAYS);

        title.getChildren().add(new Label(label));
        return title;
    }

    /**
     * Returns a HBox that acts as a separator.
     */
    private HBox getSeparator() {
        HBox separator = new HBox();
        HBox.setHgrow(separator, Priority.ALWAYS);
        return separator;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return index == card.index
                && studentView.equals(card.studentView);
    }
}
