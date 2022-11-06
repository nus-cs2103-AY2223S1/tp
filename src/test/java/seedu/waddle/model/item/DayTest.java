package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.commons.core.Messages.MESSAGE_CONFLICTING_ITEMS;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.logic.commands.CommandTestUtil.showItineraryAtIndex;
import static seedu.waddle.testutil.TypicalIndexes.INDEX_FIRST_ITINERARY;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.ListCommand;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.ModelManager;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.testutil.ItemBuilder;
import seedu.waddle.testutil.TypicalItems;

public class DayTest {
    private Day dayStub;
    private Item noonOneHour;
    private String expectedMessage;

    @BeforeEach
    public void setUp() {
        dayStub = new Day(1);
        noonOneHour = new ItemBuilder().build();
        noonOneHour.setStartTime(LocalTime.NOON);
        StringBuilder conflicts = new StringBuilder();
        conflicts.append("    ").append(noonOneHour.getDescription()).append(": ")
                .append(noonOneHour.getStartTime()).append(" - ").append(noonOneHour.getEndTime()).append("\n");
        expectedMessage = String.format(MESSAGE_CONFLICTING_ITEMS, conflicts);

        try {
            dayStub.addItem(noonOneHour);
        } catch (CommandException e) {
            assert false : "Failed to create Day stub";
        }
    }

    @Test
    public void addItem_startTimeConflict_emptyList() {
        Item startTimeConflictItem = new ItemBuilder().withDesc("start time conflict").withDuration("60").build();
        startTimeConflictItem.setStartTime(LocalTime.parse("11:30"));

        try {
            dayStub.addItem(startTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }
}
