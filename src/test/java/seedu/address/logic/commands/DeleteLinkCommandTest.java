package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GE3238_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteLinkCommand.
 */
public class DeleteLinkCommandTest {
    private static final int MODULE_INDEX_NONEXISTENT = 999999;
    private static final String MODULE_CODE_WITH_LINK = VALID_GE3238_MODULE_CODE;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteLinkCommandFilteredList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module moduleToEdit = expectedModel.getModuleUsingModuleCode(new ModuleCode(MODULE_CODE_WITH_LINK), true);
        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleTitle moduleTitle = moduleToEdit.getModuleTitle();
        List<Task> moduleTasks = moduleToEdit.getTasks();
        Set<Link> moduleLinksToDelete = moduleToEdit.copyLinks();
        Set<Link> moduleLinksEmpty = new HashSet<Link>();
        Module moduleToDeleteLink = new Module(moduleCode, moduleTitle, moduleTasks, moduleLinksEmpty);

        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(
                new ModuleCode(MODULE_CODE_WITH_LINK), moduleLinksToDelete);
        expectedModel.setModule(moduleToEdit, moduleToDeleteLink);
        String expectedMessage = String.format(DeleteLinkCommand.MESSAGE_DELETE_LINK_SUCCESS, moduleToDeleteLink);

        assertCommandSuccess(deleteLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_missingModuleLinkFilteredList_failure() {
        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(new ModuleCode(VALID_CS2106_MODULE_CODE),
                new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK_2))));
        assertCommandFailure(deleteLinkCommand, model,
                DeleteLinkCommand.MESSAGE_MISSING_LINK
                        + new ModuleCode(VALID_CS2106_MODULE_CODE).getModuleCodeAsUpperCaseString()
                        + "] [" + VALID_MODULE_LINK_2 + "]");
    }

    @Test
    public void execute_nonExistentModuleFilteredList_failure() {
        Set<Link> links = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        DeleteLinkCommand deleteLinkCommand = new DeleteLinkCommand(
                new ModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK), links);
        assertCommandFailure(deleteLinkCommand, model,
                String.format(Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                        VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK));
    }

    @Test
    public void equals() {
        final DeleteLinkCommand standardCommand = new DeleteLinkCommand(new ModuleCode(VALID_CS2106_MODULE_CODE),
                new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK))));

        // same values -> returns true
        Set<Link> copyLinks = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        DeleteLinkCommand commandWithSameValues = new DeleteLinkCommand(
                new ModuleCode(VALID_CS2106_MODULE_CODE), copyLinks);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteLinkCommand(
                new ModuleCode(VALID_MA2001_MODULE_CODE), copyLinks)));

        // different link -> returns false
        assertFalse(standardCommand.equals(new DeleteLinkCommand(
                new ModuleCode(VALID_CS2106_MODULE_CODE),
                new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK_2))))));
    }
}
