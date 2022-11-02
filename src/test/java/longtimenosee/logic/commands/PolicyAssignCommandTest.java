package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.VALID_END_DATE_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_END_DATE_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PREMIUM_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_PREMIUM_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_START_DATE_FLEXI;
import static longtimenosee.logic.commands.CommandTestUtil.VALID_START_DATE_PRUSHIELD;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandFailure;
import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.logic.commands.CommandTestUtil.showPersonAtIndex;
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
public class PolicyAssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Policy policyToAdd = model.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        PolicyAssignCommand expectedAssignCommand = new PolicyAssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));
        String expectedMessage = String.format(expectedAssignCommand.MESSAGE_ASSIGN_POLICY_SUCCESS,
                policyToAdd.getTitle(), personToAdd.getName());

        assertCommandSuccess(expectedAssignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PolicyAssignCommand policyAssignCommand = new PolicyAssignCommand(outOfBoundIndex, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));

        assertCommandFailure(policyAssignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Policy policyToAdd = model.getFilteredPolicyList().get(INDEX_FIRST_POLICY.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_SECOND_PERSON);
        PolicyAssignCommand expectedAssignCommand = new PolicyAssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));
        String expectedMessage = String.format(expectedAssignCommand.MESSAGE_ASSIGN_POLICY_SUCCESS,
                policyToAdd.getTitle(), personToAdd.getName());

        assertCommandSuccess(expectedAssignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PolicyAssignCommand policyAssignCommand = new PolicyAssignCommand(outOfBoundIndex, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));

        assertCommandFailure(policyAssignCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PolicyAssignCommand deleteFirstCommand = new PolicyAssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));
        PolicyAssignCommand deleteSecondCommand = new PolicyAssignCommand(INDEX_SECOND_PERSON, INDEX_SECOND_POLICY,
                new Premium(VALID_PREMIUM_FLEXI),
                new PolicyDate(VALID_START_DATE_FLEXI), new PolicyDate(VALID_END_DATE_FLEXI));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        PolicyAssignCommand deleteFirstCommandCopy = new PolicyAssignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_POLICY,
                new Premium(VALID_PREMIUM_PRUSHIELD),
                new PolicyDate(VALID_START_DATE_PRUSHIELD), new PolicyDate(VALID_END_DATE_PRUSHIELD));
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

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
