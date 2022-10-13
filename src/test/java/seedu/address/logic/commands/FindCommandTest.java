package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;
import static seedu.address.testutil.TypicalApplications.JANE_STREET;
import static seedu.address.testutil.TypicalApplications.SHOPEE;
import static seedu.address.testutil.TypicalApplications.getTypicalApplicationBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.CompanyContainsKeywordsPredicate;
import seedu.address.model.application.PositionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyContainsKeywordsPredicate firstCompanyPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyContainsKeywordsPredicate secondCompanyPredicate =
                new CompanyContainsKeywordsPredicate(Collections.singletonList("second"));

        PositionContainsKeywordsPredicate firstPositionPredicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("first"));
        PositionContainsKeywordsPredicate secondPositionPredicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstCompanyPredicate, firstPositionPredicate);
        FindCommand findSecondCommand = new FindCommand(secondCompanyPredicate, secondPositionPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCompanyPredicate, firstPositionPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicationFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 0);
        CompanyContainsKeywordsPredicate companyPredicate = prepareCompanyPredicate(" ");
        PositionContainsKeywordsPredicate positionPredicate = preparePositionPredicate(" ");
        FindCommand command = new FindCommand(companyPredicate, positionPredicate);
        expectedModel.updateFilteredApplicationList(companyPredicate.or(positionPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicationList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 3);
        CompanyContainsKeywordsPredicate companyPredicate = prepareCompanyPredicate("Shopee Street Backend");
        PositionContainsKeywordsPredicate positionPredicate = preparePositionPredicate("Shopee Street Backend");
        FindCommand command = new FindCommand(companyPredicate, positionPredicate);
        expectedModel.updateFilteredApplicationList(companyPredicate.or(positionPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SHOPEE, BYTEDANCE, JANE_STREET), model.getFilteredApplicationList());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyContainsKeywordsPredicate}.
     */
    private CompanyContainsKeywordsPredicate prepareCompanyPredicate(String userInput) {
        return new CompanyContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PositionContainsKeywordsPredicate}.
     */
    private PositionContainsKeywordsPredicate preparePositionPredicate(String userInput) {
        return new PositionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
