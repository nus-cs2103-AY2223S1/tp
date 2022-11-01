package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTHLY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditAppointmentDescriptor;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_PLANTAG_SAVINGS = "Savings Plan";
    public static final String VALID_CLIENTTAG_CURRENT = "CURRENT";
    public static final String VALID_CLIENTTAG_POTENTIAL = "POTENTIAL";
    public static final String VALID_RISKTAG_HIGH = "HIGH";
    public static final String VALID_RISKTAG_LOW = "LOW";

    public static final String VALID_INCOME_AMY = "$1000";
    public static final String VALID_INCOME_BOB = "$10000";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_MONTHLY_AMY = "$200";
    public static final String VALID_MONTHLY_BOB = "$1000";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_DATETIME_21_JAN_2023 = "21-Jan-2023 01:00 AM";
    public static final String VALID_DATETIME_22_JAN_2023 = "22-Jan-2023 01:00 AM";
    public static final String VALID_DATETIME_23_JAN_2023 = "23-Jan-2023 01:00 AM";

    public static final String VALID_DATETIME_23_MAR_2024 = "23-Mar-2024 01:00 AM";
    public static final String INVALID_DATETIME_210_JAN_2023 = "210-Jan-2023 01:00 AM";

    public static final String VALID_LOCATION_NUS = "NUS";
    public static final String VALID_LOCATION_JURONGPOINT = "Jurong Point";
    public static final String VALID_LOCATION_WESTMALL = "West Mall";
    public static final String INVALID_LOCATION = "";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String INCOME_DESC_AMY = " " + PREFIX_INCOME + VALID_INCOME_AMY;
    public static final String INCOME_DESC_BOB = " " + PREFIX_INCOME + VALID_INCOME_BOB;
    public static final String FIRST_APPOINTMENT_DESC = " " + PREFIX_APPOINTMENT_DATE
            + VALID_DATETIME_21_JAN_2023 + " " + PREFIX_APPOINTMENT_LOCATION + VALID_LOCATION_NUS;
    public static final String SECOND_APPOINTMENT_DESC = " " + PREFIX_APPOINTMENT_DATE
            + VALID_DATETIME_22_JAN_2023 + " " + PREFIX_APPOINTMENT_LOCATION + VALID_LOCATION_JURONGPOINT;
    public static final String MONTHLY_DESC_AMY = " " + PREFIX_MONTHLY + VALID_MONTHLY_AMY;
    public static final String MONTHLY_DESC_BOB = " " + PREFIX_MONTHLY + VALID_MONTHLY_BOB;
    public static final String PLANTAG_DESC_SAVINGS = " " + PREFIX_PLANTAG + VALID_PLANTAG_SAVINGS;
    public static final String CLIENTTAG_DESC_CURRENT = " " + PREFIX_CLIENTTAG + VALID_CLIENTTAG_CURRENT;
    public static final String CLIENTTAG_DESC_POTENTIAL = " " + PREFIX_CLIENTTAG + VALID_CLIENTTAG_POTENTIAL;
    public static final String RISKTAG_DESC_HIGH = " " + PREFIX_RISKTAG + VALID_RISKTAG_HIGH;
    public static final String RISKTAG_DESC_LOW = " " + PREFIX_RISKTAG + VALID_RISKTAG_LOW;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PLANTAG_DESC = " " + PREFIX_PLANTAG + "none"; // must end with Plan
    public static final String INVALID_CLIENTTAG_DESC = " " + PREFIX_CLIENTTAG + "none"; // must be POTENTIAL or CURRENT
    public static final String INVALID_RISKTAG_DESC = " " + PREFIX_RISKTAG + "none"; // must be HIGH LOW or MEDIUM
    public static final String INVALID_INCOME_DESC = " " + PREFIX_INCOME + "000"; // income should include "$" sign
    public static final String INVALID_MONTHLY_DESC = " " + PREFIX_MONTHLY + "000"; // monthly should include "$"
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_BOTH_FIELD_APPOINTMENT_DESC = " " + PREFIX_APPOINTMENT_DATE
            + INVALID_DATETIME_210_JAN_2023 + " " + PREFIX_APPOINTMENT_LOCATION + INVALID_LOCATION;
    public static final String INVALID_DATE_FIELD_APPOINTMENT_DESC = " " + PREFIX_APPOINTMENT_DATE
            + INVALID_DATETIME_210_JAN_2023 + " " + PREFIX_APPOINTMENT_LOCATION + VALID_LOCATION_NUS;
    public static final String INVALID_LOCATION_FIELD_APPOINTMENT_DESC = " " + PREFIX_APPOINTMENT_DATE
            + VALID_DATETIME_21_JAN_2023 + " " + PREFIX_APPOINTMENT_LOCATION + INVALID_LOCATION;
    public static final String VALID_LOCATION_FIELD_APPOINTMENT_DESC = " "
            + PREFIX_APPOINTMENT_LOCATION + VALID_LOCATION_NUS;
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;

    public static final EditAppointmentDescriptor DESC_APPT_1;
    public static final EditAppointmentDescriptor DESC_APPT_2;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withIncome(VALID_INCOME_AMY).withMonthly(VALID_MONTHLY_AMY)
                .withRiskTag(VALID_RISKTAG_LOW).withPlanTag(VALID_PLANTAG_SAVINGS)
                .withClientTag(VALID_CLIENTTAG_CURRENT).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withIncome(VALID_INCOME_BOB).withMonthly(VALID_MONTHLY_AMY)
                .withRiskTag(VALID_RISKTAG_HIGH).withPlanTag(VALID_PLANTAG_SAVINGS)
                .withClientTag(VALID_CLIENTTAG_POTENTIAL).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_APPT_1 = new EditAppointmentDescriptorBuilder().withDateTime(VALID_DATETIME_21_JAN_2023)
                    .withLocation(VALID_LOCATION_NUS).build();
        DESC_APPT_2 = new EditAppointmentDescriptorBuilder().withDateTime(VALID_DATETIME_22_JAN_2023)
                .withLocation(VALID_LOCATION_JURONGPOINT).build();
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

}
