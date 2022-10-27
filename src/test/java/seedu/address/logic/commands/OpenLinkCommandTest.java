package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GE3238_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

public class OpenLinkCommandTest {
    private static final String MODULE_CODE_WITH_LINK = VALID_GE3238_MODULE_CODE;
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_openLinkCommandFilteredList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Module moduleToOpenLinks = expectedModel.getModuleUsingModuleCode(
                new ModuleCode(MODULE_CODE_WITH_LINK), true);

        ModuleCode moduleCode = moduleToOpenLinks.getModuleCode();
        List<String> moduleLinkAliasesToOpen = moduleToOpenLinks.copyLinks().stream()
                .map(link -> link.linkAlias).collect(Collectors.toList());
        String moduleLinkUrlsToOpen = moduleToOpenLinks.copyLinks().stream()
                .map(link -> String.format("[%s]\n", link.linkUrl)).collect(Collectors.joining(""));

        OpenLinkCommand openLinkCommand = new OpenLinkCommand(
                new ModuleCode(MODULE_CODE_WITH_LINK), moduleLinkAliasesToOpen);

        String expectedMessage = String.format(OpenLinkCommand.MESSAGE_OPEN_LINK_SUCCESS,
                moduleCode.getModuleCodeAsUpperCaseString());

        assertCommandSuccess(openLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentLinkAliasFilteredList_failure() {
        OpenLinkCommand openLinkCommand = new OpenLinkCommand(new ModuleCode(VALID_CS2106_MODULE_CODE),
                Arrays.asList(VALID_MODULE_LINK_ALIAS_2));
        assertCommandFailure(openLinkCommand, model,
                String.format(OpenLinkCommand.MESSAGE_MISSING_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2,
                        new ModuleCode(VALID_CS2106_MODULE_CODE).getModuleCodeAsUpperCaseString()));
    }

    @Test
    public void execute_nonExistentModuleFilteredList_failure() {
        List<String> linkAliases = Arrays.asList(VALID_MODULE_LINK_ALIAS);
        OpenLinkCommand openLinkCommand = new OpenLinkCommand(
                new ModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK), linkAliases);
        assertCommandFailure(openLinkCommand, model,
                String.format(Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                        VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK));
    }

    @Test
    public void equals() {
        final OpenLinkCommand standardCommand = new OpenLinkCommand(new ModuleCode(VALID_CS2106_MODULE_CODE),
                Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2));
        List<String> copyLinkAliases = Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2);

        // same values, different object -> returns true
        OpenLinkCommand commandWithSameValues = new OpenLinkCommand(
                new ModuleCode(VALID_CS2106_MODULE_CODE), copyLinkAliases);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different module code -> returns false
        assertFalse(standardCommand.equals(new OpenLinkCommand(
                new ModuleCode(VALID_MA2001_MODULE_CODE), copyLinkAliases)));

        // different link alias -> returns false
        assertFalse(standardCommand.equals(new OpenLinkCommand(
                new ModuleCode(VALID_CS2106_MODULE_CODE),
                Arrays.asList(VALID_MODULE_LINK_ALIAS_2))));
    }
}
