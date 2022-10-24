package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;
import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindModuleCommand}.
 */
public class FindModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstKeyword = Collections.singletonList("first");
        List<String> secondKeyword = Collections.singletonList("second");

        FindModuleCommand findFirstCommand = new FindModuleCommand(firstKeyword);
        FindModuleCommand findSecondCommand = new FindModuleCommand(secondKeyword);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindModuleCommand findFirstCommandCopy = new FindModuleCommand(firstKeyword);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        List<String> keyword = Collections.emptyList();
        FindModuleCommand command = new FindModuleCommand(keyword);
        expectedModel.updateFilteredModuleList(new ModuleCodeContainsKeywordsPredicate(keyword));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 4);
        List<String> keywords = new ArrayList<>();
        keywords.add("cs2109s");
        keywords.add("cs2100");
        keywords.add("cs2103t");
        keywords.add("cs3230");
        FindModuleCommand command = new FindModuleCommand(keywords);
        expectedModel.updateFilteredModuleList(new ModuleCodeContainsKeywordsPredicate(keywords));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalModules(), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
