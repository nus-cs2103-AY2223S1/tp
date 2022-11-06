package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.testutil.TypicalCustomers.CARL;
import static seedu.boba.testutil.TypicalCustomers.ELLE;
import static seedu.boba.testutil.TypicalCustomers.FIONA;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.boba.commons.core.Messages;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.NameContainsKeywordsPredicate;
import seedu.boba.model.customer.Phone;

/**
 * Contains integration tests (interaction with the BobaBotModel) for {@code FindCommand}.
 */
public class FindCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
    private BobaBotModel expectedBobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedBobaBotModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, bobaBotModel, expectedMessage, expectedBobaBotModel);
        assertEquals(Collections.emptyList(), bobaBotModel.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedBobaBotModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, bobaBotModel, expectedMessage, expectedBobaBotModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), bobaBotModel.getFilteredPersonList());
    }

    @Test
    public void execute_validPhoneArg_success() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setPhone(PHONE_FIRST_PERSON);

        FindCommand findCommand = new FindCommand(findPersonDescriptor);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        expectedBobaBotModel.updateFilteredPersonList(p -> p.getPhone().equals(PHONE_FIRST_PERSON));

        assertCommandSuccess(findCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    @Test
    public void execute_invalidPhoneArg_throwsCommandException() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setPhone(new Phone("11112222"));

        FindCommand findCommand = new FindCommand(findPersonDescriptor);

        assertCommandFailure(findCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_invalidEmailArg_throwsCommandException() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setEmail(new Email("testing123@test.com"));

        FindCommand findCommand = new FindCommand(findPersonDescriptor);

        assertCommandFailure(findCommand, bobaBotModel, Messages.MESSAGE_INVALID_PERSON_INFORMATION);
    }

    @Test
    public void execute_validEmailArg_success() {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setEmail(EMAIL_FIRST_PERSON);

        FindCommand findCommand = new FindCommand(findPersonDescriptor);

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        expectedBobaBotModel.updateFilteredPersonList(p -> p.getEmail().equals(EMAIL_FIRST_PERSON));

        assertCommandSuccess(findCommand, bobaBotModel, expectedMessage, expectedBobaBotModel);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
