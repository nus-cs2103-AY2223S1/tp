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
import eatwhere.foodguide.model.eatery.PriceContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.TypicalEateries;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPriceCommand}.
 */
public class FindPriceCommandTest {
    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void equals() {
        PriceContainsKeywordsPredicate firstPredicate =
                new PriceContainsKeywordsPredicate(Collections.singletonList("first"));
        PriceContainsKeywordsPredicate secondPredicate =
                new PriceContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPriceCommand findFirstCommand = new FindPriceCommand(firstPredicate);
        FindPriceCommand findSecondCommand = new FindPriceCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPriceCommand findFirstCommandCopy = new FindPriceCommand(firstPredicate);
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
        PriceContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPriceCommand command = new FindPriceCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEateryList());
    }

    @Test
    public void execute_multipleKeywords_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 5);
        PriceContainsKeywordsPredicate predicate = preparePredicate("$ $$");
        FindPriceCommand command = new FindPriceCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE, TypicalEateries.BENSON,
                TypicalEateries.DANIEL, TypicalEateries.ELLE, TypicalEateries.GEORGE),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_singleKeyword_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        PriceContainsKeywordsPredicate predicate = preparePredicate("$$$");
        FindPriceCommand command = new FindPriceCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.FIONA),
                model.getFilteredEateryList());
    }

    /**
     * Parses {@code userInput} into a {@code PriceContainsKeywordsPredicate}.
     */
    private PriceContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PriceContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
