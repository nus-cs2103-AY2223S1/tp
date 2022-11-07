package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.sort.NameComparator;
import seedu.address.model.sort.PhoneComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        Comparator<Person> nameComparator = new NameComparator();
        Comparator<Person> phoneComparator = new PhoneComparator();

        SortCommand sortNameCommand = new SortCommand(nameComparator);
        SortCommand sortPhoneCommand = new SortCommand(phoneComparator);

        // same object -> returns true
        assertTrue(sortNameCommand.equals(sortNameCommand));

        // different types -> returns false
        assertFalse(sortNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortNameCommand.equals(null));

        // different sort commands -> returns false
        assertFalse(sortNameCommand.equals(sortPhoneCommand));
    }
}
