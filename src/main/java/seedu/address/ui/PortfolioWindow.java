package seedu.address.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PortfolioWindow extends UiPart<Region> {

    private static final String FXML = "Portfolio.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    public final int id;


    @FXML
    private Label risk;
    @FXML
    private FlowPane plans;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PortfolioWindow(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id = displayedIndex;
        if (person.getRisk().value != null && person.getRisk().value != "") {
            risk.setText(person.getRisk().value);
        } else {
            risk.setText("no risk assessment yet");
        }
        person.getPlans().stream()
                .sorted(Comparator.comparing(plan -> plan.value))
                .forEach(plan -> plans.getChildren().add(new Label(plan.value)));
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
