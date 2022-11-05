package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonComparators;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class SortCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validComparators_success() {
        SortCommand sortCommand = new SortCommand(PersonComparators.NAME_COMPARATOR);
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getSortedFilteredPersonList().size());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.sortPersonList(PersonComparators.NAME_COMPARATOR);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        sortCommand = new SortCommand(PersonComparators.ROLE_COMPARATOR);

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.sortPersonList(PersonComparators.ROLE_COMPARATOR);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);

        sortCommand = new SortCommand(PersonComparators.ADDRESS_COMPARATOR);

        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.sortPersonList(PersonComparators.ADDRESS_COMPARATOR);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }
}
