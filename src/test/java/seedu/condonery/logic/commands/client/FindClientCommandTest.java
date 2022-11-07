package seedu.condonery.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static seedu.condonery.testutil.TypicalClients.CARL_CLIENT;
import static seedu.condonery.testutil.TypicalClients.ELLE_CLIENT;
import static seedu.condonery.testutil.TypicalClients.FIONA_CLIENT;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.ClientNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindClientCommandTest {
    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void equals() {
        ClientNameContainsKeywordsPredicate firstPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ClientNameContainsKeywordsPredicate secondPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPropertyFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0);
        ClientNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindClientCommand command = new FindClientCommand(predicate);
        expectedModel.updateFilteredClientList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL_CLIENT, ELLE_CLIENT, FIONA_CLIENT), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ClientNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ClientNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
