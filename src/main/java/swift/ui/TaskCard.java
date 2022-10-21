package swift.ui;

import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import swift.model.person.Address;
import swift.model.person.Email;
import swift.model.person.Name;
import swift.model.person.Person;
import swift.model.person.Phone;
import swift.model.task.Task;
import swift.model.util.SampleDataUtil;


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
    private Label dueDate;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskName.setText(task.getTaskName().fullName);

        // placeholder for task ui testing
        UUID id1 = UUID.fromString("47005f2b-9c40-4051-8c95-69ca601cb58d");
        Person john = new Person(id1, new Name("John Cena"), new Phone("91122668"),
                new Email("john@gmail.com"), new Address("blk 123 st 28"),
                SampleDataUtil.getTagSet("friends"));
        Label lb1 = new Label(john.getName().fullName);
        Label lb2 = new Label("Alice Smith");
        setStyle(lb1, lb2);
        contacts.getChildren().add(lb1);
        contacts.getChildren().add(lb2);
        String due = "31 Oct 2022";
        dueDate.setText("Due " + due);

        //task.getContacts().stream()
        //        .sorted(Comparator.comparing(contact -> contact.fullName))
        //        .forEach(contact -> contacts.getChildren().add(new Label(contact.fullName)));
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
