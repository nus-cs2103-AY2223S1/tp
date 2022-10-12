package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddLinkCommand.
 */
public class AddLinkCommandTest {
    private static final int MODULE_INDEX_NONEXISTENT_LARGE = 999999;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addLinkCommandFilteredList_success() {
        Set<Link> linksToAdd = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module moduleToEdit = expectedModel.getFilteredModuleList().get(0);
        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        List<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> moduleLinks = moduleToEdit.copyLinks();
        moduleLinks.addAll(linksToAdd);
        Module moduleToAddLink = new Module(moduleCode, moduleTitle, moduleTasks, moduleLinks);

        AddLinkCommand addLinkCommand = new AddLinkCommand(INDEX_FIRST_MODULE, linksToAdd);
        expectedModel.setModule(moduleToEdit, moduleToAddLink);
        String expectedMessage = String.format(AddLinkCommand.MESSAGE_ADD_LINK_SUCCESS, moduleToAddLink);

        assertCommandSuccess(addLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleLinkFilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Set<Link> link = firstModule.copyLinks();
        AddLinkCommand addLinkCommand = new AddLinkCommand(INDEX_FIRST_MODULE, link);

        assertCommandFailure(addLinkCommand, model,
                AddLinkCommand.MESSAGE_DUPLICATE_LINK + INDEX_FIRST_MODULE.getOneBased() + "]");
    }

    @Test
    public void execute_nonExistentLargeModuleIndexFilteredList_failure() {
        Set<Link> links = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        AddLinkCommand addLinkCommand = new AddLinkCommand(
                Index.fromOneBased(MODULE_INDEX_NONEXISTENT_LARGE), links);

        assertCommandFailure(addLinkCommand, model,
                Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddLinkCommand standardCommand = new AddLinkCommand(INDEX_FIRST_MODULE,
                new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK))));

        // same values -> returns true
        Set<Link> copyLinks = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        AddLinkCommand commandWithSameValues = new AddLinkCommand(INDEX_FIRST_MODULE, copyLinks);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddLinkCommand(INDEX_SECOND_MODULE, copyLinks)));

        // different link -> returns false
        assertFalse(standardCommand.equals(new AddLinkCommand(INDEX_FIRST_MODULE,
                new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK_2))))));
    }
}
