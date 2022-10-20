package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ViewUpcomingEventsCommandTest {
    private static final int ONE_DAY = 1;
    private static final int FIVE_DAYS = 5;

    @Test
    public void equals() {
        ViewUpcomingEventsCommand viewUpcomingFirstCommand = new ViewUpcomingEventsCommand(ONE_DAY);
        ViewUpcomingEventsCommand viewUpcomingSecondCommand = new ViewUpcomingEventsCommand(FIVE_DAYS);

        // same values -> returns true
        assertTrue(viewUpcomingFirstCommand.equals(new ViewUpcomingEventsCommand(ONE_DAY)));

        // same object -> returns true
        assertTrue(viewUpcomingFirstCommand.equals(viewUpcomingFirstCommand));

        // null -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(null));

        // different types -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(1));

        // different value -> returns false
        assertFalse(viewUpcomingFirstCommand.equals(viewUpcomingSecondCommand));
    }
}
