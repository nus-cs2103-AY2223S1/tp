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

class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validArgs_success() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_SECOND_PERSON, new Survey("Shopping Survey"));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person unmarkedPerson = markPersonAtIndex(expectedModel, INDEX_SECOND_PERSON, new Survey("Shopping Survey"),
                false);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_MARK_PERSON_SUCCESS, unmarkedPerson);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex, new Survey("Environment Survey"));
        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSurvey_throwsCommandException() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, new Survey("asdsad"));
        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_INVALID_SURVEY);
    }

    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST_PERSON, new Survey("Environment Survey"));
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND_PERSON, new Survey("Environment Survey"));
        UnmarkCommand unmarkThirdCommand = new UnmarkCommand(INDEX_SECOND_PERSON, new Survey("Academic Survey"));

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST_PERSON, new Survey("Environment Survey"));
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));

        // different survey -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkThirdCommand));
    }
}
