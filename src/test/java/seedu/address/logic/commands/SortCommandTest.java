package seedu.address.logic.commands;

//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
//import seedu.address.model.person.Person;
import seedu.address.model.UserPrefs;
//import seedu.address.model.sort.EmailComparator;
//import seedu.address.model.sort.NameComparator;
//import seedu.address.model.sort.PhoneComparator;

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
    public void execute_nameSortedList_showsNameSortedList() {};

}
