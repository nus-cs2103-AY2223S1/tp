package seedu.taassist.ui;

import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentView;

/**
 * An UI component that displays information of a {@code StudentView}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentCard.fxml";

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
    private VBox dataFields;
    @FXML
    private FlowPane classes;

    /**
     * Creates a {@code StudentCard} with the given {@code StudentView} and index to display.
     */
    public StudentCard(StudentView studentView, int index) {
        super(FXML);

        this.studentView = studentView;
        this.index = index;

        setTitle();
        setContent();
    }

    private void setTitle() {
        Student student = studentView.getStudent();
        StudentCardTitle title = new StudentCardTitle(index, student.getName().fullName);
        setGradeIfPresent(title);
        title.getRoot().prefWidthProperty().bind(titledPane.widthProperty());
        titledPane.setGraphic(title.getRoot());
        titledPane.setExpanded(false);
    }

    private void setGradeIfPresent(StudentCardTitle title) {
        if (!studentView.hasSession()) {
            return;
        }
        Optional<SessionData> sessionData = studentView.getSessionData();
        if (sessionData.isEmpty()) {
            titledPane.getStyleClass().add("not-graded");
        } else {
            double grade = sessionData.get().getGrade();
            title.setGrade(grade);
        }
    }

    private void setContent() {
        Student student = studentView.getStudent();
        student.getModuleClasses().stream()
            .sorted(Comparator.comparing(moduleClass -> moduleClass.getClassName()))
            .forEach(moduleClass -> classes.getChildren().add(new Label(moduleClass.getClassName())));
        addDataField(student.getPhone().value);
        addDataField(student.getAddress().value);
        addDataField(student.getEmail().value);
    }

    /**
     * Adds a data field with the given {@code text} to the {@code dataFields}.
     * If the {@code text} is empty then nothing happens.
     */
    private void addDataField(String text) {
        if (text.isEmpty()) {
            return;
        }
        Label label = new Label(text);
        label.setWrapText(true);
        label.getStyleClass().add("cell_small_label");
        dataFields.getChildren().add(label);
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
