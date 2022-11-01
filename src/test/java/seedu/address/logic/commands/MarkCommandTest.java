package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.markPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Survey;

class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validArgs_success() {
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Survey("Environment Survey"));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person markedPerson = markPersonAtIndex(expectedModel, INDEX_FIRST_PERSON, new Survey("Environment Survey"),
                true);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS, markedPerson);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, new Survey("Environment Survey"));
        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSurvey_throwsCommandException() {
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, new Survey("asdsad"));
        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_INVALID_SURVEY);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST_PERSON, new Survey("Environment Survey"));
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND_PERSON, new Survey("Environment Survey"));
        MarkCommand markThirdCommand = new MarkCommand(INDEX_SECOND_PERSON, new Survey("Academic Survey"));

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST_PERSON, new Survey("Environment Survey"));
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));

        // different survey -> returns false
        assertFalse(markFirstCommand.equals(markThirdCommand));
    }
}
