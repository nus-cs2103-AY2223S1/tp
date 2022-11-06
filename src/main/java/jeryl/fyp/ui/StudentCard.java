package jeryl.fyp.ui;

import static javafx.scene.control.OverrunStyle.CLIP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import jeryl.fyp.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentCard.fxml";
    private static final int MAX_TAG_LINE_LENGTH = 40;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FypManager level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label studentId;
    @FXML
    private Label email;
    @FXML
    private Label projectName;
    @FXML
    private Label projectStatus;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox deadlineList;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getStudentName().fullStudentName);
        studentId.setText(student.getStudentId().id);
        email.setText(student.getEmail().value);
        projectName.setText(student.getProjectName().fullProjectName);
        projectStatus.setText(student.getProjectStatus().projectStatus);

        String style = "-fx-text-fill: black; -fx-background-radius: 20; -fx-background-color: ";
        switch (student.getProjectStatus().projectStatus) {
        case "DONE":
            style += "green";
            break;
        case "IP":
            style += "yellow";
            break;
        default:
            style += "red";
            break;
        }
        projectStatus.setStyle(style);

        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(
                        Arrays.asList(tag.tagName.split(String.format("(?<=\\G.{%d})", MAX_TAG_LINE_LENGTH)))
                                .stream().collect(Collectors.joining("...\n"))
                )));
        AtomicInteger index = new AtomicInteger();
        if (student.getDeadlineList().asUnmodifiableObservableList().isEmpty()) {
            deadlineList.getChildren().add(new Label("No deadline at the moment!"));
        } else {
            student.getDeadlineList().asUnmodifiableObservableList().stream()
                    .forEach(ddl -> {
                        Label newLabel = new Label(index.incrementAndGet() + ". " + ddl);
                        newLabel.setWrapText(true);
                        deadlineList.getChildren().add(newLabel);
                    });
        }
        deadlineList.getChildren().stream().forEach(child -> child.setStyle("-fx-font-size: 12"));
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
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
