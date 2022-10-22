package seedu.address.ui;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;

/**
 * A UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private CheckBox isCompleted;
    @FXML
    private Label deadline;
    @FXML
    private FlowPane assignedContacts;

    /**
     * Creates a {@code TaskCard} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex, Consumer<? super TaskCommand> commandConsumer) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().toString());

        if (task.getDeadline().isUnspecified()) {
            deadline.setVisible(false);
            deadline.setManaged(false);
        } else {
            String text = task.getDeadline().formatForUi();
            deadline.setText(text);
        }

        isCompleted.setText("");
        isCompleted.setSelected(task.getCompleted());
        isCompleted.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue) {
                commandConsumer.accept(new UnmarkTaskCommand(Index.fromOneBased(displayedIndex)));
            } else {
                commandConsumer.accept(new MarkTaskCommand(Index.fromOneBased(displayedIndex)));
            }
        });

        List<String> contactNames =
                task
                        .getAssignedContacts()
                        .stream()
                        .map(Contact::getContactName)
                        .sorted()
                        .collect(Collectors.toList());
        int numContacts = contactNames.size();
        for (int i = 0; i < numContacts; i++) {
            if (i < numContacts - 1) {
                assignedContacts.getChildren().add(new Label(contactNames.get(i) + ", "));
            } else {
                assignedContacts.getChildren().add(new Label(contactNames.get(i)));
            }
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
