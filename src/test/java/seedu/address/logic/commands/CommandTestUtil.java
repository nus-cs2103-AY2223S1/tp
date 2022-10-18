package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.PennyWise;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEntryDescriptorBuilder;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.EditEntryDescriptorBuilder;

//import javax.print.DocFlavor;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TYPE_INCOME = "i";
    public static final String VALID_TYPE_EXPENDITURE = "e";
    public static final String VALID_DESC_LUNCH = "Lunch";
    public static final String VALID_DESC_MOVIE = "Movie";
    public static final String VALID_DESC_DINNER = "Dinner";
    public static final String VALID_DESC_TUITION = "Tuition";
    public static final String VALID_AMT_LUNCH = "5.20";
    public static final String VALID_AMT_MOVIE = "8.00";
    public static final String VALID_AMT_DINNER = "4.80";
    public static final String VALID_AMT_TUITION = "40.00";
    public static final String VALID_DATE_LUNCH = "20-08-2022";
    public static final String VALID_DATE_MOVIE = "21-08-2022";
    public static final String VALID_DATE_DINNER = "24-08-2022";
    public static final String VALID_DATE_TUITION = "18-08-2022";
    public static final String VALID_TAG_MEAL = "Food";
    public static final String VALID_TAG_PERSONAL = "Others";
    public static final String VALID_TAG_LUNCH = "Food";
    public static final String VALID_TAG_DINNER = "Food";
    public static final String VALID_TAG_MOVIE = "Entertainment";
    public static final String VALID_TAG_TUITION = "Education";
    public static final String VALID_DESC_ALLOWANCE = "Allowance";
    public static final String VALID_AMT_ALLOWANCE = "300";
    public static final String VALID_DATE_ALLOWANCE = "01-10-2022";
    public static final String VALID_TAG_ALLOWANCE = "Allowance";

    public static final String TYPE_INCOME = " " + PREFIX_TYPE + VALID_TYPE_INCOME;
    public static final String TYPE_EXPENDITURE = " " + PREFIX_TYPE + VALID_TYPE_EXPENDITURE;
    public static final String DESC_ALLOWANCE = " " + PREFIX_DESCRIPTION + VALID_DESC_ALLOWANCE;
    public static final String DESC_LUNCH = " " + PREFIX_DESCRIPTION + VALID_DESC_LUNCH;
    public static final String DESC_MOVIE = " " + PREFIX_DESCRIPTION + VALID_DESC_MOVIE;
    public static final String DESC_DINNER = " " + PREFIX_DESCRIPTION + VALID_DESC_DINNER;
    public static final String DESC_TUITION = " " + PREFIX_DESCRIPTION + VALID_DESC_TUITION;
    public static final String AMT_ALLOWANCE = " " + PREFIX_AMOUNT + VALID_AMT_ALLOWANCE;
    public static final String AMT_LUNCH = " " + PREFIX_AMOUNT + VALID_AMT_LUNCH;
    public static final String AMT_MOVIE = " " + PREFIX_AMOUNT + VALID_AMT_MOVIE;
    public static final String AMT_DINNER = " " + PREFIX_AMOUNT + VALID_AMT_DINNER;
    public static final String AMT_TUITION = " " + PREFIX_AMOUNT + VALID_AMT_TUITION;
    public static final String DATE_ALLOWANCE = " " + PREFIX_DATE + VALID_DATE_ALLOWANCE;
    public static final String DATE_LUNCH = " " + PREFIX_DATE + VALID_DATE_LUNCH;
    public static final String DATE_MOVIE = " " + PREFIX_DATE + VALID_DATE_MOVIE;
    public static final String DATE_DINNER = " " + PREFIX_DATE + VALID_DATE_DINNER;
    public static final String DATE_TUITION = " " + PREFIX_DATE + VALID_DATE_TUITION;
    public static final String TAG_ALLOWANCE = " " + PREFIX_TAG + VALID_TAG_ALLOWANCE;
    public static final String TAG_LUNCH = " " + PREFIX_TAG + VALID_TAG_LUNCH;
    public static final String TAG_MOVIE = " " + PREFIX_TAG + VALID_TAG_MOVIE;
    public static final String TAG_DINNER = " " + PREFIX_TAG + VALID_TAG_DINNER;
    public static final String TAG_DESC_MEAL = " " + PREFIX_TAG + VALID_TAG_MEAL;
    public static final String TAG_DESC_PERSONAL = " " + PREFIX_TAG + VALID_TAG_PERSONAL;
    public static final String TAG_TUITION = " " + PREFIX_TAG + VALID_TAG_TUITION;

    public static final String INVALID_DESC = " " + PREFIX_DESCRIPTION + "Bre&kfast "; // '&' not allowed in description
    public static final String INVALID_TYPE = " " + PREFIX_TYPE + "g "; // only 'e' and 'i' types allowed
    public static final String INVALID_AMT = " " + PREFIX_AMOUNT + "12.20a "; // 'a' not allowed in amount
    public static final String INVALID_DATE = " " + PREFIX_DATE + "32-32-3232 "; // only valid date strings allowed
    public static final String INVALID_TAG = " " + PREFIX_TAG + "tag* "; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEntryDescriptor LUNCH;
    public static final EditCommand.EditEntryDescriptor DINNER;
    static {
        LUNCH = new EditEntryDescriptorBuilder().withDescription(VALID_DESC_LUNCH)
                .withType(VALID_TYPE_EXPENDITURE).withAmount(VALID_AMT_LUNCH).withDate(VALID_DATE_LUNCH)
                .build();
        DINNER = new EditEntryDescriptorBuilder().withDescription(VALID_DESC_DINNER)
                .withType(VALID_TYPE_EXPENDITURE).withAmount(VALID_AMT_DINNER).withDate(VALID_DATE_DINNER)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PennyWise expectedPennyWise = new PennyWise(actualModel.getPennyWise());
        List<Entry> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenditureList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPennyWise, actualModel.getPennyWise());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenditureList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the expenditure at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showExpenditureAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExpenditureList().size());

        Entry entry = model.getFilteredExpenditureList().get(targetIndex.getZeroBased());
        final String[] splitName = entry.toString().split("\\s+");
        model.updateFilteredEntryList(
                new NameContainsKeywordsPredicate(Arrays.asList(splitName[0].replace(";", ""))));
        assertEquals(1, model.getFilteredExpenditureList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the income at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showIncomeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExpenditureList().size());

        Entry entry = model.getFilteredExpenditureList().get(targetIndex.getZeroBased());
        final String[] splitName = entry.toString().split("; ");
        model.updateFilteredExpenditureList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredExpenditureList().size());
    }

}
