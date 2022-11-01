package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.CS2106_WITH_TYPICAL_TASKS;
import static seedu.address.testutil.TypicalModules.MA2001;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SwapTaskNumbersCommand.SwapTaskNumbersDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.task.Task;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.SwapTaskNumbersDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SwapTaskNumbersCommand}.
 */
public class SwapTaskNumbersCommandTest {
    private static final Model model =
            new ModelManager(getTypicalAddressBookWithOnlyModules(),
            new UserPrefs());
    static {
        model.setModule(CS2106, CS2106_WITH_TYPICAL_TASKS);
    }

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SwapTaskNumbersCommand(null));
    }
    @Test
    public void execute_taskSwappedInModule_success() {
        SwapTaskNumbersDescriptor descriptor =
                new SwapTaskNumbersDescriptorBuilder(CS2106_WITH_TYPICAL_TASKS).build();
        SwapTaskNumbersCommand swapTaskCommand = new SwapTaskNumbersCommand(descriptor);

        List<Task> expectedTaskList = getTypicalTasks();
        Collections.swap(expectedTaskList, 0, 1);
        Module expectedModule =
                new ModuleBuilder().withModuleCode(VALID_CS2106_MODULE_CODE)
                        .withModuleTitle(VALID_CS2106_MODULE_TITLE)
                        .withTasks(expectedTaskList).build();
        String expectedMessage = String.format(SwapTaskNumbersCommand.MESSAGE_SWAP_TASK_NUMBERS_SUCCESS,
                expectedModule);

        Model modelWithTasksToSwap = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        modelWithTasksToSwap.setModule(CS2106_WITH_TYPICAL_TASKS, expectedModule);

        assertCommandSuccess(swapTaskCommand, model, expectedMessage, modelWithTasksToSwap);
    }

    @Test
    public void execute_nonExistentModuleCode_throwsCommandException() {
        Module nonExistentModule =
                new ModuleBuilder()
                        .withModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK).build();
        SwapTaskNumbersDescriptor descriptor =
                new SwapTaskNumbersDescriptorBuilder(nonExistentModule).build();
        SwapTaskNumbersCommand swapTaskCommand = new SwapTaskNumbersCommand(descriptor);

        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_MODULE, () ->
                        swapTaskCommand.execute(model));
    }
    @Test
    public void execute_nonExistentTaskNumber_throwsCommandException() {
        Index validTaskNumber = Index.fromOneBased(1);
        Index nonExistentTaskNumberFour = Index.fromOneBased(4);
        Index nonExistentTaskNumberHundred = Index.fromOneBased(100);
        // one valid task number
        SwapTaskNumbersDescriptor descriptorWithOneValidTaskNumber =
                new SwapTaskNumbersDescriptorBuilder(CS2106_WITH_TYPICAL_TASKS,
                        validTaskNumber, nonExistentTaskNumberFour).build();
        SwapTaskNumbersCommand swapTaskCommandWithOneValidTaskNumber =
                new SwapTaskNumbersCommand(descriptorWithOneValidTaskNumber);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_TASK_NUMBER, () ->
                        swapTaskCommandWithOneValidTaskNumber.execute(model));

        // no valid task numbers
        SwapTaskNumbersDescriptor descriptorWithNoValidTaskNumber =
                new SwapTaskNumbersDescriptorBuilder(CS2106_WITH_TYPICAL_TASKS,
                        nonExistentTaskNumberHundred, nonExistentTaskNumberFour).build();
        SwapTaskNumbersCommand swapTaskCommandWithNoValidTaskNumber =
                new SwapTaskNumbersCommand(descriptorWithNoValidTaskNumber);
        assertThrows(CommandException.class,
                Messages.MESSAGE_NO_SUCH_TASK_NUMBER, () ->
                        swapTaskCommandWithNoValidTaskNumber.execute(model));
    }

    @Test
    public void equals() {
        final List<Module> modules = getTypicalModules();
        final SwapTaskNumbersDescriptor standardDescriptor =
                new SwapTaskNumbersDescriptorBuilder(CS2106_WITH_TYPICAL_TASKS).build();
        final SwapTaskNumbersCommand standardCommand = new SwapTaskNumbersCommand(standardDescriptor);

        // same values -> returns true
        SwapTaskNumbersDescriptor copyDescriptor =
                new SwapTaskNumbersDescriptorBuilder(CS2106_WITH_TYPICAL_TASKS).build();
        SwapTaskNumbersCommand commandWithSameValues = new SwapTaskNumbersCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new AddModuleCommand(CS2106_WITH_TYPICAL_TASKS)));

        // different module code -> returns false
        SwapTaskNumbersDescriptor differentModuleDescriptor =
                new SwapTaskNumbersDescriptorBuilder(MA2001).build();
        assertFalse(standardCommand.equals(new SwapTaskNumbersCommand(differentModuleDescriptor)));
    }
}
