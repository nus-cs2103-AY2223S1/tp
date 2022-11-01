package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalNuScheduler.getTypicalNuScheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewEventsCommand.
 */
public class ViewEventsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNuScheduler(), new UserPrefs());
        expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewEventsCommand(), model,
                ViewEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        assertCommandSuccess(new ViewEventsCommand(), model,
                ViewEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ViewEventsCommand viewEventsCommand = new ViewEventsCommand();

        // same class -> returns true
        assertTrue(viewEventsCommand.equals(new ViewEventsCommand()));

        // same object -> returns true
        assertTrue(viewEventsCommand.equals(viewEventsCommand));

        // null -> returns false
        assertFalse(viewEventsCommand.equals(null));

        // different types -> returns false
        assertFalse(viewEventsCommand.equals(1));
    }
}
