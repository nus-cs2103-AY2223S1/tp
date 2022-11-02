package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.iteration.EditIterationCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditCommissionDescriptorBuilder;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.EditIterationDescriptorBuilder;

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
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_ANIMAL = "animal";
    public static final String VALID_TAG_FOOD = "food";

    public static final String VALID_TITLE_CAT = "Cat";
    public static final String VALID_TITLE_DOG = "Dog";
    public static final String VALID_TITLE_ELEPHANT = "Elephant";
    public static final String VALID_DESCRIPTION_CAT = "A white cat";
    public static final String VALID_DESCRIPTION_DOG = "A black dog";
    public static final String VALID_DESCRIPTION_ELEPHANT = "A red elephant";

    public static final String VALID_FEE_LITTLE = "0.341245";
    public static final String VALID_FEE_NORMAL = "50.03";
    public static final String VALID_FEE_LARGE = "2550.50";
    public static final String VALID_INT_FEE = "500";

    public static final String VALID_NO_STATUS = "No";
    public static final String VALID_N_STATUS = "n";
    public static final String VALID_FALSE_STATUS = "False";
    public static final String VALID_F_STATUS = "f";
    public static final String VALID_Y_STATUS = "y";
    public static final String VALID_YES_STATUS = "YES";
    public static final String VALID_TRUE_STATUS = "true";
    public static final String VALID_T_STATUS = "T";

    public static final String VALID_DATE_FIRST_DAY_OF_YEAR = "2022-01-01";
    public static final String VALID_DATE_LAST_DAY_OF_YEAR = "2022-12-31";
    public static final String INVALID_DATE_YYYY_M_D = "2022-3-1";
    public static final String INVALID_DATE_YY_MM_DD = "22-2-2";
    public static final String INVALID_DATE_NO_DELIMINATOR = "2022 10 10";

    public static final String VALID_ITERATION_DESCRIPTION_FINALISE = "Finalised the commission";
    public static final String VALID_ITERATION_DESCRIPTION_COLOR = "Added colours to the artwork";
    public static final String VALID_ITERATION_DESCRIPTION_REMOVE = "Removed the character from the scene";
    public static final String VALID_FEEDBACK_HORRIBLE = "This looks horrible!";
    public static final String VALID_FEEDBACK_UGLY = "The colours are very ugly- please change it";
    public static final String VALID_FEEDBACK_AMAZING = "Wow, looks amazing";
    public static final String VALID_ITERATION_IMAGEPATH_FINALISE = System.getProperty("user.dir")
            + "/src/test/data/images/test_image_1.png";
    public static final String VALID_ITERATION_IMAGEPATH_COLOR = System.getProperty("user.dir")
            + "/src/test/data/images/test_image_2.png";

    public static final String TITLE_DESC_CAT = " " + PREFIX_TITLE + VALID_TITLE_CAT;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String TAG_DESC_ANIMAL = " " + PREFIX_TAG + VALID_TAG_ANIMAL;

    public static final String TAG_DESC_FOOD = " " + PREFIX_TAG + VALID_TAG_FOOD;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + " ";

    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "next monday";

    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "troo :)";

    public static final String INVALID_FEE_DESC = " " + PREFIX_FEE + "-1.2345678";

    public static final String ITERATION_DESCRIPTION_DESC_FINALISE = " " + PREFIX_ITERATION_DESCRIPTION
            + VALID_ITERATION_DESCRIPTION_FINALISE;
    public static final String ITERATION_DESCRIPTION_DESC_COLOR = " " + PREFIX_ITERATION_DESCRIPTION
            + VALID_ITERATION_DESCRIPTION_COLOR;
    public static final String ITERATION_DATE_DESC_FINALISE = " " + PREFIX_ITERATION_DATE
            + VALID_DATE_FIRST_DAY_OF_YEAR;
    public static final String ITERATION_DATE_DESC_COLOR = " " + PREFIX_ITERATION_DATE
            + VALID_DATE_LAST_DAY_OF_YEAR;
    public static final String ITERATION_FEEDBACK_DESC_FINALISE = " " + PREFIX_ITERATION_FEEDBACK
            + VALID_FEEDBACK_HORRIBLE;
    public static final String ITERATION_FEEDBACK_DESC_COLOR = " " + PREFIX_ITERATION_FEEDBACK
            + VALID_FEEDBACK_UGLY;
    public static final String ITERATION_IMAGEPATH_DESC_FINALISE = " " + PREFIX_ITERATION_IMAGEPATH
            + VALID_ITERATION_IMAGEPATH_FINALISE;
    public static final String ITERATION_IMAGEPATH_DESC_COLOR = " " + PREFIX_ITERATION_IMAGEPATH
            + VALID_ITERATION_DESCRIPTION_COLOR;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerCommand.EditCustomerDescriptor DESC_AMY;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_BOB;

    public static final EditCommissionCommand.EditCommissionDescriptor DESC_CAT;
    public static final EditCommissionCommand.EditCommissionDescriptor DESC_DOG;

    public static final EditIterationCommand.EditIterationDescriptor DESC_FINALISE;
    public static final EditIterationCommand.EditIterationDescriptor DESC_COLOR;

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_CAT = new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_CAT)
                .withDescription(VALID_DESCRIPTION_CAT).withFee(50).build();
        DESC_DOG = new EditCommissionDescriptorBuilder().withTitle(VALID_TITLE_DOG)
                .withDescription(VALID_DESCRIPTION_DOG).withFee(60).build();
        DESC_FINALISE = new EditIterationDescriptorBuilder()
                .withDescription(VALID_ITERATION_DESCRIPTION_FINALISE).withFeedback(VALID_FEEDBACK_HORRIBLE).build();
        DESC_COLOR = new EditIterationDescriptorBuilder().withDescription(VALID_ITERATION_DESCRIPTION_COLOR)
                .withFeedback(VALID_FEEDBACK_UGLY).withDate(LocalDate.parse(VALID_DATE_LAST_DAY_OF_YEAR)).build();
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
     * - the address book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getSortedFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getSortedFilteredCustomerList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedFilteredCustomerList().size());

        Customer customer = model.getSortedFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getSortedFilteredCustomerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the commission at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCommissionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedFilteredCustomerList().size());

        Commission targetCommission = model.getFilteredCommissionList().get(targetIndex.getZeroBased());
        model.updateFilteredCommissionList(commission -> commission.isSameCommission(targetCommission));

        assertEquals(1, model.getFilteredCommissionList().size());

    }

}
