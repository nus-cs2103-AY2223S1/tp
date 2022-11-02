package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.event.ViewUpcomingEventsCommand.MESSAGE_SUCCESS_MULTIPLE_EVENTS;
import static seedu.address.logic.commands.event.ViewUpcomingEventsCommand.MESSAGE_SUCCESS_NO_EVENTS;
import static seedu.address.logic.commands.event.ViewUpcomingEventsCommand.MESSAGE_SUCCESS_SINGLE_EVENT;
import static seedu.address.logic.commands.event.ViewUpcomingEventsCommand.MESSAGE_SUCCESS_TOMORROW;
import static seedu.address.logic.commands.event.ViewUpcomingEventsCommand.MESSAGE_SUCCESS_UPCOMING_DAYS;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.event.StartDateWithinTimeFramePredicate;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TypicalNuScheduler;

public class ViewUpcomingEventsCommandTest {
    private static final int ONE_DAY = 1;
    private static final int FIVE_DAYS = 5;

    private LocalDate currentDate = java.time.LocalDate.now();
    private LocalDate firstEndDate = currentDate.plusDays(ONE_DAY);
    private StartDateWithinTimeFramePredicate firstPredicate =
            new StartDateWithinTimeFramePredicate(currentDate, firstEndDate);
    private LocalDate secondEndDate = currentDate.plusDays(FIVE_DAYS);
    private StartDateWithinTimeFramePredicate secondPredicate =
            new StartDateWithinTimeFramePredicate(currentDate, secondEndDate);
    private Model model = new ModelManager(TypicalNuScheduler.getTypicalNuScheduler(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalNuScheduler.getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void equals() {
        ViewUpcomingEventsCommand viewUpcomingFirstCommand = new ViewUpcomingEventsCommand(ONE_DAY, firstPredicate);
        ViewUpcomingEventsCommand viewUpcomingSecondCommand = new ViewUpcomingEventsCommand(FIVE_DAYS, secondPredicate);

        // same values -> returns true
        assertTrue(viewUpcomingFirstCommand.equals(new ViewUpcomingEventsCommand(ONE_DAY, firstPredicate)));

        // same object -> returns true
        assertTrue(viewUpcomingFirstCommand.equals(viewUpcomingFirstCommand));

        // null -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(null));

        // different types -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(1));

        // different value -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(viewUpcomingSecondCommand));
    }

    @Test
    public void execute_noEventsFound() {
        ViewUpcomingEventsCommand command = new ViewUpcomingEventsCommand(ONE_DAY, firstPredicate);
        expectedModel.updateFilteredEventList(firstPredicate);
        assertCommandSuccess(command, model, MESSAGE_SUCCESS_NO_EVENTS + MESSAGE_SUCCESS_TOMORROW, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_singleEventFound() {
        ViewUpcomingEventsCommand command = new ViewUpcomingEventsCommand(ONE_DAY, firstPredicate);
        String nextDay = LocalDate.now().plusDays(ONE_DAY).toString();
        Event newEvent = new EventBuilder().withStartDateTime(nextDay).build();
        model.addEvent(newEvent);
        expectedModel.addEvent(newEvent);
        expectedModel.updateFilteredEventList(firstPredicate);
        assertCommandSuccess(command, model, MESSAGE_SUCCESS_SINGLE_EVENT
                + MESSAGE_SUCCESS_TOMORROW, expectedModel);
        assertEquals(Arrays.asList(newEvent), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleEventsFound() {
        ViewUpcomingEventsCommand command = new ViewUpcomingEventsCommand(FIVE_DAYS, firstPredicate);
        String nextDay = LocalDate.now().plusDays(ONE_DAY).toString();
        String inThreeDays = LocalDate.now().plusDays(3).toString();
        Event firstNewEvent = new EventBuilder().withStartDateTime(nextDay).build();
        Event secondNewEvent = new EventBuilder().withStartDateTime(inThreeDays).build();
        model.addEvent(firstNewEvent);
        expectedModel.addEvent(firstNewEvent);
        model.addEvent(secondNewEvent);
        expectedModel.addEvent(secondNewEvent);
        expectedModel.updateFilteredEventList(secondPredicate);
        String successMessage = MESSAGE_SUCCESS_MULTIPLE_EVENTS
                + String.format(MESSAGE_SUCCESS_UPCOMING_DAYS, FIVE_DAYS);
        assertCommandSuccess(command, model, successMessage, expectedModel);
        assertEquals(Arrays.asList(firstNewEvent, secondNewEvent), model.getFilteredEventList());
    }
}
