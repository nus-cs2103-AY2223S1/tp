package seedu.travelr.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.travelr.model.Model;
import seedu.travelr.model.ModelManager;
import seedu.travelr.model.UserPrefs;

import static seedu.travelr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.travelr.logic.commands.CommandTestUtil.showTripAtIndex;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.travelr.testutil.TypicalTrips.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTripAtIndex(model, INDEX_FIRST_TRIP);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
