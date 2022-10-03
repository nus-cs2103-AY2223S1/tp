package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
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

    public final Person person;

    private Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane attributeA;
    @FXML
    private FlowPane attributeB;
    @FXML
    private FlowPane attributeC;
    @FXML
    private FlowPane attributeD;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Logic logic) {
        super(FXML);
        this.person = person;
        this.logic = logic;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        setAttributes();
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

    /**
     * Sets the attributes in the order required.
     */
    public void setAttributes() {
        int[] order = orderAttributes();
        FlowPane[] flowpanes = new FlowPane[4];
        flowpanes[order[0]] = attributeA;
        flowpanes[order[1]] = attributeB;
        flowpanes[order[2]] = attributeC;
        flowpanes[order[3]] = attributeD;

        if (flowpanes[0] != null) {
            flowpanes[0].getChildren().add(new Label(person.getPhone().value));
        }
        if (flowpanes[1] != null) {
            flowpanes[1].getChildren().add(new Label(person.getAddress().value));
        }
        if (flowpanes[2] != null) {
            flowpanes[2].getChildren().add(new Label(person.getEmail().value));
        }
        if (flowpanes[3] != null) {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> flowpanes[3].getChildren().add(new Label(tag.tagName)));
        }
    }

    /**
     * Generates the order of the attributes based on the order set by the user.
     *
     * @return the required order
     */
    public int[] orderAttributes() {
        int[] order = new int[4];
        String orderStr = logic.getGuiSettings().getAttributeOrder();
        for (int i = 0; i < 4; i++) {
            //to be replaced with enum
            order[i] = Integer.parseInt(String.valueOf(orderStr.charAt(i)));
        }
        return order;
    }
}
