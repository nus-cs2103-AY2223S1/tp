package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMatchesKeywordsPredicate;
import seedu.address.model.person.contact.ContactType;
import seedu.address.testutil.SetPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_ROLE_AMY = "Software Engineer";
    public static final String VALID_ROLE_BOB = "Product Manager";
    public static final String VALID_TIMEZONE_AMY = "+8";
    public static final String VALID_TIMEZONE_BOB = "-8";
    public static final String VALID_TELEGRAM_AMY = "@amybee123";
    public static final String VALID_TELEGRAM_BOB = "@bobch00";
    public static final String VALID_EMAIL_AMY = "amyb@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@gmail.com";
    public static final String VALID_PHONE_AMY = "87654321";
    public static final String VALID_PHONE_BOB = "98765432";
    public static final String VALID_SLACK_AMY = "amybee123";
    public static final String VALID_SLACK_BOB = "bobch00";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String TIMEZONE_DESC_AMY = " " + PREFIX_TIMEZONE + VALID_TIMEZONE_AMY;
    public static final String TIMEZONE_DESC_BOB = " " + PREFIX_TIMEZONE + VALID_TIMEZONE_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String SLACK_DESC_AMY = " " + PREFIX_SLACK + VALID_SLACK_AMY;
    public static final String SLACK_DESC_BOB = " " + PREFIX_SLACK + VALID_SLACK_BOB;

    public static final String INVALID_NAME = "James&"; // '&' not allowed in names
    public static final String INVALID_ADDRESS = ""; // empty string not allowed for addresses
    public static final String INVALID_TAG = "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TIMEZONE = "gmt +8"; // should be a number with sign
    public static final String INVALID_TELEGRAM = "james"; // telegram starts with '@'
    public static final String INVALID_EMAIL = "james gmail"; // email should have an '@'
    public static final String INVALID_PHONE = "1800-SAFETY"; // phone shouldn't have letters
    public static final String INVALID_SLACK = "ReallyLoooongUsername"; // max slack length=20
    public static final String INVALID_ROLE = "Software-Engineer"; // role only alphanumeric

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_NAME;
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + INVALID_ADDRESS;
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + INVALID_TAG;
    public static final String INVALID_TIMEZONE_DESC = " " + PREFIX_TIMEZONE + INVALID_TIMEZONE;
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + INVALID_TELEGRAM;
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + INVALID_EMAIL;
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + INVALID_PHONE;
    public static final String INVALID_SLACK_DESC = " " + PREFIX_SLACK + INVALID_SLACK;
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + INVALID_ROLE;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final SetCommand.SetPersonDescriptor DESC_AMY;
    public static final SetCommand.SetPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new SetPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withRole(VALID_ROLE_AMY)
                .withContact(ContactType.TELEGRAM, VALID_TELEGRAM_AMY)
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .withContact(ContactType.PHONE, VALID_PHONE_AMY)
                .withContact(ContactType.SLACK, VALID_SLACK_AMY)
                .withTimezone(VALID_TIMEZONE_AMY)
                .build();

        DESC_BOB = new SetPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withRole(VALID_ROLE_BOB)
                .withContact(ContactType.TELEGRAM, VALID_TELEGRAM_BOB)
                .withContact(ContactType.EMAIL, VALID_EMAIL_BOB)
                .withContact(ContactType.PHONE, VALID_PHONE_BOB)
                .withContact(ContactType.SLACK, VALID_SLACK_BOB)
                .withTimezone(VALID_TIMEZONE_BOB)
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
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getSortedFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getSortedFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedFilteredPersonList().size());

        Person person = model.getSortedFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.filterPersonList(new PersonMatchesKeywordsPredicate(splitName[0]));

        assertEquals(1, model.getSortedFilteredPersonList().size());
    }

}
