package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import seedu.address.model.person.Person;

/**
 * Tab containing the list of persons.
 */
public class PersonListTab extends Tab {
    private static final String TAB_NAME = "contacts";

    /**
     * Creates a {@code PersonListTab} with the given {@code ObservableList}.
     */
    public PersonListTab(ObservableList<Person> personList) {
        super(TAB_NAME, new PersonListPanel(personList).getRoot());
    }
}
