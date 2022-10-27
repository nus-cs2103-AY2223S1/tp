package seedu.condonery.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.CARL;
import static seedu.condonery.testutil.TypicalProperties.OASIS;
import static seedu.condonery.testutil.TypicalProperties.PINNACLE;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.PropertyPriceWithinRangePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class RangePropertyCommandTest {
    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void equals() {
        PropertyPriceWithinRangePredicate firstPredicate =
                new PropertyPriceWithinRangePredicate(1000000, 1999999);
        PropertyPriceWithinRangePredicate secondPredicate =
                new PropertyPriceWithinRangePredicate(2000000, 2999999);

        RangePropertyCommand rangeFirstCommand = new RangePropertyCommand(firstPredicate);
        RangePropertyCommand rangeSecondCommand = new RangePropertyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(rangeFirstCommand.equals(rangeFirstCommand));

        // same values -> returns true
        RangePropertyCommand rangeFirstCommandCopy = new RangePropertyCommand(firstPredicate);
        assertTrue(rangeFirstCommand.equals(rangeFirstCommandCopy));

        // different types -> returns false
        assertFalse(rangeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rangeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(rangeFirstCommand.equals(rangeSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 3);
        PropertyPriceWithinRangePredicate predicate = preparePredicate(1000000, 3000000);
        RangePropertyCommand command = new RangePropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PINNACLE, OASIS, CARL), model.getFilteredPropertyList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PropertyPriceWithinRangePredicate preparePredicate(int lower, int upper) {
        return new PropertyPriceWithinRangePredicate(lower, upper);
    }
}
