package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.BENSON;
import static tuthub.testutil.TypicalTutors.CARL;
import static tuthub.testutil.TypicalTutors.DANIEL;
import static tuthub.testutil.TypicalTutors.ELLE;
import static tuthub.testutil.TypicalTutors.FIONA;
import static tuthub.testutil.TypicalTutors.GEORGE;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tuthub.logic.parser.Prefix;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.SortByRatingComparator;
import tuthub.model.tutor.SortByTeachingNominationComparator;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void equals() {
        SortByRatingComparator firstComparator =
                new SortByRatingComparator("a");
        SortByRatingComparator secondComparator =
                new SortByRatingComparator("d");

        Prefix firstPrefix = new Prefix("a/");
        Prefix secondPrefix = new Prefix("tn/");

        SortCommand sortFirstCommand = new SortCommand("a", firstPrefix,
                firstComparator);
        SortCommand sortSecondCommand = new SortCommand("a", firstPrefix,
                secondComparator); // different comparator
        SortCommand sortThirdCommand = new SortCommand("d", firstPrefix,
                firstComparator); // different order
        SortCommand sortFourthCommand = new SortCommand("a", secondPrefix,
                firstComparator); // different prefix
        SortCommand sortFifthCommand = new SortCommand("d", secondPrefix,
                secondComparator); // all different

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand("a", firstPrefix, firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different comparator -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
        // different order -> returns false
        assertFalse(sortFirstCommand.equals(sortThirdCommand));
        // different prefix -> returns false
        assertFalse(sortFirstCommand.equals(sortFourthCommand));
        // all different -> returns false
        assertFalse(sortFirstCommand.equals(sortFifthCommand));
    }

    @Test
    public void execute_sortTutorList_success() {
        // Test for sorting rating in ascending order
        String expectedMessage1 = String.format("Sorted based on %1$s, in %2$s order.", "RATINGS", "ascending");
        SortByRatingComparator ratingComparatorAsc = new SortByRatingComparator("a");
        SortCommand command1 = new SortCommand("a", new Prefix("r/"), ratingComparatorAsc);

        expectedModel.updateSortedTutorList(ratingComparatorAsc);
        assertCommandSuccess(command1, model, expectedMessage1, expectedModel);
        assertEquals(Arrays.asList(DANIEL, BENSON, FIONA, ELLE, GEORGE, ALICE, CARL),
                model.getFilteredTutorList());

        // Test for sorting rating in descending order
        String expectedMessage2 = String.format("Sorted based on %1$s, in %2$s order.", "RATINGS", "descending");
        SortByRatingComparator ratingComparatorDesc = new SortByRatingComparator("d");
        SortCommand command2 = new SortCommand("d", new Prefix("r/"), ratingComparatorDesc);

        expectedModel.updateSortedTutorList(ratingComparatorDesc);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
        assertEquals(Arrays.asList(CARL, ALICE, GEORGE, ELLE, FIONA, BENSON, DANIEL),
                model.getFilteredTutorList());

        // Test for sorting teaching nomination in ascending order
        String expectedMessage3 = String.format("Sorted based on %1$s, in %2$s order.",
                "TEACHING NOMINATIONS", "ascending");
        SortByTeachingNominationComparator teachingNominationComparatorAsc =
                new SortByTeachingNominationComparator("a");
        SortCommand command3 = new SortCommand("a", new Prefix("tn/"), teachingNominationComparatorAsc);

        expectedModel.updateSortedTutorList(teachingNominationComparatorAsc);
        assertCommandSuccess(command3, model, expectedMessage3, expectedModel);
        // All tutors have 1 teaching nomination, hence will stay the same as previous result.
        assertEquals(Arrays.asList(CARL, ALICE, GEORGE, ELLE, FIONA, BENSON, DANIEL),
                model.getFilteredTutorList());

        // Test for sorting teaching nomination in descending order
        String expectedMessage4 = String.format("Sorted based on %1$s, in %2$s order.",
                "TEACHING NOMINATIONS", "descending");
        SortByTeachingNominationComparator teachingNominationComparatorDesc =
                new SortByTeachingNominationComparator("d");
        SortCommand command4 = new SortCommand("d", new Prefix("tn/"), teachingNominationComparatorDesc);

        expectedModel.updateSortedTutorList(teachingNominationComparatorDesc);
        assertCommandSuccess(command4, model, expectedMessage4, expectedModel);
        assertEquals(Arrays.asList(CARL, ALICE, GEORGE, ELLE, FIONA, BENSON, DANIEL),
                model.getFilteredTutorList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
