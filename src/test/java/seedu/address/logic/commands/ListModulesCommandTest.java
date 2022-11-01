package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBookForTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListModulesCommand.
 */
public class ListModulesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookForTask(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    class EmptyModelStub extends ModelManager {
        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return FXCollections.emptyObservableList();
        }
    }

    @Test
    public void execute_listModuleIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListModulesCommand(), model,
                ListModulesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    //integration test with command result...
    @Test
    public void execute_EmptyList() {
        EmptyModelStub emptymodelStub = new EmptyModelStub();
        assertEquals(new ListModulesCommand().execute(emptymodelStub),
                new CommandResult(ListModulesCommand.EMPTY_LIST));
    }
    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_THIRD);
        assertCommandSuccess(new ListModulesCommand(), model, ListModulesCommand.MESSAGE_SUCCESS, expectedModel);
    }
}