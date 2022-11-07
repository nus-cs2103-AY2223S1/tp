package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PURPOSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DOB_AMY = "09/09/1969";
    public static final String VALID_DOB_BOB = "08/12/2000";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_GENDER_AMY = "Female";

    public static final String VALID_GENDER_BOB = "Male";

    public static final String VALID_GENDER_TYPE = "g";

    public static final String VALID_AGE_TYPE = "a";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    public static final String DOB_DESC_AMY = " " + PREFIX_DOB + VALID_DOB_AMY;

    public static final String DOB_DESC_BOB = " " + PREFIX_DOB + VALID_DOB_BOB;

    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;

    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "unknown";
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "c";

    public static final String TYPE_DESC_GENDER = " " + PREFIX_TYPE + VALID_GENDER_TYPE;

    public static final String TYPE_DESC_AGE = " " + PREFIX_TYPE + VALID_AGE_TYPE;
    public static final String INVALID_DOB_DESC = " " + PREFIX_DOB + "1.1.2012";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    // Values used by Event Methods
    public static final String VALID_EVENT_TITLE_CHOCOLATE = "Chocolate Sale";
    public static final String VALID_DATE_CHOCOLATE = "10/10/2022";
    public static final String VALID_START_TIME_CHOCOLATE = "13:30";
    public static final String VALID_PURPOSE_CHOCOLATE = "$10 off any 2 boxes of chocolates";
    public static final String VALID_EVENT_TITLE_SOCKS = "Socks Sale";
    public static final String VALID_DATE_SOCKS = "12/12/2022";
    public static final String VALID_START_TIME_SOCKS = "09:30";
    public static final String VALID_PURPOSE_SOCKS = "$1 off any pair of socks";

    public static final String EVENT_TITLE_DESC_CHOCOLATE = " " + PREFIX_EVENT_TITLE + VALID_EVENT_TITLE_CHOCOLATE;
    public static final String DATE_DESC_CHOCOLATE = " " + PREFIX_START_DATE + VALID_DATE_CHOCOLATE;
    public static final String START_TIME_DESC_CHOCOLATE = " " + PREFIX_START_TIME + VALID_START_TIME_CHOCOLATE;
    public static final String PURPOSE_DESC_CHOCOLATE = " " + PREFIX_PURPOSE + VALID_PURPOSE_CHOCOLATE;

    public static final String EVENT_TITLE_DESC_SOCKS = " " + PREFIX_EVENT_TITLE + VALID_EVENT_TITLE_SOCKS;
    public static final String DATE_DESC_SOCKS = " " + PREFIX_START_DATE + VALID_DATE_SOCKS;
    public static final String START_TIME_DESC_SOCKS = " " + PREFIX_START_TIME + VALID_START_TIME_SOCKS;
    public static final String PURPOSE_DESC_SOCKS = " " + PREFIX_PURPOSE + VALID_PURPOSE_SOCKS;

    // '$' not allowed in eventTitle
    public static final String INVALID_EVENT_TITLE_DESC = " " + PREFIX_EVENT_TITLE + "$$Car Sale";
    public static final String INVALID_DATE_DESC = " " + PREFIX_START_DATE + "0/1/1/1"; // invalid value for date
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START_TIME + "13212:30"; // invalid time > 24:00
    public static final String INVALID_PURPOSE_DESC = " " + PREFIX_PURPOSE; // empty string not allowed for purpose
    public static final EditEventCommand.EditEventDescriptor DESC_CHOCOLATE;
    public static final EditEventCommand.EditEventDescriptor DESC_SOCKS;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withGender(VALID_GENDER_AMY).withDob(VALID_DOB_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_BOB).withDob(VALID_DOB_BOB).build();
        DESC_CHOCOLATE = new EditEventDescriptorBuilder().withEventTitle(VALID_EVENT_TITLE_CHOCOLATE)
                .withDate(VALID_DATE_CHOCOLATE).withStartTime(VALID_START_TIME_CHOCOLATE)
                .withPurpose(VALID_PURPOSE_CHOCOLATE).build();
        DESC_SOCKS = new EditEventDescriptorBuilder().withEventTitle(VALID_EVENT_TITLE_SOCKS)
                .withDate(VALID_DATE_SOCKS).withStartTime(VALID_START_TIME_SOCKS)
                .withPurpose(VALID_PURPOSE_SOCKS).build();
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
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the events at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitEventTitle = event.getEventTitle().eventTitle.split("\\s+");
        model.updateFilteredEventList(new EventTitleContainsKeywordsPredicate(Arrays.asList(splitEventTitle[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
