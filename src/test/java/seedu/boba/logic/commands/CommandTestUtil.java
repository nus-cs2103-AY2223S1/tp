package seedu.boba.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_BIRTHDAY_MONTH;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_REWARD;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.boba.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.boba.commons.core.index.Index;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.NameContainsKeywordsPredicate;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "85355255";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_BIRTHDAY_MONTH_AMY = "1";
    public static final String VALID_BIRTHDAY_MONTH_BOB = "2";
    public static final String VALID_REWARD_AMY = "420";
    public static final String VALID_REWARD_BOB = "5000";
    public static final String VALID_TAG_GOLD = "GOLD";
    public static final String VALID_TAG_MEMBER = "MEMBER";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String BIRTHDAY_MONTH_DESC_AMY = " " + PREFIX_BIRTHDAY_MONTH + VALID_BIRTHDAY_MONTH_AMY;
    public static final String BIRTHDAY_MONTH_DESC_BOB = " " + PREFIX_BIRTHDAY_MONTH + VALID_BIRTHDAY_MONTH_BOB;
    public static final String REWARD_DESC_AMY = " " + PREFIX_REWARD + VALID_REWARD_AMY;
    public static final String REWARD_DESC_BOB = " " + PREFIX_REWARD + VALID_REWARD_BOB;
    public static final String TAG_DESC_MEMBER = " " + PREFIX_TAG + VALID_TAG_MEMBER;
    public static final String TAG_DESC_GOLD = " " + PREFIX_TAG + VALID_TAG_GOLD;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_BIRTHDAY_MONTH_DESC = " " + PREFIX_BIRTHDAY_MONTH + "two"; // not integer value
    public static final String INVALID_REWARD_DESC = " " + PREFIX_REWARD; // empty string not allowed for rewards
    public static final String INVALID_NEGATIVE_REWARD_DESC = " " + PREFIX_REWARD + "-1";
    public static final String EXCEED_MAX_INTEGER_REWARD_DESC =  " " + PREFIX_REWARD
            + "2147483648"; // values more than max integer not allowed for rewards
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "GOLD*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withBirthdayMonth(VALID_BIRTHDAY_MONTH_AMY)
                .withReward(VALID_REWARD_AMY).withTags(VALID_TAG_MEMBER).build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withBirthdayMonth(VALID_BIRTHDAY_MONTH_BOB)
                .withReward(VALID_REWARD_BOB).withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualBobaBotModel} matches {@code expectedBobaBotModel}
     */
    public static void assertCommandSuccess(Command command, BobaBotModel actualBobaBotModel,
                                            CommandResult expectedCommandResult,
                                            BobaBotModel expectedBobaBotModel) {
        try {
            CommandResult result = command.execute(actualBobaBotModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedBobaBotModel, actualBobaBotModel);
        } catch (CommandException | ParseException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, BobaBotModel, CommandResult, BobaBotModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, BobaBotModel actualBobaBotModel, String expectedMessage,
                                            BobaBotModel expectedBobaBotModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualBobaBotModel, expectedCommandResult, expectedBobaBotModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered customer list and selected customer in {@code actualBobaBotModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, BobaBotModel actualBobaBotModel, String expectedMessage) {
        // we are unable to defensively copy the bobaBotModel for comparison later, so we can
        // only do so by copying its components.
        BobaBot expectedBobaBot = new BobaBot(actualBobaBotModel.getBobaBot());
        List<Customer> expectedFilteredList = new ArrayList<>(actualBobaBotModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualBobaBotModel));
        assertEquals(expectedBobaBot, actualBobaBotModel.getBobaBot());
        assertEquals(expectedFilteredList, actualBobaBotModel.getFilteredPersonList());
    }
    /**
     * Updates {@code bobaBotModel}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code bobaBotModel}'s address book.
     */
    public static void showPersonAtIndex(BobaBotModel bobaBotModel, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < bobaBotModel.getFilteredPersonList().size());

        Customer customer = bobaBotModel.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        bobaBotModel.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, bobaBotModel.getFilteredPersonList().size());
    }

}
