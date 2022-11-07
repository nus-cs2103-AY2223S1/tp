package paymelah.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import paymelah.model.debt.Debt;
import paymelah.model.person.Person;

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

    public final Person person;

    @FXML
    protected HBox personCardPane;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<Debt> debts;

    /**
     * Creates a {@code PersonCard} with the given {@code Person}.
     */
    public PersonCard(Person person) {
        super(FXML);
        this.person = person;
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        telegram.setText(person.getTelegram().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        debts.setItems(FXCollections.observableArrayList(person.getDebts().asList()));
        debts.setCellFactory(listView -> new DebtListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Debt} using a {@code DebtCard}.
     */
    class DebtListViewCell extends ListCell<Debt> {
        @Override
        protected void updateItem(Debt debt, boolean empty) {
            super.updateItem(debt, empty);

            if (empty || debt == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DebtCard(debt, getIndex() + 1).getRoot());
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
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return person.equals(card.person);
    }
}
