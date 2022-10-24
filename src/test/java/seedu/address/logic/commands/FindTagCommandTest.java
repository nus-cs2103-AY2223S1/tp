package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteTagsContainsKeywordsPredicate;
import seedu.address.model.note.Title;
import seedu.address.model.person.PersonTagsContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonTagsContainsKeywordsPredicate firstPersonPredicate =
                new PersonTagsContainsKeywordsPredicate(Collections.singletonList("first"));
        PersonTagsContainsKeywordsPredicate secondPersonPredicate =
                new PersonTagsContainsKeywordsPredicate(Collections.singletonList("second"));
        NoteTagsContainsKeywordsPredicate firstNotePredicate =
                new NoteTagsContainsKeywordsPredicate(Collections.singletonList("first"));
        NoteTagsContainsKeywordsPredicate secondNotePredicate =
                new NoteTagsContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTagCommand findTagFirstCommand = new FindTagCommand(firstPersonPredicate, firstNotePredicate);
        FindTagCommand findTagSecondCommand = new FindTagCommand(secondPersonPredicate, secondNotePredicate);

        // same object -> returns true
        assertTrue(findTagFirstCommand.equals(findTagFirstCommand));

        // same values -> returns true
        FindTagCommand findTagFirstCommandCopy = new FindTagCommand(firstPersonPredicate, firstNotePredicate);
        assertTrue(findTagFirstCommand.equals(findTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(findTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findTagFirstCommand.equals(findTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsContainsKeywordsPredicate personPredicate = preparePersonPredicate(" ");
        NoteTagsContainsKeywordsPredicate notePredicate = prepareNotePredicate(" ");
        FindTagCommand command = new FindTagCommand(personPredicate, notePredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredNoteList(notePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsContainsKeywordsPredicate personPredicate = preparePersonPredicate("friends owesMoney");
        NoteTagsContainsKeywordsPredicate notePredicate = prepareNotePredicate("friends owesMoney");
        FindTagCommand command = new FindTagCommand(personPredicate, notePredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredNoteList(notePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroKeywords_noPersonOrNoteFound() {
        String noteTitle = "Club meeting soon!";
        String noteContent = "Remind club members to attend meeting.";
        String tagName = "friends";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + noteTitle + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + noteContent + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsContainsKeywordsPredicate personPredicate = preparePersonPredicate(" ");
        NoteTagsContainsKeywordsPredicate notePredicate = prepareNotePredicate(" ");
        FindTagCommand command = new FindTagCommand(personPredicate, notePredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredNoteList(notePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
        assertEquals(Collections.emptyList(), model.getFilteredNoteList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsAndNotesFound() {
        String noteTitleA = "Club meeting soon!";
        String noteContentA = "Remind club members to attend meeting.";
        String tagNameA = "friends";

        String noteTitleB = "T-Shirt payment due";
        String noteContentB = "Collect money";
        String tagNameB = "owesMoney";

        Note noteA = new Note(
                new Title(noteTitleA),
                new Content(noteContentA),
                new HashSet<>(Arrays.asList(new Tag(tagNameA))));
        Note noteB = new Note(
                new Title(noteTitleB),
                new Content(noteContentB),
                new HashSet<>(Arrays.asList(new Tag(tagNameB))));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + noteTitleA + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + noteContentA + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagNameA)
                .execute(model));

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + noteTitleB + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + noteContentB + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagNameB)
                .execute(model));

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsContainsKeywordsPredicate personPredicate = preparePersonPredicate("friends owesMoney");
        NoteTagsContainsKeywordsPredicate notePredicate = prepareNotePredicate("friends owesMoney");
        FindTagCommand command = new FindTagCommand(personPredicate, notePredicate);
        expectedModel.updateFilteredPersonList(personPredicate);
        expectedModel.updateFilteredNoteList(notePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
        assertEquals(Arrays.asList(noteA, noteB), model.getFilteredNoteList());
    }

    /**
     * Parses {@code userInput} into a {@code PersonTagsContainsKeywordsPredicate}.
     */
    private PersonTagsContainsKeywordsPredicate preparePersonPredicate(String userInput) {
        return new PersonTagsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NoteTagsContainsKeywordsPredicate}.
     */
    private NoteTagsContainsKeywordsPredicate prepareNotePredicate(String userInput) {
        return new NoteTagsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
