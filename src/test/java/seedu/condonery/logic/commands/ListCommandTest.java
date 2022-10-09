package seedu.condonery.logic.commands;

import static seedu.condonery.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.condonery.logic.commands.CommandTestUtil.showPropertyAtIndex;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.condonery.testutil.TypicalPersons.getTypicalPropertyDirectory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPropertyDirectory(), new UserPrefs());
        expectedModel = new ModelManager(model.getPropertyDirectory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
