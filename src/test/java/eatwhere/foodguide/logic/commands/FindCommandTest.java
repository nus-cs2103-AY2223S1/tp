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
import eatwhere.foodguide.model.eatery.NameContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.TypicalEateries;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
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
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEateryList());
    }

    @Test
    public void execute_multipleKeywords_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Carl Elle Fiona");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.ELLE, TypicalEateries.FIONA),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_keyword_notCaseSensitive() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 1);
        FindCommand command;

        // Identical to stored input
        NameContainsKeywordsPredicate predicateCamelCase = preparePredicate("Abalone");
        command = new FindCommand(predicateCamelCase);
        expectedModel.updateFilteredEateryList(predicateCamelCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE),
                model.getFilteredEateryList());

        // Capital letters
        NameContainsKeywordsPredicate predicateUpperCase = preparePredicate("BAR");
        command = new FindCommand(predicateUpperCase);
        expectedModel.updateFilteredEateryList(predicateUpperCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.BENSON),
                model.getFilteredEateryList());

        // Mixed capital and small letters
        NameContainsKeywordsPredicate predicateMixedCase = preparePredicate("cAfEtErIa");
        command = new FindCommand(predicateMixedCase);
        expectedModel.updateFilteredEateryList(predicateMixedCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL),
                model.getFilteredEateryList());

        // Small letters
        NameContainsKeywordsPredicate predicateLowerCase = preparePredicate("diner");
        command = new FindCommand(predicateLowerCase);
        expectedModel.updateFilteredEateryList(predicateLowerCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.DANIEL),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_keyword_searchesSubstrings() {
        String expectedMessage;
        FindCommand command;

        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicateDanielPart = preparePredicate("dan");
        command = new FindCommand(predicateDanielPart);
        expectedModel.updateFilteredEateryList(predicateDanielPart);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.DANIEL),
                model.getFilteredEateryList());

        expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicateDanielEllePart = preparePredicate("el");
        command = new FindCommand(predicateDanielEllePart);
        expectedModel.updateFilteredEateryList(predicateDanielEllePart);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.DANIEL, TypicalEateries.ELLE),
                model.getFilteredEateryList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
