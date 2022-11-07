package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_1;
import static seedu.address.testutil.TypicalMeetings.MEETING_TYPICAL_3;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.MeetingFilterDatePredicate;


public class FilterMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void equals() {
        LocalDateTime date1 = LocalDateTime.of(2020, 1, 8, 10, 12, 10);
        LocalDateTime date2 = LocalDateTime.of(2021, 2, 11, 21, 11, 3);
        LocalDateTime date3 = LocalDateTime.of(2022, 4, 1, 22, 5, 7);
        LocalDateTime date4 = LocalDateTime.of(2023, 8, 24, 11, 15, 1);

        MeetingFilterDatePredicate filterDatePredicate = new MeetingFilterDatePredicate(date1, date2);
        MeetingFilterDatePredicate filterDatePredicate2 = new MeetingFilterDatePredicate(date3, date4);

        FilterMeetingCommand filterMeetingCommand = new FilterMeetingCommand(filterDatePredicate);

        // same object -> returns true
        assertEquals(filterMeetingCommand, filterMeetingCommand);

        // same text and same functional interface -> returns true
        FilterMeetingCommand filterMeetingCommandCopy = new FilterMeetingCommand(filterDatePredicate);
        assertEquals(filterMeetingCommand, filterMeetingCommandCopy);

        // different types -> returns false
        assertNotEquals(0, filterMeetingCommand);

        // null -> returns false
        assertNotEquals(null, filterMeetingCommand);

        // different dates -> returns false
        assertNotEquals(filterMeetingCommand, new FilterMeetingCommand(filterDatePredicate2));
    }

    @Test
    public void execute_outOfScopeFilter_noMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        // dates are in 9999 where it's for sure out of scope
        LocalDateTime date1 = LocalDateTime.of(9999, 4, 1, 22, 5, 7);
        LocalDateTime date2 = LocalDateTime.of(9999, 8, 24, 11, 15, 1);
        MeetingFilterDatePredicate filterDatePredicate = new MeetingFilterDatePredicate(date1, date2);
        expectedModel.updateFilteredMeetingList(filterDatePredicate);
        FilterMeetingCommand filterMeetingCommand = new FilterMeetingCommand(filterDatePredicate);

        assertCommandSuccess(filterMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }

    @Test
    public void execute_filterScopeInRange_multipleMeetingsFound() {
        //test with two meetings TYPICAL 1 and TYPICAL 3
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        LocalDateTime date1 = LocalDateTime.of(2022, 1, 1, 1, 1, 1);
        LocalDateTime date2 = LocalDateTime.of(2022, 3, 1, 1, 1, 1);
        MeetingFilterDatePredicate filterDatePredicate = new MeetingFilterDatePredicate(date1, date2);
        FilterMeetingCommand filterMeetingCommand = new FilterMeetingCommand(filterDatePredicate);

        expectedModel.updateFilteredMeetingList(filterDatePredicate);
        assertCommandSuccess(filterMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING_TYPICAL_1, MEETING_TYPICAL_3),
                model.getFilteredMeetingList());
    }
    @Test
    public void execute_filterSingleDate_singleMeetingsFound() {
        //test finding one meeting when date1 = date2 scenario
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 1);
        LocalDateTime date1 = LocalDateTime.of(2022, 1, 12, 23, 59);
        MeetingFilterDatePredicate filterDatePredicate = new MeetingFilterDatePredicate(date1, date1);
        FilterMeetingCommand filterMeetingCommand = new FilterMeetingCommand(filterDatePredicate);

        expectedModel.updateFilteredMeetingList(filterDatePredicate);
        assertCommandSuccess(filterMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(List.of(MEETING_TYPICAL_1), model.getFilteredMeetingList());
    }
}
