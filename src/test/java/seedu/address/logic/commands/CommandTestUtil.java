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
//import seedu.address.model.person.Person;
//import seedu.address.testutil.EditPersonDescriptorBuilder;

//import javax.print.DocFlavor;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // public static final String VALID_NAME_AMY = "Amy Bee";
    // public static final String VALID_NAME_BOB = "Bob Choo";
    // public static final String VALID_PHONE_AMY = "11111111";
    // public static final String VALID_PHONE_BOB = "22222222";
    // public static final String VALID_EMAIL_AMY = "amy@example.com";
    // public static final String VALID_EMAIL_BOB = "bob@example.com";
    // public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    // public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    // public static final String VALID_TAG_HUSBAND = "husband";
    // public static final String VALID_TAG_FRIEND = "friend";

    // public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    // public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    // public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    // public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    // public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    // public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    // public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    // public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    // public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    // public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    // public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    // public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    // public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    // public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    // public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String VALID_TYPE_INCOME = "i";
    public static final String VALID_TYPE_EXPENDITURE = "e";
    public static final String VALID_DESC_LUNCH = "Lunch";
    public static final String VALID_DESC_MOVIE = "Movie";
    public static final String VALID_DESC_DINNER = "Dinner";
    public static final String VALID_AMT_LUNCH = "5.20";
    public static final String VALID_AMT_MOVIE = "8.00";
    public static final String VALID_AMT_DINNER = "4.80";
    public static final String VALID_DATE_LUNCH = "20-08-2022";
    public static final String VALID_DATE_MOVIE = "21-08-2022";
    public static final String VALID_DATE_DINNER = "24-08-2022";
    public static final String VALID_TAG_LUNCH = "Lunch";
    public static final String VALID_TAG_DINNER = "Dinner";
    public static final String VALID_TAG_MOVIE = "Movie";

    public static final String TYPE_INCOME = " " + PREFIX_TYPE + VALID_TYPE_INCOME;
    public static final String TYPE_EXPENDITURE = " " + PREFIX_TYPE + VALID_TYPE_EXPENDITURE;
    public static final String DESC_LUNCH = " " + PREFIX_DESCRIPTION + VALID_DESC_LUNCH;
    public static final String DESC_MOVIE = " " + PREFIX_DESCRIPTION + VALID_DESC_MOVIE;
    public static final String DESC_DINNER = " " + PREFIX_DESCRIPTION + VALID_DESC_DINNER;
    public static final String AMT_LUNCH = " " + PREFIX_AMOUNT + VALID_AMT_LUNCH;
    public static final String AMT_MOVIE = " " + PREFIX_AMOUNT + VALID_AMT_MOVIE;
    public static final String AMT_DINNER = " " + PREFIX_AMOUNT + VALID_AMT_DINNER;
    public static final String DATE_LUNCH = " " + PREFIX_DATE + VALID_DATE_LUNCH;
    public static final String DATE_MOVIE = " " + PREFIX_DATE + VALID_DATE_MOVIE;
    public static final String DATE_DINNER = " " + PREFIX_DATE + VALID_DATE_DINNER;
    public static final String TAG_LUNCH = " " + PREFIX_TAG + VALID_TAG_LUNCH;
    public static final String TAG_MOVIE = " " + PREFIX_TAG + VALID_TAG_MOVIE;
    public static final String TAG_DINNER = " " + PREFIX_TAG + VALID_TAG_DINNER;
    public static final String INVALID_DESC = " " + PREFIX_DESCRIPTION + " ";
    public static final String INVALID_AMT = " " + PREFIX_AMOUNT + " ";
    public static final String INVALID_DATE = " " + PREFIX_DATE + " ";



    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // public static final EditCommand.EditPersonDescriptor DESC_AMY;
    // public static final EditCommand.EditPersonDescriptor DESC_BO
    // static {
    //     DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
    //             .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
    //             .withTags(VALID_TAG_FRIEND).build();
    //     DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
    //             .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
    //             .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    // }

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
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExpenditureList().size());

        Entry entry = model.getFilteredExpenditureList().get(targetIndex.getZeroBased());
        final String[] splitName = entry.toString().split("\\s+");
        model.updateFilteredEntryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredExpenditureList().size());
    }

}
