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

/**
 * Contains integration tests (interaction with the Model) for {@code FindContactCommand}.
 */
public class FindContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final String nameKeyword1 = "Alice";
    private final String nameKeyword2 = "Benson";
    private final String phoneKeyword1 = "999";
    private final String phoneKeyword2 = "62353535";
    private final String emailKeyword1 = "john@gmail.com";
    private final String emailKeyword2 = "colonel-sanders-123@kfc.co.uk";
    private final String addressKeyword1 = "17A Lower Kent Ridge Road, #01-01, S(119081)";
    private final String addressKeyword2 = "17A Lower Kent Ridge Road, c/o reception@sally, #01-01, S(119081)";

    private final List<Name> nameKeywords = Arrays.asList(new Name(nameKeyword1), new Name(nameKeyword2));
    private final List<Phone> phoneKeywords = Arrays.asList(new Phone(phoneKeyword1), new Phone(phoneKeyword2));
    private final List<Email> emailKeywords = Arrays.asList(new Email(emailKeyword1), new Email(emailKeyword2));
    private final List<Address> addressKeywords = Arrays.asList(new Address(addressKeyword1),
            new Address(addressKeyword2));

    private final List<Name> emptyNameKeywords = new ArrayList<>();
    private final List<Phone> emptyPhoneKeywords = new ArrayList<>();
    private final List<Email> emptyEmailKeywords = new ArrayList<>();
    private final List<Address> emptyAddressKeywords = new ArrayList<>();

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, phoneKeywords,
                        emailKeywords, addressKeywords);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(nameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords);

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroMatchingKeywords_noPersonFound() {
        // TODO
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(nameKeywords, emptyPhoneKeywords,
                        emptyEmailKeywords, emptyAddressKeywords);
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
}
