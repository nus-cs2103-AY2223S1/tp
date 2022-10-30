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
import seedu.condonery.model.property.PropertyTypeContainsKeywordsPredicate;

public class TypePropertyCommandTest {

    private final Model model = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPropertyDirectory(),
            getTypicalClientDirectory(), new UserPrefs());

    @Test
    public void constructor_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TypePropertyCommand(null));
    }

    @Test
    public void execute_zeroKeywords_noPropertyFound() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 0);
        PropertyTypeContainsKeywordsPredicate predicate = preparePredicate(" ");
        TypePropertyCommand command = new TypePropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void equals() {
        PropertyTypeContainsKeywordsPredicate hdbPredicate =
                new PropertyTypeContainsKeywordsPredicate(new ArrayList<>(List.of("HDB")));
        PropertyTypeContainsKeywordsPredicate condoPredicate =
                new PropertyTypeContainsKeywordsPredicate(new ArrayList<>(List.of("CONDO")));
        TypePropertyCommand hdbStatus = new TypePropertyCommand(hdbPredicate);
        TypePropertyCommand condoStatus = new TypePropertyCommand(condoPredicate);

        // same object -> returns true
        assertEquals(hdbStatus, hdbStatus);

        // same values -> returns true
        TypePropertyCommand statusHdbCommandCopy = new TypePropertyCommand(hdbPredicate);
        assertEquals(hdbStatus, statusHdbCommandCopy);

        // different types -> returns false
        assertNotEquals(1, hdbStatus);

        // null -> returns false
        assertNotNull(hdbStatus);

        // different person -> returns false
        assertNotEquals(hdbStatus, condoStatus);
    }
    private PropertyTypeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PropertyTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
