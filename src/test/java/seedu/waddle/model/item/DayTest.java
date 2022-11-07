package seedu.waddle.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.commons.core.Messages.MESSAGE_CONFLICTING_ITEMS;
import static seedu.waddle.commons.core.Messages.MESSAGE_ITEM_PAST_MIDNIGHT;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.testutil.ItemBuilder;

public class DayTest {
    private Day dayStub;
    private Item noonOneHour;
    private String expectedConflictMessage;

    @BeforeEach
    public void setUp() {
        dayStub = new Day(0);
        noonOneHour = new ItemBuilder().build();
        noonOneHour.setStartTime(LocalTime.NOON);
        StringBuilder conflicts = new StringBuilder();
        conflicts.append("    ").append(noonOneHour.getDescription()).append(": ")
                .append(noonOneHour.getStartTime()).append(" - ").append(noonOneHour.getEndTime()).append("\n");
        expectedConflictMessage = String.format(MESSAGE_CONFLICTING_ITEMS, conflicts);

        try {
            dayStub.addItem(noonOneHour);
        } catch (CommandException e) {
            assert false : "Failed to create Day stub";
        }
    }

    @Test
    public void addItem_startTimeConflict_emptyList() {
        Item startTimeConflictItem = new ItemBuilder().withDesc("start time conflict").build();
        startTimeConflictItem.setStartTime(LocalTime.parse("11:30"));

        try {
            dayStub.addItem(startTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedConflictMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }

    @Test
    public void addItem_endTimeConflict_emptyList() {
        Item endTimeConflictItem = new ItemBuilder().withDesc("end time conflict").build();
        endTimeConflictItem.setStartTime(LocalTime.parse("12:30"));

        try {
            dayStub.addItem(endTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedConflictMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }

    @Test
    public void addItem_sameTimeConflict_emptyList() {
        Item sameTimeConflictItem = new ItemBuilder().withDesc("same time conflict").build();
        sameTimeConflictItem.setStartTime(LocalTime.parse("12:00"));

        try {
            dayStub.addItem(sameTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedConflictMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }

    @Test
    public void addItem_overlapTimeConflict_emptyList() {
        Item overlapTimeConflictItem = new ItemBuilder().withDesc("overlap time conflict")
                .withDuration("120").build();
        overlapTimeConflictItem.setStartTime(LocalTime.parse("11:30"));

        try {
            dayStub.addItem(overlapTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedConflictMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }

    @Test
    public void addItem_pastMidnightTimeConflict_emptyList() {
        Item pastMidnightTimeConflictItem = new ItemBuilder().withDesc("past midnight time conflict").build();
        pastMidnightTimeConflictItem.setStartTime(LocalTime.parse("23:30"));
        String expectedMidnightMessage = String.format(MESSAGE_ITEM_PAST_MIDNIGHT,
                pastMidnightTimeConflictItem.getDescription());

        try {
            dayStub.addItem(pastMidnightTimeConflictItem);
        } catch (CommandException actualCommandException) {
            assertEquals(actualCommandException.getLocalizedMessage(), expectedMidnightMessage);
            return;
        }
        assert false : "A time conflict CommandException should have been thrown.";
    }

    @Test
    public void getVacantSlots_correctOutput() {
        modifyDayStub();
        String expectedString = "Day 1:" + System.lineSeparator()
                + "    02:00 - 11:00" + System.lineSeparator()
                + "    13:00 - 23:00" + System.lineSeparator();
        String actualString = dayStub.getVacantSlots();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void getTextRepresentation_correctOutput() {
        modifyDayStub();
        String expectedString = "Day 1" + System.lineSeparator()
                + "    1. start at midnight" + System.lineSeparator()
                + "        ★★★★★" + System.lineSeparator()
                + "        Cost $100.00" + System.lineSeparator()
                + "        Duration 60 mins" + System.lineSeparator()
                + "        Time: 00:00 - 01:00" + System.lineSeparator()
                + "    " + System.lineSeparator()
                + "    2. start joined with previous item" + System.lineSeparator()
                + "        ★★★★★" + System.lineSeparator()
                + "        Cost $100.00" + System.lineSeparator()
                + "        Duration 60 mins" + System.lineSeparator()
                + "        Time: 01:00 - 02:00" + System.lineSeparator()
                + "    " + System.lineSeparator()
                + "    3. end joined with next item" + System.lineSeparator()
                + "        ★★★★★" + System.lineSeparator()
                + "        Cost $100.00" + System.lineSeparator()
                + "        Duration 60 mins" + System.lineSeparator()
                + "        Time: 11:00 - 12:00" + System.lineSeparator()
                + "    " + System.lineSeparator()
                + "    4. Airport" + System.lineSeparator()
                + "        ★★★★★" + System.lineSeparator()
                + "        Cost $100.00" + System.lineSeparator()
                + "        Duration 60 mins" + System.lineSeparator()
                + "        Time: 12:00 - 13:00" + System.lineSeparator()
                + "    " + System.lineSeparator()
                + "    5. end at midnight" + System.lineSeparator()
                + "        ★★★★★" + System.lineSeparator()
                + "        Cost $100.00" + System.lineSeparator()
                + "        Duration 60 mins" + System.lineSeparator()
                + "        Time: 23:00 - 00:00 (next day)" + System.lineSeparator()
                + "    " + System.lineSeparator();
        String actualString = dayStub.getTextRepresentation();
        assertEquals(expectedString, actualString);
    }

    private void modifyDayStub() {
        Item startAtMidnight = new ItemBuilder().withDesc("start at midnight").build();
        startAtMidnight.setStartTime(LocalTime.MIDNIGHT);
        Item startJoined = new ItemBuilder().withDesc("start joined with previous item").build();
        startJoined.setStartTime(LocalTime.parse("01:00"));
        Item endJoined = new ItemBuilder().withDesc("end joined with next item").build();
        endJoined.setStartTime(LocalTime.parse("11:00"));
        Item endAtMidnight = new ItemBuilder().withDesc("end at midnight").build();
        endAtMidnight.setStartTime(LocalTime.parse("23:00"));

        try {
            dayStub.addItem(startAtMidnight);
            dayStub.addItem(startJoined);
            dayStub.addItem(endJoined);
            dayStub.addItem(endAtMidnight);
        } catch (CommandException e) {
            assert false : "Failed to modify Day stub";
        }
    }
}
