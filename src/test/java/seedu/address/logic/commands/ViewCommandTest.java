package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.EXPENDITURE_BY_CATEGORY;
import static seedu.address.logic.commands.CommandTestUtil.EXPENDITURE_BY_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.address.model.GraphConfiguration;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCommand.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalPennyWise(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        ViewCommand viewCommandExpenditureByCategory = new ViewCommand(EXPENDITURE_BY_CATEGORY);
        String expectedMessageExpenditureByCategory = String.format(
                ViewCommand.MESSAGE_SUCCESS,
                EXPENDITURE_BY_CATEGORY.getEntryType(),
                EXPENDITURE_BY_CATEGORY.getGraphType()
        );
        // Explicitly construct an expected command result because the ViewCommand has a side effect of modifying
        // the graph panel and thus cannot rely on the default graph configuration.
        CommandResult expectedCommandResultExpenditureByCategory = new CommandResult(
                expectedMessageExpenditureByCategory,
                false,
                false,
                new GraphConfiguration(
                        EXPENDITURE_BY_CATEGORY.getEntryType(),
                        EXPENDITURE_BY_CATEGORY.getGraphType(),
                        true
                )
        );
        assertCommandSuccess(viewCommandExpenditureByCategory, model, expectedCommandResultExpenditureByCategory, model);

        ViewCommand viewCommandExpenditureByMonth = new ViewCommand(EXPENDITURE_BY_MONTH);
        String expectedMessageExpenditureByMonth = String.format(
                ViewCommand.MESSAGE_SUCCESS,
                EXPENDITURE_BY_MONTH.getEntryType(),
                EXPENDITURE_BY_MONTH.getGraphType()
        );
        CommandResult expectedCommandResultExpenditureByMonth = new CommandResult(
                expectedMessageExpenditureByMonth,
                false,
                false,
                new GraphConfiguration(
                        EXPENDITURE_BY_MONTH.getEntryType(),
                        EXPENDITURE_BY_MONTH.getGraphType(),
                        true
                )
        );
        assertCommandSuccess(viewCommandExpenditureByMonth, model, expectedCommandResultExpenditureByMonth, model);
    }

    @Test
    public void equals() {
        final ViewCommand standardCommand = new ViewCommand(EXPENDITURE_BY_MONTH);

        // same values -> returns true
        ViewEntriesDescriptor copyDescriptor = new ViewEntriesDescriptor(EXPENDITURE_BY_MONTH);
        ViewCommand commandWithSameValues = new ViewCommand(copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new ViewCommand(EXPENDITURE_BY_CATEGORY));
    }

}
