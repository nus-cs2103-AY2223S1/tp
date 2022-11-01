package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BuyerBook;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.NameContainsKeywordsPredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class BuyerCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_PRICE_RANGE_AMY = "200000 - 500000";
    public static final String VALID_PRICE_RANGE_BOB = "20 - 50";
    public static final String VALID_DESIRED_CHARACTERISTICS_AMY = "Toa Payoh; 5-Room";
    public static final String VALID_DESIRED_CHARACTERISTICS_BOB = "Jurong; 3-Room";
    public static final String VALID_PRIORITY_HIGH = "HIGH";
    public static final String VALID_PRIORITY_LOW = "LOW";
    public static final String VALID_NAME_HOME = "Home";
    public static final String VALID_ADDRESS_HOME = "25 College Avenue East";
    public static final String VALID_PRICE_HOME = "0";
    public static final String VALID_DESCRIPTION_HOME = "Tembu";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String PRICE_RANGE_DESC_AMY = " " + PREFIX_PRICE_RANGE + VALID_PRICE_RANGE_AMY;
    public static final String PRICE_RANGE_DESC_BOB = " " + PREFIX_PRICE_RANGE + VALID_PRICE_RANGE_BOB;
    public static final String DESIRED_CHARACTERISTICS_DESC_AMY = " " + PREFIX_CHARACTERISTICS
            + VALID_DESIRED_CHARACTERISTICS_AMY;
    public static final String DESIRED_CHARACTERISTICS_DESC_BOB = " " + PREFIX_CHARACTERISTICS
            + VALID_DESIRED_CHARACTERISTICS_BOB;
    public static final String TAG_DESC_PRIORITY_HIGH = " " + PREFIX_PRIORITY + VALID_PRIORITY_HIGH;
    public static final String TAG_DESC_PRIORITY_LOW = " " + PREFIX_PRIORITY + VALID_PRIORITY_LOW;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PRICE_RANGE_DESC = " " + PREFIX_PRICE_RANGE + "200"; // missing upper end value
    public static final String INVALID_DESIRED_CHARACTERISTICS_DESC = " " + PREFIX_CHARACTERISTICS;
    // empty string not allowed for desired characteristics
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "zzz";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditBuyerCommand.EditBuyerDescriptor DESC_AMY;
    public static final EditBuyerCommand.EditBuyerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withPriceRange(VALID_PRICE_RANGE_AMY).withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_AMY)
                .withPriority(VALID_PRIORITY_HIGH).build();
        DESC_BOB = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withPriceRange(VALID_PRICE_RANGE_BOB).withDesiredCharacteristics(VALID_DESIRED_CHARACTERISTICS_BOB)
                .withPriority(VALID_PRIORITY_LOW).build();
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
            System.out.println(expectedCommandResult.getFeedbackToUser());
            System.out.println(result.getFeedbackToUser());
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
     * - the buyer book, filtered buyer list and selected buyer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        BuyerBook expectedBuyerBook = new BuyerBook(actualModel.getBuyerBook());
        List<Buyer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBuyerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBuyerBook, actualModel.getBuyerBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredBuyerList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the PropertyBook, filtered property list and selected property in {@code actualModel} remain unchanged
     */
    public static void assertPropertyCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PropertyBook expectedPropertyBook = new PropertyBook(actualModel.getPropertyBook());
        List<Property> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPropertyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPropertyBook, actualModel.getPropertyBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPropertyList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the buyer at the given {@code targetIndex} in the
     * buyer book.
     */
    public static void showBuyerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBuyerList().size());

        Buyer buyer = model.getFilteredBuyerList().get(targetIndex.getZeroBased());
        final String[] splitName = buyer.getName().fullName.split("\\s+");
        model.updateFilteredBuyerList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBuyerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the property at the given {@code targetIndex} in the
     * {@code model}'s property book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property property = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String[] splitName = property.getPropertyName().fullName.split("\\s+");
        model.updateFilteredPropertyList(new PropertyNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPropertyList().size());
    }


}
