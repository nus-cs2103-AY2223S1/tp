package paymelah.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_DATE;
import static paymelah.logic.parser.CliSyntax.PREFIX_DEBT;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;
import static paymelah.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static paymelah.logic.parser.CliSyntax.PREFIX_TIME;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.exceptions.CommandException;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.AddressBook;
import paymelah.model.Model;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.Person;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.testutil.PersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_TELEGRAM_AMY = "@amy_123";
    public static final String VALID_TELEGRAM_BOB = "@bob_123";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_DEBT_KOI_DESCRIPTION = "koi";
    public static final String VALID_DEBT_KOI_MONEY = "4.0";
    public static final String VALID_DEBT_KOI_DATE = "2022-10-12";
    public static final String VALID_DEBT_KOI_TIME = "12:34";
    public static final Debt VALID_DEBT_KOI =
            new Debt(new Description(VALID_DEBT_KOI_DESCRIPTION), new Money(VALID_DEBT_KOI_MONEY),
                    new DebtDate(VALID_DEBT_KOI_DATE), new DebtTime(VALID_DEBT_KOI_TIME));

    public static final String VALID_DEBT_PIZZA_DESCRIPTION = "pizza";
    public static final String VALID_DEBT_PIZZA_MONEY = "$5";
    public static final String VALID_DEBT_PIZZA_DATE = "2022-09-12";
    public static final String VALID_DEBT_PIZZA_TIME = "00:00";
    public static final Debt VALID_DEBT_PIZZA =
            new Debt(new Description(VALID_DEBT_PIZZA_DESCRIPTION), new Money(VALID_DEBT_PIZZA_MONEY),
                    new DebtDate(VALID_DEBT_PIZZA_DATE), new DebtTime(VALID_DEBT_PIZZA_TIME));

    public static final String VALID_DEBT_KARAOKE_DESCRIPTION = "karaoke";
    public static final String VALID_DEBT_KARAOKE_MONEY = "7";
    public static final String VALID_DEBT_KARAOKE_DATE = "2022-08-10";
    public static final String VALID_DEBT_KARAOKE_TIME = "10:24";
    public static final Debt VALID_DEBT_KARAOKE =
            new Debt(new Description(VALID_DEBT_KARAOKE_DESCRIPTION), new Money(VALID_DEBT_KARAOKE_MONEY),
                    new DebtDate(VALID_DEBT_KARAOKE_DATE), new DebtTime(VALID_DEBT_KARAOKE_TIME));

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "bob bob"; // space within username
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String VALID_DESCRIPTION_MCDONALDS = "McDonald's";
    public static final String VALID_DESCRIPTION_SUPPER = "supper jio";
    public static final String VALID_MONEY_MCDONALDS = "10.80";
    public static final String VALID_MONEY_SUPPER = "$4.5";
    public static final String VALID_DATE_MCDONALDS = "2022-10-11";
    public static final String VALID_DATE_SUPPER = "2022-9-12";
    public static final String VALID_TIME_MCDONALDS = "12:34";
    public static final String VALID_TIME_SUPPER = "00:00";

    public static final String DESCRIPTION_DESC_MCDONALDS = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MCDONALDS;
    public static final String DESCRIPTION_DESC_SUPPER = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SUPPER;

    public static final String MONEY_DESC_MCDONALDS = " " + PREFIX_MONEY + VALID_MONEY_MCDONALDS;
    public static final String MONEY_DESC_SUPPER = " " + PREFIX_MONEY + VALID_MONEY_SUPPER;
    public static final String MONEY_DESC_TEN_DOLLARS = " " + PREFIX_MONEY + "10";

    public static final String DATE_DESC_MCDONALDS = " " + PREFIX_DATE + VALID_DATE_MCDONALDS;
    public static final String DATE_DESC_SUPPER = " " + PREFIX_DATE + VALID_DATE_SUPPER;

    public static final String TIME_DESC_MCDONALDS = " " + PREFIX_TIME + VALID_TIME_MCDONALDS;
    public static final String TIME_DESC_SUPPER = " " + PREFIX_TIME + VALID_TIME_SUPPER;

    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " ";
    public static final String INVALID_MONEY_DESC = " " + PREFIX_MONEY + "one hundred";
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "invalid date";
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "invalid time";

    public static final String VALID_DEBT_INDEX = " " + PREFIX_DEBT + "1";
    public static final String VALID_DEBT_INDICES = " " + PREFIX_DEBT + "1 2 3";
    public static final String VALID_DEBT_INDICES_REPEAT = " " + PREFIX_DEBT + "1 3 3 1";
    public static final String INVALID_DEBT_INDEX = " " + PREFIX_DEBT + "a";
    public static final String INVALID_DEBT_INDEX_ZERO = " " + PREFIX_DEBT + "0";
    public static final String INVALID_DEBT_INDICES = " " + PREFIX_DEBT + "1 -1";

    public static final Index SINGLE_VALID_INDEX = INDEX_FIRST_PERSON;
    public static final Set<Index> SINGLE_VALID_INDEX_SET = new HashSet<>(List.of(SINGLE_VALID_INDEX));
    public static final String SINGLE_VALID_INDEX_STRING = String.valueOf(SINGLE_VALID_INDEX.getOneBased());

    public static final Index FIRST_VALID_MULTI_INDEX = INDEX_FIRST_PERSON;
    public static final Index SECOND_VALID_MULTI_INDEX = INDEX_SECOND_PERSON;
    public static final Index THIRD_VALID_MULTI_INDEX = INDEX_THIRD_PERSON;
    public static final Set<Index> THIRD_VALID_MULTI_INDEX_SET = new HashSet<>(List.of(THIRD_VALID_MULTI_INDEX));
    public static final Set<Index> MULTI_VALID_INDEX_SET =
            new HashSet<>(List.of(FIRST_VALID_MULTI_INDEX, SECOND_VALID_MULTI_INDEX, THIRD_VALID_MULTI_INDEX));
    public static final String MULTI_VALID_INDEX_STRING = FIRST_VALID_MULTI_INDEX.getOneBased()
            + " " + SECOND_VALID_MULTI_INDEX.getOneBased() + " " + THIRD_VALID_MULTI_INDEX.getOneBased();

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final PersonDescriptor DESC_AMY;
    public static final PersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new PersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withTelegram(VALID_TELEGRAM_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new PersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTelegram(VALID_TELEGRAM_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
        model.updateFilteredPersonList(new PersonMatchesDescriptorPredicate(
                new PersonDescriptorBuilder().withName(person.getName().fullName).build()));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the persons with debt in the
     * {@code model}'s address book.
     */
    public static void showDebtors(Model model) {
        Predicate<Person> predicateShowDebtors = p -> !p.getDebts().isEmpty();

        model.updateFilteredPersonList(predicateShowDebtors);
        assertTrue(!model.getFilteredPersonList().isEmpty());
    }

}
