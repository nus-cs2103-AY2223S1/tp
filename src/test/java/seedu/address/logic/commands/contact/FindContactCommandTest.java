package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;

/**
 * Contains integration tests (interaction with the Model) for {@code FindContactCommand}.
 */
public class FindContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final String nameKeyword1 = "Alice";
    private final String nameKeyword2 = "Benson";
    private final String nameKeyword3 = "Zen";
    private final String phoneKeyword1 = "94351253";
    private final String phoneKeyword2 = "98765432";
    private final String phoneKeyword3 = "97771234";
    private final String emailKeyword1 = "alice@example.com";
    private final String emailKeyword2 = "johnd@example.com";
    private final String emailKeyword3 = "zen@nomatch.com";
    private final String addressKeyword1 = "#08-111";
    private final String addressKeyword2 = "#02-25";
    private final String addressKeyword3 = "#99-99";
    private final String remarkKeywords1 = "monday";
    private final String remarkKeywords2 = "tuesday";
    private final String remarkKeywords3 = "wednesday";

    private final List<Name> nameKeywords = Arrays.asList(new Name(nameKeyword1), new Name(nameKeyword2));
    private final List<Phone> phoneKeywords = Arrays.asList(new Phone(phoneKeyword1), new Phone(phoneKeyword2));
    private final List<Email> emailKeywords = Arrays.asList(new Email(emailKeyword1), new Email(emailKeyword2));
    private final List<Address> addressKeywords = Arrays.asList(new Address(addressKeyword1),
            new Address(addressKeyword2));
    private final List<Remark> remarkKeywords = Arrays.asList(new Remark(remarkKeywords1),
            new Remark(remarkKeywords2));

    private final List<Name> nameKeywordsNoMatch = List.of(new Name(nameKeyword3));
    private final List<Phone> phoneKeywordsNoMatch = List.of(new Phone(phoneKeyword3));
    private final List<Email> emailKeywordsNoMatch = List.of(new Email(emailKeyword3));
    private final List<Address> addressKeywordsNoMatch = List.of(new Address(addressKeyword3));
    private final List<Remark> remarkKeywordsNoMatch = List.of(new Remark(remarkKeywords3));

    private final List<Name> emptyNameKeywords = new ArrayList<>();
    private final List<Phone> emptyPhoneKeywords = new ArrayList<>();
    private final List<Email> emptyEmailKeywords = new ArrayList<>();
    private final List<Address> emptyAddressKeywords = new ArrayList<>();
    private final List<Remark> emptyRemarkKeywords = new ArrayList<>();

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                        emailKeywords, addressKeywords, remarkKeywords);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(emptyNameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords, emptyRemarkKeywords);

        FindContactCommand findFirstCommand = new FindContactCommand(firstPredicate);
        FindContactCommand findSecondCommand = new FindContactCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindContactCommand findFirstCommandCopy = new FindContactCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicates -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroMatchingKeywords_noPersonFound() {
        // no matching name keywords, all other fields have matching keywords
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(nameKeywordsNoMatch, phoneKeywords,
                        emailKeywords, addressKeywords, remarkKeywords);
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredPersonList());

        // no matching phone keywords, all other fields have matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        predicate = new PersonContainsKeywordsPredicate(nameKeywords,
                phoneKeywordsNoMatch, emailKeywords, addressKeywords, remarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredPersonList());

        // no matching email keywords, all other fields have matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        predicate = new PersonContainsKeywordsPredicate(nameKeywords,
                phoneKeywords, emailKeywordsNoMatch, addressKeywords, remarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredPersonList());

        // no matching address keywords, all other fields have matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        predicate = new PersonContainsKeywordsPredicate(nameKeywords,
                phoneKeywords, emailKeywords, addressKeywordsNoMatch, remarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredPersonList());

        // no matching remark keywords, all other fields have matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        predicate = new PersonContainsKeywordsPredicate(nameKeywords,
                phoneKeywords, emailKeywords, addressKeywords, remarkKeywordsNoMatch);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        // multiple name keyword matches
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(nameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords, emptyRemarkKeywords);
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // multiple phone keyword matches
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonContainsKeywordsPredicate(emptyNameKeywords, phoneKeywords,
                emptyEmailKeywords, emptyAddressKeywords, emptyRemarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // multiple email keyword matches
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonContainsKeywordsPredicate(emptyNameKeywords, emptyPhoneKeywords,
                emailKeywords, emptyAddressKeywords, emptyRemarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // multiple address keyword matches, all other fields have no matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonContainsKeywordsPredicate(emptyNameKeywords, emptyPhoneKeywords,
                emptyEmailKeywords, addressKeywords, emptyRemarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // multiple remark keyword matches, all other fields have no matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonContainsKeywordsPredicate(emptyNameKeywords, emptyPhoneKeywords,
                emptyEmailKeywords, emptyAddressKeywords, remarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());

        // all fields have matching keywords
        expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        predicate = new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                emailKeywords, addressKeywords, remarkKeywords);
        command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
}
