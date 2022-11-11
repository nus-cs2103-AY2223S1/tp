package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Tasks}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";
    private static final String INCOMPLETE_TAG = "-fx-text-fill: white;"
            + "-fx-background-color: #e84118;"
            + "-fx-padding: 1 3 1 3;"
            + "-fx-border-radius: 2;"
            + "-fx-background-radius: 2;"
            + "-fx-font-size: 11;";
    private static final String COMPLETE_TAG = "-fx-text-fill: white;"
            + "-fx-background-color: #4cd137;"
            + "-fx-padding: 1 3 1 3;"
            + "-fx-border-radius: 2;"
            + "-fx-background-radius: 2;"
            + "-fx-font-size: 11;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private VBox parentContainer;
    @FXML
    private Label datetime;
    @FXML
    private Label path;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        List<Attribute<?>> attrs = task.getAttributes();
        for (int i = 0; i < attrs.size(); i++) {
            if (attrs.get(i).isDisplayable()) {
                parentContainer.getChildren().add(attrs.get(i).getJavaFxRepresentation());
            }
        }
        path.setText(String.format("Path: " + task.getFullPath()));
        if (task.getCompletedTime() != null) {
            datetime.setText(
                    "Completed on: " + task.getCompletedTime()
                            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
            datetime.setStyle(COMPLETE_TAG);
        } else {
            datetime.setText("Incomplete!");
            datetime.setStyle(INCOMPLETE_TAG);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
