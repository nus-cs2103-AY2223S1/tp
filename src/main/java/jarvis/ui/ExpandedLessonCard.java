package jarvis.ui;

import jarvis.model.Lesson;
import jarvis.model.Student;
import jarvis.model.Studio;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays the full information of a {@code Lesson}.
 */
public class ExpandedLessonCard extends UiPart<Region> {

    public static final double TABLE_VIEW_HEIGHT_CONSULT = 250;
    public static final double TABLE_VIEW_HEIGHT_MC = 200;
    public static final double TABLE_VIEW_HEIGHT_STUDIO = 500;

    private static final String FXML = "ExpandedLessonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private ImageView checkbox;
    @FXML
    private Label lessonType;
    @FXML
    private Label lessonDesc;
    @FXML
    private Label timePeriod;
    @FXML
    private Label generalNotes;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> studentIndex;
    @FXML
    private TableColumn<Student, String> studentNames;
    @FXML
    private TableColumn<Student, String> studentAttendance;
    @FXML
    private TableColumn<Student, Integer> studentParticipation;
    @FXML
    private TableColumn<Student, String> studentNotes;

    private Image tick = new Image(getClass().getResourceAsStream("/images/tick.png"));
    private Image cross = new Image(getClass().getResourceAsStream("/images/cross.png"));

    /**
     * Creates an {@code ExpandedLessonCard} with the given {@code Lesson} and index to display.
     */
    public ExpandedLessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        if (lesson.isCompleted()) {
            checkbox.setImage(tick);
        } else {
            checkbox.setImage(cross);
        }
        switch(lesson.getLessonType()) {
        case MASTERY_CHECK:
            lessonType.setText("Mastery Check");
            tableView.setPrefHeight(TABLE_VIEW_HEIGHT_MC);
            break;
        case CONSULT:
            lessonType.setText("Consult");
            tableView.setPrefHeight(TABLE_VIEW_HEIGHT_CONSULT);
            break;
        case STUDIO:
            lessonType.setText("Studio");
            tableView.setPrefHeight(TABLE_VIEW_HEIGHT_STUDIO);
            // Set participation column for studio
            studentParticipation.setVisible(true);
            studentParticipation.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>((
                    (Studio) lesson).getParticipationForStudent(s.getValue())));
            break;
        default:
            assert false : "There are only 3 types of lesson";
        }
        if (lesson.hasDesc()) {
            lessonDesc.setText("Description: " + lesson.getDesc().lessonDesc);
        } else {
            lessonDesc.setText("{No description}");
        }
        timePeriod.setText(lesson.getTimePeriod().toString());
        generalNotes.setText(lesson.getGeneralNotes());

        ObservableList<Student> list = FXCollections.observableArrayList(lesson.getAttendance().getAllStudents());
        studentIndex.setCellValueFactory(s -> new ReadOnlyObjectWrapper<>(
                tableView.getItems().indexOf(s.getValue()) + 1));
        studentNames.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getName().toString()));
        studentAttendance.setCellValueFactory(s -> new ReadOnlyStringWrapper(lesson.isPresent(s.getValue())));
        studentNotes.setCellValueFactory(s -> new ReadOnlyStringWrapper(
                lesson.getStudentNotes(s.getValue())));
        tableView.setItems(list);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExpandedLessonCard)) {
            return false;
        }

        // state check
        ExpandedLessonCard card = (ExpandedLessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }

}
