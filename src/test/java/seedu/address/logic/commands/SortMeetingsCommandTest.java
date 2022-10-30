package seedu.address.logic.commands;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.util.DateTimeConverter;

public class SortMeetingsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingList(), new UserPrefs());

    @Test
    public void equals() {
        SortMeetingsCommand sortDescending = new SortMeetingsCommand(false);
        SortMeetingsCommand sortAscending = new SortMeetingsCommand(true);
        assertEquals(sortAscending, sortAscending);
        assertNotEquals(sortAscending, sortDescending);

        SortMeetingsCommand sortAscendingCopy = new SortMeetingsCommand(true);
        assertEquals(sortAscending, sortAscendingCopy);
    }
    @Test
    public void execute_sorted() throws CommandException {
        //test descending sorted
        SortMeetingsCommand sortDescending = new SortMeetingsCommand(false);
        sortDescending.execute(model);
        assertTrue(isListSorted(model.getFilteredMeetingList(), false));

        //test ascending sorted
        SortMeetingsCommand sortAscending = new SortMeetingsCommand(true);
        sortAscending.execute(model);
        assertTrue(isListSorted(model.getFilteredMeetingList(), true));
    }

    private boolean isListSorted(ObservableList<Meeting> meetingList, boolean isInAscending) {
        for (int i = 1; i < meetingList.size(); i++) {
            LocalDateTime currDate = DateTimeConverter
                    .processedStringToLocalDatetime(meetingList.get(i - 1).getDateAndTime());
            LocalDateTime nextDate = DateTimeConverter
                    .processedStringToLocalDatetime(meetingList.get(i).getDateAndTime());
            if ((isInAscending && currDate.isAfter(nextDate) || (!isInAscending && currDate.isBefore(nextDate)))) {
                return false;
            }
        }
        return true;
    }
}
