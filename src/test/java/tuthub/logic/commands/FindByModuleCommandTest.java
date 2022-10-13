package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.HOON;
import static tuthub.testutil.TypicalTutors.IDA;
import static tuthub.testutil.TypicalTutors.JACKSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.ModuleContainsKeywordPredicate;
import tuthub.model.tutor.Tutor;

/**
 * Contains integration tests (interaction with the model) for {@code FindByModuleCommand}.
 */
public class FindByModuleCommandTest {
    private List<Tutor> testTaList = Arrays.asList(IDA, HOON, JACKSON);
    private Model model = new ModelManager(getTestTaTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTestTaTuthub(), new UserPrefs());

    @Test
    public void equals() {
        System.out.println(getTestTaTuthub());
        ModuleContainsKeywordPredicate firstPredicate =
                new ModuleContainsKeywordPredicate(Collections.singletonList("first"));
        ModuleContainsKeywordPredicate secondPredicate =
                new ModuleContainsKeywordPredicate(Collections.singletonList("second"));

        FindByModuleCommand findByModuleFirstCommand = new FindByModuleCommand(firstPredicate);
        FindByModuleCommand findByModuleSecondCommand = new FindByModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findByModuleFirstCommand.equals(findByModuleFirstCommand));

        // same values -> returns true
        FindByModuleCommand findByModuleFirstCommandCopy = new FindByModuleCommand(firstPredicate);
        assertTrue(findByModuleFirstCommand.equals(findByModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(findByModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findByModuleFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(findByModuleFirstCommand.equals(findByModuleSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTutorFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 0);
        ModuleContainsKeywordPredicate predicate = preparePredicate(" ");
        FindByModuleCommand command = new FindByModuleCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        ModuleContainsKeywordPredicate predicate = preparePredicate("cs2105 cs2103t");
        FindByModuleCommand command = new FindByModuleCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HOON, JACKSON), model.getFilteredTutorList());
    }

    private Tuthub getTestTaTuthub() {
        Tuthub testTaTuthub = new Tuthub();
        for (Tutor tutor : testTaList) {
            testTaTuthub.addTutor(tutor);
        }
        return testTaTuthub;
    }

    /**
     * Parses {@code userInput} into a {@code ModuleContainsKeywordsPredicate}.
     */
    private ModuleContainsKeywordPredicate preparePredicate(String userInput) {
        return new ModuleContainsKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
