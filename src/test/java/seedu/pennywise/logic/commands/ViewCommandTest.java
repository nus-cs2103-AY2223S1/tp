package seedu.pennywise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_CATEGORY;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.pennywise.model.GraphConfiguration;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.EntryInYearMonthPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCommand.
 */
public class ViewCommandTest {

    private Model model;

    @BeforeEach
    // We set the model before each test suite to prevent diffusion of the model contents as
    // the ViewCommand has a side effect of modifying the contents of the model.
    public void setModel() {
        model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecifiedForCategory_success() {
        ViewCommand viewCommand = new ViewCommand(EXPENDITURE_BY_CATEGORY);
        String expectedMessage = String.format(
                ViewCommand.MESSAGE_SUCCESS,
                EXPENDITURE_BY_CATEGORY.getEntryType(),
                EXPENDITURE_BY_CATEGORY.getGraphType()
        );
        // Explicitly construct an expected command result because the ViewCommand has a side effect of modifying
        // the graph panel and thus cannot rely on the default graph configuration.
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage,
                false,
                false,
                new GraphConfiguration(
                        EXPENDITURE_BY_CATEGORY.getEntryType(),
                        EXPENDITURE_BY_CATEGORY.getGraphType(),
                        true
                )
        );
        assertCommandSuccess(
                viewCommand,
                model,
                expectedCommandResult,
                model
        );
    }

    @Test
    public void execute_allFieldsSpecifiedForMonth_success() {
        ViewCommand viewCommand = new ViewCommand(EXPENDITURE_BY_MONTH);
        String expectedMessage = String.format(
                ViewCommand.MESSAGE_SUCCESS,
                EXPENDITURE_BY_MONTH.getEntryType(),
                EXPENDITURE_BY_MONTH.getGraphType()
        );
        // Explicitly construct an expected command result because the ViewCommand has a side effect of modifying
        // the graph panel and thus cannot rely on the default graph configuration.
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage,
                false,
                false,
                new GraphConfiguration(
                        EXPENDITURE_BY_MONTH.getEntryType(),
                        EXPENDITURE_BY_MONTH.getGraphType(),
                        true
                )
        );

        Model expectedModel = new ModelManager(new PennyWise(model.getPennyWise()), new UserPrefs());
        assert EXPENDITURE_BY_MONTH.getYearMonth().isPresent();
        expectedModel.updateFilteredExpenditureList(
                new EntryInYearMonthPredicate(EXPENDITURE_BY_MONTH.getYearMonth().get())
        );
        assertCommandSuccess(
                viewCommand,
                model,
                expectedCommandResult,
                expectedModel
        );
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
