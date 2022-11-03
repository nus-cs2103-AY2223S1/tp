package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.FilterPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterTasksCommand}.
 */
public class FilterTasksCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeValidModuleTasksFound_success() throws Exception {
        FilterPredicate predicate = new FilterPredicate(Optional.of(CS2030), Optional.empty(),
                Optional.empty());
        String expectedMessage =String.format(FilterTasksCommand.MESSAGE_SUCCESS, predicate);

        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        filterTasksCommand.execute(model);
        assertCommandSuccess(filterTasksCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeValidCompletionStatusTasksFound_success() throws Exception {
        FilterPredicate predicate = new FilterPredicate(Optional.empty(), Optional.of(true),
                Optional.empty());
        String expectedMessage =String.format(FilterTasksCommand.MESSAGE_SUCCESS, predicate);

        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        filterTasksCommand.execute(model);
        assertCommandSuccess(filterTasksCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeValidLinkStatusTasksFound_success() throws Exception {
        FilterPredicate predicate = new FilterPredicate(Optional.empty(), Optional.empty(),
                Optional.of(true));
        String expectedMessage =String.format(FilterTasksCommand.MESSAGE_SUCCESS, predicate);

        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        filterTasksCommand.execute(model);
        assertCommandSuccess(filterTasksCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeAllConditionsPresentTasksFound_success() throws Exception {
        FilterPredicate predicate = new FilterPredicate(Optional.of(CS2030), Optional.of(false),
                Optional.of(false));
        String expectedMessage =String.format(FilterTasksCommand.MESSAGE_SUCCESS, predicate);

        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        filterTasksCommand.execute(model);
        assertCommandSuccess(filterTasksCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeNoTasksFound_success() throws Exception {
        FilterPredicate predicate = new FilterPredicate(Optional.of(CS2030), Optional.of(false),
                Optional.of(true));
        String expectedMessage =String.format(FilterTasksCommand.MESSAGE_NO_RESULTS, predicate);

        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        filterTasksCommand.execute(model);
        assertCommandSuccess(filterTasksCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeInvalidModule_throwsCommandException() {
        FilterPredicate predicate = new FilterPredicate(Optional.of(new Module(new ModuleCode("CS2000"))),
                Optional.of(false), Optional.of(true));
        FilterTasksCommand filterTasksCommand = new FilterTasksCommand(predicate);
        assertCommandFailure(filterTasksCommand, model, Messages.MESSAGE_MODULE_NOT_FOUND);
    }

    @Test
    public void equals() {
        FilterPredicate firstPredicate = new FilterPredicate(Optional.of(CS2030), Optional.empty(),
                Optional.empty());
        FilterPredicate secondPredicate = new FilterPredicate(Optional.of(CS2040), Optional.empty(),
                Optional.empty());

        FilterTasksCommand firstFilterTasksCommand = new FilterTasksCommand(firstPredicate);
        FilterTasksCommand secondFilterTasksCommand = new FilterTasksCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstFilterTasksCommand.equals(firstFilterTasksCommand));

        // same values -> returns true
        FilterTasksCommand firstFilterTasksCommandCopy = new FilterTasksCommand(firstPredicate);
        assertTrue(firstFilterTasksCommand.equals(firstFilterTasksCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterTasksCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterTasksCommand.equals(null));

        // different predicate -> returns false
        assertFalse(firstFilterTasksCommand.equals(secondFilterTasksCommand));
    }
}
