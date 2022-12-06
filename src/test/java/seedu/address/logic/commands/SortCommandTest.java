package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalClients;
import static seedu.address.testutil.TypicalClients.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());


    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {

        SortCommand sortFirstLatestCommand = new SortCommand(INDEX_FIRST_CLIENT, true);
        SortCommand sortFirstOldestCommand = new SortCommand(INDEX_FIRST_CLIENT, false);
        SortCommand sortSecondLatestCommand = new SortCommand(INDEX_SECOND_CLIENT, true);

        // different object -> returns false
        assertTrue(sortFirstLatestCommand.equals(sortFirstLatestCommand));

        // different types -> returns false
        assertFalse(sortFirstLatestCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstLatestCommand.equals(null));

        // different client -> returns false
        assertFalse(sortFirstLatestCommand.equals(sortSecondLatestCommand));

        // different sorting -> returns false
        assertFalse(sortFirstLatestCommand.equals(sortFirstOldestCommand));
    }

    @Test
    public void execute_indexOne_viewsClientSuccess() {
        SortCommand command = new SortCommand(INDEX_FIRST_CLIENT, true);

        List<Client> clients = getTypicalClients();
        Client expectedClient = clients.get(0);
        String expectedClientName = expectedClient.getName().toString();

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, "latest", 1);

        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate(expectedClientName);
        expectedModel.updateFilteredClientList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredClientList().size(), 1);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJeeqTracker().getClientList().size());

        SortCommand sortCommand = new SortCommand(outOfBoundIndex, true);

        assertCommandFailure(sortCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }
}
