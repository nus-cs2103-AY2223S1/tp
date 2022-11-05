package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.getTypicalProfNus;
import static seedu.address.testutil.TypicalTutors.CARL;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalProfNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProfNus(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        StudentNameContainsKeywordsPredicate thirdPredicate =
                new StudentNameContainsKeywordsPredicate(Collections.singletonList("first"));
        StudentNameContainsKeywordsPredicate forthPredicate =
                new StudentNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, thirdPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, forthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, thirdPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0),
                false, false, false,
                true, false, false, false, false, false);
        NameContainsKeywordsPredicate predicate = preparePredicate("none");
        StudentNameContainsKeywordsPredicate studentPredicate = prepareStudentPredicate("none");
        FindCommand command = new FindCommand(predicate, studentPredicate);
        expectedModel.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        expectedModel.updateFilteredTutorList(studentPredicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3),
                false, false, false,
                true, false, false, false, false, false);
        NameContainsKeywordsPredicate predicate = preparePredicate("Alice Benson Carl");
        StudentNameContainsKeywordsPredicate studentPredicate = prepareStudentPredicate("Alice Benson Carl");
        FindCommand command = new FindCommand(predicate, studentPredicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code StudentNameContainsKeywordsPredicate}.
     */
    private StudentNameContainsKeywordsPredicate prepareStudentPredicate(String userInput) {
        return new StudentNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
