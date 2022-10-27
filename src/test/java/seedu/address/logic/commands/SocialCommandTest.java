package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsSocial;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SocialCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonContainsSocial firstPredicate =
                new PersonContainsSocial("insta");
        PersonContainsSocial secondPredicate =
                new PersonContainsSocial("tele");

        SocialCommand socialInstaCommand = new SocialCommand(firstPredicate);
        SocialCommand socialTeleCommand = new SocialCommand(secondPredicate);

        // same object -> returns true
        assertTrue(socialInstaCommand.equals(socialInstaCommand));

        // same values -> returns true
        SocialCommand socialInstaCommandCopy = new SocialCommand(firstPredicate);
        assertTrue(socialInstaCommand.equals(socialInstaCommandCopy));

        // different types -> returns false
        assertFalse(socialInstaCommand.equals(1));

        // null -> returns false
        assertFalse(socialInstaCommand.equals(null));

        // different person -> returns false
        assertFalse(socialInstaCommand.equals(socialTeleCommand));
    }

    @Test
    public void execute_invalidInput_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonContainsSocial predicate = new PersonContainsSocial("invalid");
        SocialCommand command = new SocialCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

}
