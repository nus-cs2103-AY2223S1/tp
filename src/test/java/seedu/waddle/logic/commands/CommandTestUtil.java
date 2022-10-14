package seedu.waddle.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
<<<<<<< HEAD
import static seedu.waddle.logic.parser.CliSyntax.*;
=======
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PEOPLE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_DATE;
>>>>>>> 5b35a0ee3d05f71ceea91bfe854e284e7aa72887
import static seedu.waddle.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;
<<<<<<< HEAD
import seedu.waddle.model.person.NameContainsKeywordsPredicate;
import seedu.waddle.model.person.Person;
=======
>>>>>>> 5b35a0ee3d05f71ceea91bfe854e284e7aa72887
import seedu.waddle.testutil.EditItineraryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_SUMMER = "Summer Trip";
    public static final String VALID_NAME_WINTER = "Winter Trip";
    public static final String VALID_COUNTRY_SUMMER = "Sweden";
    public static final String VALID_COUNTRY_WINTER = "Japan";
    public static final String VALID_START_DATE_SUMMER = "2022-08-01";
    public static final String VALID_START_DATE_WINTER = "2023-01-01";
    public static final String VALID_END_DATE_SUMMER = "2022-08-26";
    public static final String VALID_END_DATE_WINTER = "2023-01-31";
    public static final String VALID_PEOPLE_SUMMER = "5";
    public static final String VALID_PEOPLE_WINTER = "10";

    public static final String NAME_DESC_SUMMER = " " + PREFIX_NAME + VALID_NAME_SUMMER;
    public static final String NAME_DESC_WINTER = " " + PREFIX_NAME + VALID_NAME_WINTER;
    public static final String COUNTRY_DESC_SUMMER = " " + PREFIX_COUNTRY + VALID_COUNTRY_SUMMER;
    public static final String COUNTRY_DESC_WINTER = " " + PREFIX_COUNTRY + VALID_COUNTRY_WINTER;
    public static final String START_DATE_DESC_SUMMER = " " + PREFIX_START_DATE + VALID_START_DATE_SUMMER;
    public static final String START_DATE_DESC_WINTER = " " + PREFIX_START_DATE + VALID_START_DATE_WINTER;
    public static final String END_DATE_DESC_SUMMER = " " + PREFIX_END_DATE + VALID_END_DATE_SUMMER;
    public static final String END_DATE_DESC_WINTER = " " + PREFIX_END_DATE + VALID_END_DATE_WINTER;
    public static final String PEOPLE_DESC_SUMMER = " " + PREFIX_PEOPLE + VALID_PEOPLE_SUMMER;
    public static final String PEOPLE_DESC_WINTER = " " + PREFIX_PEOPLE + VALID_PEOPLE_WINTER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    // public static final String INVALID_COUNTRY_DESC = " " + PREFIX_COUNTRY + "911a"; // 'a' not allowed in phones
    public static final String INVALID_START_DATE= " " + PREFIX_START_DATE + "Jan 01"; // wrong format
    public static final String INVALID_END_DATE = " " + PREFIX_END_DATE; // empty string not allowed for dates
    public static final String INVALID_PEOPLE = " " + PREFIX_PEOPLE + "five"; // only numbers allowed for people

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditItineraryDescriptor DESC_SUMMER;
    public static final EditCommand.EditItineraryDescriptor DESC_WINTER;

    static {
        DESC_SUMMER = new EditItineraryDescriptorBuilder().withName(VALID_NAME_SUMMER)
                .withCountry(VALID_COUNTRY_SUMMER).withStartDate(VALID_START_DATE_SUMMER)
                .withEndDate(VALID_END_DATE_SUMMER)
                .withPeople(VALID_PEOPLE_SUMMER).build();
        DESC_WINTER = new EditItineraryDescriptorBuilder().withName(VALID_NAME_WINTER)
                .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
                .withEndDate(VALID_END_DATE_WINTER)
                .withPeople(VALID_PEOPLE_WINTER).build();
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
        Waddle expectedAddressBook = new Waddle(actualModel.getWaddle());
        List<Itinerary> expectedFilteredList = new ArrayList<>(actualModel.getFilteredItineraryList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getWaddle());
        assertEquals(expectedFilteredList, actualModel.getFilteredItineraryList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showItineraryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItineraryList().size());

        Itinerary itinerary = model.getFilteredItineraryList().get(targetIndex.getZeroBased());
        final String[] splitName = itinerary.getName().fullName.split("\\s+");
        model.updateFilteredItineraryList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredItineraryList().size());
    }

}
