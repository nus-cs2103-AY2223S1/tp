package swift.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import swift.model.bridge.PersonTaskBridge;
import swift.model.person.Person;
import swift.model.task.Task;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonTaskCard extends UiPart<Region> {

    private static final String FXML = "PersonTaskCard.fxml";

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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane contacts;
    @FXML
    private Label dueDate;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonTaskCard(Task task, int displayedIndex, ObservableList<PersonTaskBridge> personTaskBridgeList,
                    ObservableList<Person> personList) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        setAssociatedContacts(personTaskBridgeList, personList);
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
                            dueDate.setText("No due date");
                            return;
                        }
                    }
                });
    }

    private void setStyle(Label... labels) {
        for (Label label: labels) {
            label.setStyle("-fx-background-color:derive(#6D28D9, 50%); -fx-text-fill: #FFFFFF;"
                    + "-fx-font-family: Inter; -fx-font-size: 10; -fx-font-weight: bold;"
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
        if (!(other instanceof PersonTaskCard)) {
            return false;
        }

        // state check
        PersonTaskCard card = (PersonTaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
