package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_EATERIES_LISTED_OVERVIEW;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.model.eatery.LocationContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.TypicalEateries;

/**
 * Contains integration tests (interaction with the Model) for {@code FindLocationCommand}.
 */
public class FindLocationCommandTest {
    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void equals() {
        LocationContainsKeywordsPredicate firstPredicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("first"));
        LocationContainsKeywordsPredicate secondPredicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("second"));

        FindLocationCommand findFirstCommand = new FindLocationCommand(firstPredicate);
        FindLocationCommand findSecondCommand = new FindLocationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindLocationCommand findFirstCommandCopy = new FindLocationCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different eatery -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 0);
        LocationContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindLocationCommand command = new FindLocationCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEateryList());
    }

    @Test
    public void execute_multipleKeywords_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 3);
        LocationContainsKeywordsPredicate predicate = preparePredicate("S3 S4 E5");
        FindLocationCommand command = new FindLocationCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.DANIEL, TypicalEateries.ELLE),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_singleKeyword_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 5);
        LocationContainsKeywordsPredicate predicate = preparePredicate("Faculty");
        FindLocationCommand command = new FindLocationCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.DANIEL, TypicalEateries.ELLE,
                        TypicalEateries.FIONA, TypicalEateries.GEORGE),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_keyword_notCaseSensitive() {
        String expectedMessage;
        FindLocationCommand command;

        // Identical to stored input
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        LocationContainsKeywordsPredicate predicateCamelCase = preparePredicate("University");
        command = new FindLocationCommand(predicateCamelCase);
        expectedModel.updateFilteredEateryList(predicateCamelCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE, TypicalEateries.BENSON),
                model.getFilteredEateryList());

        // Capital letters
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        LocationContainsKeywordsPredicate predicateUpperCase = preparePredicate("SCIENCE");
        command = new FindLocationCommand(predicateUpperCase);
        expectedModel.updateFilteredEateryList(predicateUpperCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.DANIEL),
                model.getFilteredEateryList());

        // Mixed capital and small letters
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        LocationContainsKeywordsPredicate predicateMixedCase = preparePredicate("eNgInEeRiNg");
        command = new FindLocationCommand(predicateMixedCase);
        expectedModel.updateFilteredEateryList(predicateMixedCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ELLE, TypicalEateries.FIONA),
                model.getFilteredEateryList());

        // Small letters
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 1);
        LocationContainsKeywordsPredicate predicateLowerCase = preparePredicate("arts");
        command = new FindLocationCommand(predicateLowerCase);
        expectedModel.updateFilteredEateryList(predicateLowerCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.GEORGE),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_Keyword_searchesSubstrings() {
        String expectedMessage;
        FindLocationCommand command;

        // Searches substrings in words
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        LocationContainsKeywordsPredicate predicateSciencePart = preparePredicate("Sci");
        command = new FindLocationCommand(predicateSciencePart);
        expectedModel.updateFilteredEateryList(predicateSciencePart);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.DANIEL),
                model.getFilteredEateryList());

        // Deals with punctuation properly. Finds "S3," from "S3"
        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 1);
        LocationContainsKeywordsPredicate predicateWithCommaPart = preparePredicate("S3");
        command = new FindLocationCommand(predicateWithCommaPart);
        expectedModel.updateFilteredEateryList(predicateWithCommaPart);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL),
                model.getFilteredEateryList());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate preparePredicate(String userInput) {
        return new LocationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
