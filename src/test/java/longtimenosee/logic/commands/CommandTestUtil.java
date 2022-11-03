package longtimenosee.logic.commands;

import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;
import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import longtimenosee.commons.core.index.Index;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.AddressBook;
import longtimenosee.model.Model;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.predicate.NameInEventContainsKeywordsPredicate;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.predicate.NameContainsKeywordsPredicate;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.predicate.TitleContainsKeywordsPredicate;
import longtimenosee.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_BIRTHDAY_AMY = "2020-01-01";
    public static final String VALID_BIRTHDAY_BOB = "2010-01-01";
    public static final String VALID_INCOME_AMY = "100.0";
    public static final String VALID_INCOME_BOB = "200.0";
    public static final String VALID_RISK_APPETITE_AMY = "M";
    public static final String VALID_RISK_APPETITE_BOB = "M";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_TITLE_MANUEXTRA = "ManuExtra II";
    public static final String VALID_TITLE_PRULIFE = "PruLife";
    public static final String VALID_COMPANY_MANUEXTRA = "MNF";
    public static final String VALID_COMPANY_PRULIFE = "PRU";
    public static final String VALID_COMMISSION_MANUEXTRA = "15% 10% 1%";
    public static final String VALID_COMMISSION_PRULIFE = "10% 3% 2%";
    public static final String VALID_COVERAGE_MANUEXTRA = "MOTOR";
    public static final String VALID_COVERAGE_PRULIFE = "LIFE";

    public static final String VALID_PREMIUM_PRUSHIELD = "200";
    public static final String VALID_PREMIUM_FLEXI = "300";
    public static final String VALID_START_DATE_PRUSHIELD = "2010-10-10";
    public static final String VALID_END_DATE_PRUSHIELD = "2021-12-12";
    public static final String VALID_START_DATE_FLEXI = "2011-01-01";
    public static final String VALID_END_DATE_FLEXI = "2019-01-01";


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
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String INCOME_DESC_AMY = " " + PREFIX_INCOME + VALID_INCOME_AMY;
    public static final String INCOME_DESC_BOB = " " + PREFIX_INCOME + VALID_INCOME_BOB;
    public static final String RISK_APPETITE_DESC_AMY = " " + PREFIX_RISK_APPETITE + VALID_RISK_APPETITE_AMY;
    public static final String RISK_APPETITE_DESC_BOB = " " + PREFIX_RISK_APPETITE + VALID_RISK_APPETITE_BOB;

    public static final String TITLE_DESC_MANUEXTRA = " " + PREFIX_TITLE + VALID_TITLE_MANUEXTRA;
    public static final String TITLE_DESC_PRULIFE = " " + PREFIX_TITLE + VALID_TITLE_PRULIFE;
    public static final String COMPANY_DESC_MANUEXTRA = " " + PREFIX_COMPANY + VALID_COMPANY_MANUEXTRA;
    public static final String COMPANY_DESC_PRULIFE = " " + PREFIX_COMPANY + VALID_COMPANY_PRULIFE;
    public static final String COMMISSION_DESC_MANUEXTRA = " " + PREFIX_COMMISSION + VALID_COMMISSION_MANUEXTRA;
    public static final String COMMISSION_DESC_PRULIFE = " " + PREFIX_COMMISSION + VALID_COMMISSION_PRULIFE;
    public static final String COVERAGE_DESC_MANUEXTRA = " " + PREFIX_COVERAGES + VALID_COVERAGE_MANUEXTRA;
    public static final String COVERAGE_DESC_PRULIFE = " " + PREFIX_COVERAGES + VALID_COVERAGE_PRULIFE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Policy&"; // '&' not allowed in Title
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "sds"; // 'sds' not a valid company
    public static final String INVALID_COMMISSION_DESC = " " + PREFIX_COMMISSION + "101% 101% 101%"; // '101%' too large
    public static final String INVALID_COVERAGE_DESC = " " + PREFIX_COVERAGES + "dsdas"; // 'dsdas' not a valid coverage

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withBirthday(VALID_BIRTHDAY_AMY).withIncome(VALID_INCOME_AMY)
                .withRiskAppetite(VALID_RISK_APPETITE_AMY).build();

        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withBirthday(VALID_BIRTHDAY_BOB)
                .withIncome(VALID_INCOME_BOB)
                .withRiskAppetite(VALID_RISK_APPETITE_BOB).build();
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
            assertEquals(expectedModel, actualModel);
            //added additional check for result and expectedCommandResult
            assertEquals(result, expectedCommandResult);
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
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false);
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
     * Updates {@code model}'s filtered list to show only the policy at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPolicyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPolicyList().size());

        Policy policy = model.getFilteredPolicyList().get(targetIndex.getZeroBased());
        final String[] splitName = policy.getTitle().fullTitle.split("\\s+");
        model.updateFilteredPolicyList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPolicyList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getPersonName().fullName.split("\\s+");
        model.updateFilteredEventList(new NameInEventContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
