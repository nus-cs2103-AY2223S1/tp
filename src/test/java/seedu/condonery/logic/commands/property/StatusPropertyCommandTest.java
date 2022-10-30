package seedu.condonery.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.condonery.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalClients.getTypicalClientDirectory;
import static seedu.condonery.testutil.TypicalProperties.getTypicalPropertyDirectory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.CommandTestUtil;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.property.PropertyStatusContainsKeywordsPredicate;

public class StatusPropertyCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StatusPropertyCommand(null));
    }

    @Test
    public void execute_zeroKeywords_noPropertyFound() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        PropertyStatusContainsKeywordsPredicate predicate = preparePredicate(" ");
        StatusPropertyCommand command = new StatusPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void equals() {
        PropertyStatusContainsKeywordsPredicate pendingPredicate =
                new PropertyStatusContainsKeywordsPredicate(new ArrayList<>(List.of("PENDING")));
        PropertyStatusContainsKeywordsPredicate availablePredicate =
                new PropertyStatusContainsKeywordsPredicate(new ArrayList<>(List.of("AVAILABLE")));
        StatusPropertyCommand pendingStatus = new StatusPropertyCommand(pendingPredicate);
        StatusPropertyCommand condoStatus = new StatusPropertyCommand(availablePredicate);

        // same object -> returns true
        assertEquals(pendingStatus, pendingStatus);

        // same values -> returns true
        StatusPropertyCommand statusHdbCommandCopy = new StatusPropertyCommand(pendingPredicate);
        assertEquals(pendingStatus, statusHdbCommandCopy);

        // different types -> returns false
        assertNotEquals(1, pendingStatus);

        // null -> returns false
        assertNotNull(pendingStatus);

        // different person -> returns false
        assertNotEquals(pendingStatus, condoStatus);
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PropertyStatusContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PropertyStatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
