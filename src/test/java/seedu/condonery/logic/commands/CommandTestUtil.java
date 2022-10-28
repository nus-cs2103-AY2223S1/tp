package seedu.condonery.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.Client;
import seedu.condonery.model.client.ClientNameContainsKeywordsPredicate;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.PropertyDirectory;
import seedu.condonery.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.condonery.testutil.EditClientDescriptorBuilder;
import seedu.condonery.testutil.EditPropertyDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String CLIENT_VALID_NAME_AMY = "Amy Bee";
    public static final String CLIENT_VALID_NAME_BOB = "Bob Choo";
    public static final String CLIENT_VALID_PHONE_AMY = "11111111";
    public static final String CLIENT_VALID_PHONE_BOB = "22222222";
    public static final String CLIENT_VALID_EMAIL_AMY = "amy@example.com";
    public static final String CLIENT_VALID_EMAIL_BOB = "bob@example.com";
    public static final String CLIENT_VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String CLIENT_VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_PRICE_AMY = "100000";
    public static final String VALID_PRICE_BOB = "100000";
    public static final String CLIENT_VALID_TAG_HUSBAND = "husband";
    public static final String CLIENT_VALID_TAG_FRIEND = "friend";

    public static final String PROPERTY_VALID_NAME_SCOTTS = "SCOTTS SQUARE";
    public static final String PROPERTY_VALID_NAME_WHISTLER = "Whistler Grand";
    public static final String PROPERTY_VALID_ADDRESS_SCOTTS = "8 Scotts Rd, Singapore 228238";
    public static final String PROPERTY_VALID_ADDRESS_WHISTLER = "105 W Coast Vale, Singapore 126757";
    public static final String PROPERTY_VALID_TAG_SCOTTS = "City";
    public static final String PROPERTY_VALID_TAG_WHISTLER = "Central";
    public static final String PROPERTY_VALID_PRICE_SCOTTS = "100000";
    public static final String PROPERTY_VALID_PRICE_WHISTLER = "200000";
    public static final String PROPERTY_VALID_STATUS = "AVAILABLE";
    public static final String PROPERTY_VALID_TYPE_SCOTTS = "HDB";
    public static final String PROPERTY_VALID_TYPE_WHISTLER = "CONDO";

    public static final String PROPERTY_NAME_DESC_SCOTTS = " " + PREFIX_NAME + PROPERTY_VALID_NAME_SCOTTS;
    public static final String PROPERTY_ADDRESS_DESC_SCOTTS = " " + PREFIX_ADDRESS + PROPERTY_VALID_ADDRESS_SCOTTS;
    public static final String PROPERTY_TAGS_DESC_SCOTTS = " " + PREFIX_TAG + PROPERTY_VALID_TAG_SCOTTS;
    public static final String PROPERTY_PRICE_DESC_SCOTTS = " " + PREFIX_PRICE + PROPERTY_VALID_PRICE_SCOTTS;
    public static final String PROPERTY_TYPE_DESC_SCOTTS = " " + PREFIX_PROPERTY_TYPE + PROPERTY_VALID_TYPE_SCOTTS;
    public static final String PROPERTY_STATUS_DESC_SCOTTS = " " + PREFIX_PROPERTY_STATUS + PROPERTY_VALID_STATUS;

    public static final String PROPERTY_NAME_DESC_WHISTLER = " " + PREFIX_NAME + PROPERTY_VALID_NAME_WHISTLER;
    public static final String PROPERTY_ADDRESS_DESC_WHISTLER = " " + PREFIX_ADDRESS + PROPERTY_VALID_ADDRESS_WHISTLER;
    public static final String PROPERTY_TAGS_DESC_WHISTLER = " " + PREFIX_TAG + PROPERTY_VALID_TAG_WHISTLER;
    public static final String PROPERTY_PRICE_DESC_WHISTLER = " " + PREFIX_PRICE + PROPERTY_VALID_PRICE_WHISTLER;
    public static final String PROPERTY_TYPE_DESC_WHISTLER = " " + PREFIX_PROPERTY_TYPE + PROPERTY_VALID_TYPE_WHISTLER;
    public static final String PROPERTY_STATUS_DESC_WHISTLER = " " + PREFIX_PROPERTY_STATUS + PROPERTY_VALID_STATUS;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + CLIENT_VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + CLIENT_VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CLIENT_VALID_PHONE_AMY;
    public static final String EMAIL_DESC_AMY = " " + CLIENT_VALID_EMAIL_AMY;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + CLIENT_VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + CLIENT_VALID_ADDRESS_BOB;
    public static final String PRICE_DESC_AMY = " " + PREFIX_PRICE + VALID_PRICE_AMY;
    public static final String PRICE_DESC_BOB = " " + PREFIX_PRICE + VALID_PRICE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + CLIENT_VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + CLIENT_VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE; // empty string not allowed for price
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPropertyDescriptor PROPERTY_DESC_SCOTTS;
    public static final EditPropertyDescriptor PROPERTY_DESC_WHISTLER;
    public static final EditClientDescriptor CLIENT_DESC_AMY;
    public static final EditClientDescriptor CLIENT_DESC_BOB;

    static {
        PROPERTY_DESC_SCOTTS = new EditPropertyDescriptorBuilder()
                .withName(PROPERTY_VALID_NAME_SCOTTS)
                .withAddress(PROPERTY_VALID_ADDRESS_SCOTTS)
                .withPrice(PROPERTY_VALID_PRICE_SCOTTS)
                .withPropertyTypeEnum(PROPERTY_VALID_TYPE_SCOTTS)
                .withTags(PROPERTY_VALID_TAG_SCOTTS)
                .build();
        PROPERTY_DESC_WHISTLER = new EditPropertyDescriptorBuilder()
                .withName(PROPERTY_VALID_NAME_WHISTLER)
                .withAddress(PROPERTY_VALID_ADDRESS_WHISTLER)
                .withPrice(PROPERTY_VALID_PRICE_WHISTLER)
                .withPropertyTypeEnum(PROPERTY_VALID_TYPE_WHISTLER)
                .withTags(PROPERTY_VALID_TAG_WHISTLER).build();
        CLIENT_DESC_AMY = new EditClientDescriptorBuilder()
                .withName(CLIENT_VALID_NAME_AMY)
                .withAddress(CLIENT_VALID_ADDRESS_AMY)
                .withTags(CLIENT_VALID_TAG_FRIEND).build();

        CLIENT_DESC_BOB = new EditClientDescriptorBuilder()
                .withName(CLIENT_VALID_NAME_BOB)
                .withAddress(CLIENT_VALID_ADDRESS_BOB)
                .withTags(CLIENT_VALID_TAG_FRIEND).build();
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
        PropertyDirectory expectedPropertyDirectory = new PropertyDirectory(actualModel.getPropertyDirectory(),
                actualModel.getPropertyDirectoryFilePath());
        List<Property> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPropertyList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPropertyDirectory, actualModel.getPropertyDirectory());
        assertEquals(expectedFilteredList, actualModel.getFilteredPropertyList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the {@code Property} at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPropertyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPropertyList().size());

        Property person = model.getFilteredPropertyList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPropertyList(new PropertyNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPropertyList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the {@code Client} at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new ClientNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

}
