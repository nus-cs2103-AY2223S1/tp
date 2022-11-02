package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showEventAtIndex;
import static longtimenosee.testutil.TypicalEvents.getTypicalAddressBook;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListEventsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListEventsCommand(), model, ListEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showEventAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListEventsCommand(), model, ListEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

