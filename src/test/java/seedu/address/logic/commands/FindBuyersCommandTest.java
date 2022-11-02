package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindBuyersCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equals() {
        BuyerNameContainsSubstringPredicate firstPredicate =
                new BuyerNameContainsSubstringPredicate("first");
        BuyerNameContainsSubstringPredicate secondPredicate =
                new BuyerNameContainsSubstringPredicate("second");

        FindBuyersCommand findFirstCommand = new FindBuyersCommand(firstPredicate);
        FindBuyersCommand findSecondCommand = new FindBuyersCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBuyersCommand findFirstCommandCopy = new FindBuyersCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different buyer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    //    @Test
    //    public void execute_zeroKeywords_noPersonFound() {
    //        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
    //        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
    //        FindBuyersCommand command = new FindBuyersCommand(predicate);
    //        expectedModel.updateFilteredPersonList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    //    }

    //    @Test
    //    public void execute_multipleKeywords_multiplePersonsFound() {
    //        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
    //        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
    //        FindBuyersCommand command = new FindBuyersCommand(predicate);
    //        expectedModel.updateFilteredPersonList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    //    }
}
