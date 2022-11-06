package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.commons.core.Messages.MESSAGE_CONFLICTING_ITEMS;
import static seedu.waddle.commons.core.Messages.MESSAGE_ITEM_PAST_MIDNIGHT;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.value.WritableDoubleValue;
import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.ItemBuilder;
import seedu.waddle.testutil.ItineraryBuilder;
import seedu.waddle.testutil.TypicalItineraries;

public class PlanCommandTest {
    private Model model;
    private Itinerary validItinerary;
    private String timeConflictMessage;

    @BeforeEach
    private void setUp() {
        this.model = getModelStub();

        // select validItinerary
        StageManager.getInstance().setWishStage(validItinerary);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PlanCommand(null, null, null));
    }

    @Test
    public void execute_startAtMidnight_planSuccessful() {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(1), new DayNumber("1"), LocalTime.parse("00:00"));
        Model expectedModel = getModelStub();
        try {
            expectedModel.getFilteredItineraryList().get(0)
                    .planItem(Index.fromZeroBased(1), new DayNumber("1"), LocalTime.parse("00:00"));
        } catch (CommandException e) {
            assert false : "Planning failed";
        }

        assertCommandSuccess(planCommand, model,
                String.format(PlanCommand.MESSAGE_SUCCESS, "item 3"), expectedModel);
    }

    @Test
    public void execute_endAtMidnight_planSuccessful() {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(1), new DayNumber("1"), LocalTime.parse("23:00"));
        Model expectedModel = getModelStub();
        try {
            expectedModel.getFilteredItineraryList().get(0)
                    .planItem(Index.fromZeroBased(1), new DayNumber("1"), LocalTime.parse("23:00"));
        } catch (CommandException e) {
            assert false : "Planning failed";
        }

        assertCommandSuccess(planCommand, model,
                String.format(PlanCommand.MESSAGE_SUCCESS, "item 3"), expectedModel);
    }

    @Test
    public void execute_startTimeConflict_throwsCommandException() throws Exception {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(1), new DayNumber("1"), LocalTime.parse("11:30"));

        assertCommandFailure(planCommand, model, timeConflictMessage);
    }

    @Test
    public void execute_endTimeConflict_throwsCommandException() throws Exception {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(0), new DayNumber("1"), LocalTime.parse("12:30"));

        assertCommandFailure(planCommand, model, timeConflictMessage);
    }

    @Test
    public void execute_sameTimeConflict_throwsCommandException() throws Exception {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(0), new DayNumber("1"), LocalTime.parse("12:00"));

        assertCommandFailure(planCommand, model, timeConflictMessage);
    }

    @Test
    public void execute_overlapTimeConflict_throwsCommandException() throws Exception {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(0), new DayNumber("1"), LocalTime.parse("11:30"));

        assertCommandFailure(planCommand, model, timeConflictMessage);
    }

    @Test
    public void execute_pastMidnightTimeConflict_throwsCommandException() throws Exception {
        PlanCommand planCommand =
                new PlanCommand(Index.fromZeroBased(0), new DayNumber("1"), LocalTime.parse("23:30"));
        String expectedMessage = String.format(MESSAGE_ITEM_PAST_MIDNIGHT,
                "item 2");

        assertCommandFailure(planCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Itinerary summer = new ItineraryBuilder().withName("Summer").build();
        Itinerary winter = new ItineraryBuilder().withName("Winter").build();
        AddCommand addSummerCommand = new AddCommand(summer);
        AddCommand addWinterCommand = new AddCommand(winter);

        // same object -> returns true
        assertTrue(addSummerCommand.equals(addSummerCommand));

        // same values -> returns true
        AddCommand addSummerCommandCopy = new AddCommand(summer);
        assertTrue(addSummerCommand.equals(addSummerCommandCopy));

        // different types -> returns false
        assertFalse(addSummerCommand.equals(1));

        // null -> returns false
        assertFalse(addSummerCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(addSummerCommand.equals(addWinterCommand));
    }

    private Model getModelStub() {
        // model set up
        Model model = new ModelManager(new Waddle(), new UserPrefs());

        // itinerary set up
        validItinerary = TypicalItineraries.getAutumn();
        Item validItem1 = new ItemBuilder().withDesc("item 1").build();
        Item validItem2 = new ItemBuilder().withDesc("item 2").withDuration("120").build();
        Item validItem3 = new ItemBuilder().withDesc("item 3").build();
        Item validItem4 = new ItemBuilder().withDesc("item 4").build();
        validItinerary.addItem(validItem1);
        validItinerary.addItem(validItem2);
        validItinerary.addItem(validItem3);
        validItinerary.addItem(validItem4);

        // plan validItem1 to day 1 noon
        try {
            validItinerary.planItem(Index.fromZeroBased(0), new DayNumber("1"), LocalTime.NOON);
        } catch (CommandException e) {
            assert false : "Failed to set up valid itinerary";
        }

        // time conflict message set up
        StringBuilder conflicts = new StringBuilder();
        conflicts.append("    ").append(validItem1.getDescription()).append(": ")
                .append(validItem1.getStartTime()).append(" - ").append(validItem1.getEndTime()).append("\n");
        timeConflictMessage = String.format(MESSAGE_CONFLICTING_ITEMS, conflicts);

        model.addItinerary(validItinerary);
        return model;
    }
}
