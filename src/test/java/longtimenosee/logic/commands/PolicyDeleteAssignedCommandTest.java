package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.VALID_END_DATE_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PREMIUM_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_START_DATE_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandFailure;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
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
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.PolicyDate;
import longtimenosee.model.policy.Premium;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PolicyAssignCommand}.
 */
public class PolicyDeleteAssignedCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        PolicyAssignCommand expectedAssignCommand = new PolicyAssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));
        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Policy policyToAdd = model.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedAssignCommand.execute(model);
        PolicyDeleteAssignedCommand expectedDeleteCommand = new PolicyDeleteAssignedCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_POLICY);
        String expectedMessage = String.format(expectedDeleteCommand.MESSAGE_ASSIGN_POLICY_SUCCESS,
                policyToAdd.getTitle(), personToAdd.getName());

        assertCommandSuccess(expectedDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PolicyDeleteAssignedCommand policyDeleteAssignedCommand = new PolicyDeleteAssignedCommand(outOfBoundIndex,
                INDEX_FIRST_POLICY);
        assertCommandFailure(policyDeleteAssignedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        PolicyDeleteAssignedCommand deleteFirstCommand = new PolicyDeleteAssignedCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_POLICY);
        PolicyDeleteAssignedCommand deleteSecondCommand = new PolicyDeleteAssignedCommand(INDEX_SECOND_PERSON,
                INDEX_SECOND_POLICY);
        PolicyDeleteAssignedCommand deleteThirdCommand = new PolicyDeleteAssignedCommand(INDEX_SECOND_PERSON,
                INDEX_FIRST_POLICY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        PolicyDeleteAssignedCommand deleteFirstCommandCopy = new PolicyDeleteAssignedCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_POLICY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));

        // different policy -> returns false
        assertFalse(deleteSecondCommand.equals(deleteThirdCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
