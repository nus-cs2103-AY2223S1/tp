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
import eatwhere.foodguide.model.eatery.TagsContainsKeywordsPredicate;
import eatwhere.foodguide.testutil.TypicalEateries;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FindTagCommandTest {
    private Model model = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEateries.getTypicalFoodGuide(), new UserPrefs());

    @Test
    public void equals() {
        TagsContainsKeywordsPredicate firstPredicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("first"));
        TagsContainsKeywordsPredicate secondPredicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTagCommand findFirstCommand = new FindTagCommand(firstPredicate);
        FindTagCommand findSecondCommand = new FindTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTagCommand findFirstCommandCopy = new FindTagCommand(firstPredicate);
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
        TagsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEateryList());
    }

    @Test
    public void execute_multipleKeywords_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        TagsContainsKeywordsPredicate predicate = preparePredicate("seafood alcohol");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE, TypicalEateries.BENSON),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_singleKeyword_multipleEateriesFound() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 2);
        TagsContainsKeywordsPredicate predicate = preparePredicate("steak");
        FindTagCommand command = new FindTagCommand(predicate);
        expectedModel.updateFilteredEateryList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.CARL, TypicalEateries.DANIEL),
                model.getFilteredEateryList());
    }

    @Test
    public void execute_keyword_notCaseSensitive() {
        String expectedMessage = String.format(MESSAGE_EATERIES_LISTED_OVERVIEW, 1);
        FindTagCommand command;

        // Identical to stored input
        TagsContainsKeywordsPredicate predicateCamelCase = preparePredicate("seafood");
        command = new FindTagCommand(predicateCamelCase);
        expectedModel.updateFilteredEateryList(predicateCamelCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE),
                model.getFilteredEateryList());

        // Capital letters
        TagsContainsKeywordsPredicate predicateUpperCase = preparePredicate("SEAFOOD");
        command = new FindTagCommand(predicateUpperCase);
        expectedModel.updateFilteredEateryList(predicateUpperCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE),
                model.getFilteredEateryList());

        // Mixed capital and small letters
        TagsContainsKeywordsPredicate predicateMixedCase = preparePredicate("sEaFoOd");
        command = new FindTagCommand(predicateMixedCase);
        expectedModel.updateFilteredEateryList(predicateMixedCase);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEateries.ALICE),
                model.getFilteredEateryList());

    }

    /**
     * Parses {@code userInput} into a {@code TagsContainsKeywordsPredicate}.
     */
    private TagsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
