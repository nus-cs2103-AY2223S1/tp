package seedu.travelr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.Travelr;
import seedu.travelr.model.trip.TitleContainsKeywordsPredicate;
import seedu.travelr.model.trip.Trip;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_GERMANY = "Germany trip";
    public static final String VALID_TITLE_ANTARCTICA = "Antarctic trip";
    public static final String VALID_TITLE_JAPAN = "Japan Trip";

    public static final String VALID_DESCRIPTION_GERMANY = "Block 312, Amy Street 1";
    public static final String VALID_DESCRIPTION_ANTARCTICA = "Block 123, Bobby Street 3";
    public static final String VALID_DESCRIPTION_JAPAN = "Going around Japan";

    public static final String VALID_EVENT_EATING = "Eating";
    public static final String VALID_EVENT_SIGHTSEEING = "Sightseeing";

    public static final String VALID_LOCATION_GERMANY = "Germany";
    public static final String VALID_LOCATION_JAPAN = "Japan";

    public static final String VALID_DATE = "01-01-2000";


    public static final String TITLE_DESC_GERMANY = " " + PREFIX_TITLE + VALID_TITLE_GERMANY;
    public static final String TITLE_DESC_ANTARCTICA = " " + PREFIX_TITLE + VALID_TITLE_ANTARCTICA;
    public static final String DESCRIPTION_DESC_GERMANY = " " + PREFIX_DESC + VALID_DESCRIPTION_GERMANY;
    public static final String DESCRIPTION_DESC_ANTARCTICA = " " + PREFIX_DESC + VALID_DESCRIPTION_ANTARCTICA;
    public static final String EVENT_DESC_EATING = " " + PREFIX_EVENT + VALID_EVENT_EATING;
    public static final String EVENT_DESC_SIGHTSEEING = " " + PREFIX_EVENT + VALID_EVENT_SIGHTSEEING;
    public static final String LOCATION_DESC = " " + PREFIX_LOCATION + VALID_LOCATION_GERMANY;
    public static final String DATE_DESC = " " + PREFIX_DATE + VALID_DATE;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "James&"; // '&' not allowed in titles
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESC; // empty string not allowed for description
    public static final String INVALID_EVENT_DESC = " " + PREFIX_EVENT + "hubby*"; // '*' not allowed in events

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

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
     * - the address book, filtered trip list and selected trip in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Travelr expectedAddressBook = new Travelr(actualModel.getTravelr());
        List<Trip> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTripList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getTravelr());
        assertEquals(expectedFilteredList, actualModel.getFilteredTripList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the trip at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTripAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTripList().size());

        Trip trip = model.getFilteredTripList().get(targetIndex.getZeroBased());
        final String[] splitTitle = trip.getTitle().fullTitle.split("\\s+");
        model.updateFilteredTripList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredTripList().size());
    }

}
