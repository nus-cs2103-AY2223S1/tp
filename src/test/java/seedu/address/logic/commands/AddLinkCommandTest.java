package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddLinkCommand.
 */
public class AddLinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_duplicateModuleLinkUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Set<Link> link = firstModule.copyLinks();
        AddLinkCommand addLinkCommand = new AddLinkCommand(INDEX_FIRST_MODULE, link);

        assertCommandFailure(addLinkCommand, model,
                AddLinkCommand.MESSAGE_DUPLICATE_LINK + INDEX_FIRST_MODULE.getOneBased());
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
