package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandFailure;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showPolicyAtIndex;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static longtimenosee.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static longtimenosee.testutil.TypicalIndexes.INDEX_SECOND_POLICY;
import static longtimenosee.testutil.TypicalPolicies.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.commons.core.Messages;
import longtimenosee.commons.core.index.Index;
import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;
import longtimenosee.model.policy.Policy;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PolicyDeleteCommand}.
 */
public class PolicyDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Policy policyToDelete = model.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());
        PolicyDeleteCommand policyDeleteCommand = new PolicyDeleteCommand(INDEX_FIRST_POLICY);

        String expectedMessage = String.format(PolicyDeleteCommand.MESSAGE_DELETE_POLICY_SUCCESS, policyToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePolicy(policyToDelete);

        assertCommandSuccess(policyDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPolicyList().size() + 1);
        PolicyDeleteCommand policyDeleteCommand = new PolicyDeleteCommand(outOfBoundIndex);

        assertCommandFailure(policyDeleteCommand, model, Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPolicyAtIndex(model, INDEX_FIRST_POLICY);

        Index outOfBoundIndex = INDEX_SECOND_POLICY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PolicyDeleteCommand policyDeleteCommand = new PolicyDeleteCommand(outOfBoundIndex);

        assertCommandFailure(policyDeleteCommand, model, Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PolicyDeleteCommand deleteFirstCommand = new PolicyDeleteCommand(INDEX_FIRST_POLICY);
        PolicyDeleteCommand deleteSecondCommand = new PolicyDeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        PolicyDeleteCommand deleteFirstCommandCopy = new PolicyDeleteCommand(INDEX_FIRST_POLICY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPolicyList().isEmpty());
    }
}
