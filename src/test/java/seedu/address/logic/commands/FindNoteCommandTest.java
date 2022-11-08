package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_NOTES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NON_MATCHING_NOTE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalNotes.CHARITY;
import static seedu.address.testutil.TypicalNotes.DONATE;
import static seedu.address.testutil.TypicalNotes.ELECTION;
import static seedu.address.testutil.TypicalNotes.KEYWORD_MATCHING_EVENT;
import static seedu.address.testutil.TypicalNotes.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.TitleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindNoteCommand}.
 */
public class FindNoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("first"));

        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindNoteCommand findFirstNoteCommand = new FindNoteCommand(firstPredicate);
        FindNoteCommand findSecondNoteCommand = new FindNoteCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstNoteCommand.equals(findFirstNoteCommand));

        // same values -> returns true
        FindNoteCommand findFirstNoteCommandCopy = new FindNoteCommand(firstPredicate);
        assertTrue(findFirstNoteCommand.equals(findFirstNoteCommandCopy));

        // different types -> returns false
        assertFalse(findFirstNoteCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstNoteCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstNoteCommand.equals(findSecondNoteCommand));
    }

    @Test
    public void execute_zeroKeywords_noNoteFound() {
        String expectedMessage = String.format(MESSAGE_NOTES_LISTED_OVERVIEW, 0);
        TitleContainsKeywordsPredicate predicate = preparePredicate(INVALID_NON_MATCHING_NOTE_TITLE);
        FindNoteCommand command = new FindNoteCommand(predicate);
        expectedModel.updateFilteredNoteList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredNoteList());
    }

    @Test
    public void execute_multipleKeywords_multipleNoteFound() {
        String expectedMessage = String.format(MESSAGE_NOTES_LISTED_OVERVIEW, 3);
        TitleContainsKeywordsPredicate predicate = preparePredicate(KEYWORD_MATCHING_EVENT);
        FindNoteCommand command = new FindNoteCommand(predicate);
        expectedModel.updateFilteredNoteList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CHARITY, DONATE, ELECTION), model.getFilteredNoteList());
    }


    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private TitleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TitleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
