package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MyInsuRec;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingContainsClientPredicate;
import seedu.address.model.product.Product;
import seedu.address.testutil.EditClientDescriptorBuilder;

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
    public static final LocalDate VALID_BIRTHDAY_AMY = LocalDate.of(1952, 1, 1);
    public static final LocalDate VALID_BIRTHDAY_BOB = LocalDate.of(2000, 12, 31);
    public static final String VALID_PRODUCT_1 = "Product1";
    public static final String VALID_PRODUCT_2 = "Product2";
    public static final String VALID_START_TIME_MEETING1 = "0720";
    public static final String VALID_START_TIME_MEETING2 = "0720";
    public static final String VALID_END_TIME_MEETING1 = "0820";
    public static final String VALID_END_TIME_MEETING2 = "0820";
    public static final String VALID_DATE_MEETING1 = LocalDate.now().plusDays(1)
            .format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    public static final String VALID_DATE_MEETING2 = LocalDate.now().plusDays(5)
            .format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    public static final String INVALID_PRODUCT_1 = "Unadded Product";

    public static final String VALID_DESCRIPTION_MEETING1 = "meeting1";
    public static final String VALID_DESCRIPTION_MEETING2 = "meeting2";
    public static final LocalDate VALID_MEETING_DATE_MEETING1 = LocalDate.of(2020, 1, 8);
    public static final LocalDate VALID_MEETING_DATE_MEETING2 = LocalDate.of(2020, 5, 5);
    public static final LocalTime VALID_MEETING_TIME_MEETING1 = LocalTime.of(7, 20, 45, 342123342);
    public static final LocalTime VALID_MEETING_TIME_MEETING2 = LocalTime.of(7, 20, 45, 342123321);

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + "01011952";
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + "31122000";
    public static final String PRODUCT_DESC_PRODUCT1 = " " + PREFIX_PRODUCT + VALID_PRODUCT_1;
    public static final String PRODUCT_DESC_PRODUCT2 = " " + PREFIX_PRODUCT + VALID_PRODUCT_2;
    public static final String MEETING_DESC_MEETING1 = " " + PREFIX_START_TIME + VALID_START_TIME_MEETING1
            + " " + PREFIX_END_TIME + VALID_END_TIME_MEETING1
            + " " + PREFIX_DATE + VALID_DATE_MEETING1
            + " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MEETING1;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PRODUCT_DESC = " " + PREFIX_PRODUCT + "hubby*"; // '*' not allowed in Products
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START_TIME + "7653"; // time is not allowed
    public static final String INVALID_END_TIME_DESC = " " + PREFIX_END_TIME + "7653"; // time is not allowed
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "13132000"; // date is not allowed
    public static final String INVALID_BIRTHDAY_1_DESC = " " + PREFIX_BIRTHDAY + "30022020"; // 30 feb not exist
    public static final String INVALID_BIRTHDAY_2_DESC = " "
            + PREFIX_BIRTHDAY + "29022021"; // 29 feb not exist non leap year
    public static final String INVALID_BIRTHDAY_3_DESC = " " + PREFIX_BIRTHDAY + "31112020"; // 31 nov not exist

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditClientCommand.EditClientDescriptor DESC_AMY;
    public static final EditClientCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBirthday(VALID_BIRTHDAY_AMY).withProducts(VALID_PRODUCT_1).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withBirthday(VALID_BIRTHDAY_BOB).withProducts(VALID_PRODUCT_1, VALID_PRODUCT_2).build();
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
     * - MyInsuRec, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MyInsuRec expectedMyInsuRec = new MyInsuRec(actualModel.getMyInsuRec());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMyInsuRec, actualModel.getMyInsuRec());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }
    /**
     * Updates {@code model}'s client filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s MyInsuRec.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s meeting filtered list to show only the meeting at the given {@code targetIndex} in
     * the {@code model}'s MyInsuRec.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final Client[] clientList = {meeting.getClient()};
        model.updateFilteredMeetingList(new MeetingContainsClientPredicate(Arrays.asList(clientList)));

        assertEquals(1, model.getFilteredMeetingList().size());
    }

    /**
     * Updates {@code model}'s filtered product list to show only the product at the given {@code targetIndex} in the
     * {@code model}'s MyInsuRec.
     */
    public static void showProductAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Product product = model.getFilteredProductList().get(targetIndex.getZeroBased());
        model.updateFilteredProductList(item -> item.equals(product));

        assertEquals(1, model.getFilteredProductList().size());
    }
}
