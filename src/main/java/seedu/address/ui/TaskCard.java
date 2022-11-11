package seedu.address.ui;

import java.net.MalformedURLException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TaskList level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label module;
    @FXML
    private Label deadline;
    @FXML
    private ImageView checkbox;
    @FXML
    private FlowPane tags;

    private Image checkedPng = new Image(this.getClass().getResourceAsStream("/images/checkbox_checked.png"));
    private Image uncheckedPng = new Image(this.getClass().getResourceAsStream("/images/checkbox_unchecked.png"));

    /**
     * Creates a {@code PersonCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) throws MalformedURLException {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        deadline.setText(task.getDeadline().toDisplayString());
        module.setText(task.getModule().fullName);
        Image checkBoxImage = task.isDone() ? checkedPng : uncheckedPng;
        checkbox.setImage(checkBoxImage);
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
