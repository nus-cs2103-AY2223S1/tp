package swift.ui;

import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Deadline;
import swift.model.task.Task;

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
    private Label taskName;
    @FXML
    private Label id;
    @FXML
    private FlowPane contacts;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex, ObservableList<PersonTaskBridge> personTaskBridgeList,
                    ObservableList<Person> personList) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskName.setText(task.getName().fullName);
        deadline.setText(task.getDeadline().map(Deadline::toString).orElse("No deadline"));
        // TODO: Populate the description
        // task.getDescription().map(Description::toString).orElse("No description");
    }

    private void setAssociatedContacts(ObservableList<PersonTaskBridge> personTaskBridgeList,
                                       ObservableList<Person> personList) {
        personTaskBridgeList.stream()
                .filter(bridge -> bridge.getTaskId().equals(task.getId()))
                .collect(Collectors.toList())
                .forEach(taskBridge -> {
                    UUID personId = taskBridge.getPersonId();
                    Person associatedPerson;

                    for (Person person : personList) {
                        associatedPerson = person;
                        if (associatedPerson.getId().equals(personId)) {
                            Label label = new Label(associatedPerson.getName().toString());
                            setStyle(label);
                            contacts.getChildren().add(label);
                            return;
                        }
                    }
                });
    }

    private void setStyle(Label... labels) {
        for (Label label: labels) {
            label.setStyle("-fx-background-color:derive(#6D28D9, 50%); -fx-text-fill: #FFFFFF;"
                    + "-fx-font-family: Inter; -fx-font-size: 12; -fx-font-weight: bold;"
                    + "-fx-border-insets: 3; -fx-border-radius: 4px; -fx-border-width: 2px;"
                    + "-fx-border-color: derive(#6D28D9, 50%); -fx-background-insets: 4; -fx-label-padding: 3;");
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
