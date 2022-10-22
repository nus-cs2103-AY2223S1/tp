package jeryl.fyp.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import jeryl.fyp.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentCard.fxml";

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
    private Hyperlink email;
    @FXML
    private ImageView emailImage;
    @FXML
    private Label projectName;
    @FXML
    private Label projectStatus;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane deadlineList;

    private Image emailThumbnail = new Image(this.getClass().getResourceAsStream("/images/address_book_32.png"));
    private static final int MAX_TAG_LINE_LENGTH = 40;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getStudentName().fullStudentName);
        studentId.setText(student.getStudentId().id);
        emailImage.setImage(emailThumbnail);
        email.setOnAction(t -> {
            try {
                Desktop.getDesktop().browse(new URI("mailto:" + student.getEmail().value));
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            } catch (URISyntaxException urie) {
                throw new RuntimeException(urie);
            }
        });
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
                    .sorted(Comparator.comparing(ddl -> ddl.fullDeadlineDateTime))
                    .forEach(ddl -> deadlineList.getChildren().add(new Label(index.incrementAndGet() + ". " + ddl)));
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
