package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventSortField;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListEventsCommand.
 */
public class ListEventsCommandTest {

    private Model model;
    private Model expectedModel;
    private EventSortField dummySortField;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        dummySortField = EventSortField.sortByNoField();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String successMessage = String.format(ListEventsCommand.MESSAGE_SUCCESS, dummySortField.getField());
        assertCommandSuccess(new ListEventsCommand(dummySortField), model, successMessage, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        String successMessage = String.format(ListEventsCommand.MESSAGE_SUCCESS, dummySortField.getField());
        showEventAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListEventsCommand(dummySortField), model, successMessage, expectedModel);
    }
}

