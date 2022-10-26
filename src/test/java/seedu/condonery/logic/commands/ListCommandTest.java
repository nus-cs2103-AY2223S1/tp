package seedu.condonery.logic.commands;

import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.ListPropertyCommand;
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
        model = new ModelManager(getTypicalPropertyDirectory(), getTypicalClientDirectory(), new UserPrefs());
        expectedModel = new ModelManager(model.getPropertyDirectory(), model.getClientDirectory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListPropertyCommand(), model,
                ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showPropertyAtIndex(model, INDEX_FIRST);
        CommandTestUtil.assertCommandSuccess(new ListPropertyCommand(), model,
                ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
