package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class CopyContactEmailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final String tagKeyword1 = "CS2103T";
    private final String tagKeyword2 = "GEN2000";

    private final Set<Tag> tagKeywords1 = new HashSet<>(List.of(new Tag(tagKeyword1)));
    private final Set<Tag> tagKeywords2 = new HashSet<>(List.of(new Tag(tagKeyword2)));

    @Test
    public void equals() {
        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(tagKeywords1);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(tagKeywords2);

        CopyContactEmailCommand copyFirstCommand = new CopyContactEmailCommand(firstPredicate);
        CopyContactEmailCommand copySecondCommand = new CopyContactEmailCommand(secondPredicate);

        // same object -> returns true
        assertTrue(copyFirstCommand.equals(copyFirstCommand));

        // same values -> returns true
        CopyContactEmailCommand copyFirstCommandCopy = new CopyContactEmailCommand(firstPredicate);
        assertTrue(copyFirstCommand.equals(copyFirstCommandCopy));

        // different types -> returns false
        assertFalse(copyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(copyFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(copyFirstCommand.equals(copySecondCommand));
    }

    @Test
    public void execute_noMatchingKeyword_throwsCommandException() {
        String expectedMessage = Messages.MESSAGE_LABEL_DOES_NOT_EXIST;
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(tagKeywords2);
        CopyContactEmailCommand command = new CopyContactEmailCommand(predicate);
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    @Test
    public void execute_matchingKeyword_emailsCopiedSuccessfully() {
        String expectedMessage =
                String.format(CopyContactEmailCommand.MESSAGE_COPIED_EMAILS, "alice@example.com johnd@example.com ");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(tagKeywords1);
        CopyContactEmailCommand command = new CopyContactEmailCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }
}
