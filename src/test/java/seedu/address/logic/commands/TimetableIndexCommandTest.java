package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TimetableIndexCommand}.
 */
public class TimetableIndexCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexWithLessons_success() {
        TimetableIndexCommand timetableIndexCommand = new TimetableIndexCommand(INDEX_THIRD_PERSON);

        String expectedMessage = String.format(TimetableIndexCommand.MESSAGE_TIMETABLE_ACKNOWLEDGEMENT,
                INDEX_THIRD_PERSON.getOneBased());

        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(timetableIndexCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        TimetableIndexCommand timetableIndexCommand = new TimetableIndexCommand(outOfBoundIndex);

        assertCommandFailure(timetableIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexNoLessons_throwsCommandException() {
        TimetableIndexCommand timetableIndexCommand = new TimetableIndexCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(timetableIndexCommand, model,
                String.format(TimetableIndexCommand.MESSAGE_NO_LESSONS, INDEX_FIRST_PERSON.getOneBased()));
    }

    @Test
    public void equals() {
        TimetableIndexCommand firstCommand = new TimetableIndexCommand(INDEX_FIRST_PERSON);
        TimetableIndexCommand secondCommand = new TimetableIndexCommand(INDEX_SECOND_PERSON);

        assertTrue(firstCommand.equals(firstCommand));

        TimetableIndexCommand firstCommandCopy = new TimetableIndexCommand(INDEX_FIRST_PERSON);
        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }
}
