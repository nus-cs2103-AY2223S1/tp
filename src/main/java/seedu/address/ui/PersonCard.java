package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final String TRANSPARENT_BACKGROUND = "-fx-background-color: transparent;";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label income;
    @FXML
    private Label monthly;
    @FXML
    private FlowPane specialTags;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Appointment> appointments;
    private int displayedIndex;
    private PersonListPanel.PersonListViewCell owner;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, PersonListPanel.PersonListViewCell owner) {
        super(FXML);
        this.person = person;
        this.displayedIndex = displayedIndex;
        this.owner = owner;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        addTagLabels(person);
        appointments.setStyle(TRANSPARENT_BACKGROUND);
        appointments.setItems(person.getAppointments().getObservableList());
        appointments.setCellFactory(listView -> new AppointmentListViewCell());
        gridpane.getChildren().forEach(child -> child.setOnMousePressed(event -> handleOnMousePressed(event)));
    }

    public void select() {
        owner.select(displayedIndex - 1);
    }

    @FXML
    void handleOnMousePressed(MouseEvent event) {
        select();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
                setStyle(TRANSPARENT_BACKGROUND);
            } else {
                setGraphic(new AppointmentHBox(getIndex() + 1, appointment).getRoot());
                setStyle(TRANSPARENT_BACKGROUND);
            }
        }
    }

    private void addTagLabels(Person person) {
        person.getSpecialTags().stream()
                .forEach(tag -> specialTags.getChildren().add(new SpecialTagLabel(tag.tagName)));
        income.setText(person.getIncome().value);
        monthly.setText(person.getMonthly().value);
        person.getTags().stream()
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
