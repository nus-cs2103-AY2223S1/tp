package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyNameContainsSubstringPredicate;
import seedu.address.testutil.EditPropertyDescriptorBuilder;

/**
* Contains helper methods for testing property commands.
*/
public class PropertyCommandTestUtil {

    public static final String VALID_NAME_PROPERTY1 = "Heng Mui Keng Terrace HDB";
    public static final String VALID_NAME_HOME = "Homegrounds";
    public static final String VALID_PRICE_PROPERTY1 = "1000000";
    public static final String VALID_PRICE_HOME = "500000";
    public static final String VALID_ADDRESS_PROPERTY1 = "Blk 123, Heng Mui Keng Terrace";
    public static final String VALID_ADDRESS_HOME = "25 College Avenue East";
    public static final String VALID_DESCRIPTION_PROPERTY1 = "A random HDB flat at Heng Mui Keng Terrace";
    public static final String VALID_DESCRIPTION_HOME = "Where I stay";
    public static final String VALID_CHARACTERISTICS_PROPERTY1 = "HDB; 5-room; bright; sunny";
    public static final String VALID_CHARACTERISTICS_HOME = "homey; spacious; comfortable; modern";
    public static final String VALID_OWNER_NAME_PROPERTY1 = "Anthony";
    public static final String VALID_OWNER_NAME_HOME = "Bob";
    public static final String VALID_OWNER_PHONE_PROPERTY1 = "91234567";
    public static final String VALID_OWNER_PHONE_HOME = "81234567";
    public static final String NAME_DESC_PROPERTY1 = " " + PREFIX_NAME + " " + VALID_NAME_PROPERTY1;
    public static final String NAME_DESC_HOME = " " + PREFIX_NAME + " " + VALID_NAME_HOME;
    public static final String PRICE_DESC_PROPERTY1 = " " + PREFIX_PRICE + " " + VALID_PRICE_PROPERTY1;
    public static final String PRICE_DESC_HOME = " " + PREFIX_PRICE + " " + VALID_PRICE_HOME;
    public static final String ADDRESS_DESC_PROPERTY1 = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_PROPERTY1;
    public static final String ADDRESS_DESC_HOME = " " + PREFIX_ADDRESS + " " + VALID_ADDRESS_HOME;
    public static final String DESCRIPTION_DESC_PROPERTY1 = " " + PREFIX_DESCRIPTION + " "
            + VALID_DESCRIPTION_PROPERTY1;
    public static final String DESCRIPTION_DESC_HOME = " " + PREFIX_DESCRIPTION + " " + VALID_DESCRIPTION_HOME;
    public static final String CHARACTERISTICS_DESC_PROPERTY1 = " " + PREFIX_CHARACTERISTICS + " "
            + VALID_CHARACTERISTICS_PROPERTY1;
    public static final String CHARACTERISTICS_DESC_HOME = " " + PREFIX_CHARACTERISTICS + " "
            + VALID_CHARACTERISTICS_HOME;
    public static final String OWNER_NAME_DESC_PROPERTY1 = " " + PREFIX_OWNER_NAME + " "
            + VALID_OWNER_NAME_PROPERTY1;
    public static final String OWNER_NAME_DESC_HOME = " " + PREFIX_OWNER_NAME + " " + VALID_OWNER_NAME_HOME;
    public static final String OWNER_PHONE_DESC_PROPERTY1 = " " + PREFIX_PHONE + " " + VALID_OWNER_PHONE_PROPERTY1;
    public static final String OWNER_PHONE_DESC_HOME = " " + PREFIX_PHONE + " " + VALID_OWNER_PHONE_HOME;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + " " + "123.45x"; // 'x' not allowed in prices
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION; // description cannot be empty
    public static final String INVALID_CHARACTERISTICS_DESC = " " + PREFIX_CHARACTERISTICS; // cannot be empty if used
    public static final String INVALID_OWNER_NAME_DESC = " " + PREFIX_OWNER_NAME + " " + "Tom%";
    // '%' not allowed in names
    public static final String INVALID_OWNER_PHONE_DESC = " " + PREFIX_PHONE + " " + "911a";
    // 'a' not allowed in phones

    public static final EditPropertyCommand.EditPropertyDescriptor DESC_PROPERTY1;
    public static final EditPropertyCommand.EditPropertyDescriptor DESC_HOME;

    static {
        DESC_PROPERTY1 = new EditPropertyDescriptorBuilder().withName(VALID_NAME_PROPERTY1)
                .withPrice(VALID_PRICE_PROPERTY1).withAddress(VALID_ADDRESS_PROPERTY1)
                .withDescription(VALID_DESCRIPTION_PROPERTY1)
                .withCharacteristics(VALID_CHARACTERISTICS_PROPERTY1)
                .withOwner(VALID_OWNER_NAME_PROPERTY1, VALID_OWNER_PHONE_PROPERTY1).build();

        DESC_HOME = new EditPropertyDescriptorBuilder().withName(VALID_NAME_HOME)
                .withPrice(VALID_PRICE_HOME).withAddress(VALID_ADDRESS_HOME)
                .withDescription(VALID_DESCRIPTION_HOME)
                .withCharacteristics(VALID_CHARACTERISTICS_HOME)
                .withOwner(VALID_OWNER_NAME_HOME, VALID_OWNER_PHONE_HOME).build();
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
     * Updates {@code model}'s filtered list to show only the property at the given {@code targetIndex} in the
     * {@code model}'s property book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property property = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String[] splitName = property.getPropertyName().fullName.split("\\s+");
        model.updateFilteredPropertyList(new PropertyNameContainsSubstringPredicate(splitName[0]));

        assertEquals(1, model.getFilteredPropertyList().size());
    }

}
