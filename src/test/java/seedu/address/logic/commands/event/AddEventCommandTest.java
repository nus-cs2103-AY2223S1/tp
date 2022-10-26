package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithEvent;
import seedu.address.model.NuScheduler;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT, () -> addEventCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Event presentation = new EventBuilder().withTitle("Presentation")
                .withStartDateTime("2022-04-20 08:00")
                .withEndDateTime("2022-04-20 10:00")
                .build();
        Event practice = new EventBuilder().withTitle("Practice")
                .withStartDateTime("2022-05-20 08:00")
                .withEndDateTime("2022-05-20 10:00")
                .build();
        AddEventCommand addPresentationCommand = new AddEventCommand(presentation);
        AddEventCommand addPracticeCommand = new AddEventCommand(practice);

        // same object -> returns true
        assertTrue(addPresentationCommand.equals(addPresentationCommand));

        // same values -> returns true
        AddEventCommand addPresentationCommandCopy = new AddEventCommand(presentation);
        assertTrue(addPresentationCommand.equals(addPresentationCommandCopy));

        // different types -> returns false
        assertFalse(addPresentationCommand.equals(1));

        // null -> returns false
        assertFalse(addPresentationCommand.equals(null));

        // different event -> returns false
        assertFalse(addPresentationCommand.equals(addPracticeCommand));
    }

    /**
     * A Model stub that always accept the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyNuScheduler getNuScheduler() {
            return new NuScheduler();
        }
    }

}
