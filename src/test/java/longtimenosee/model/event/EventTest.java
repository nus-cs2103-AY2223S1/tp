package longtimenosee.model.event;

import static longtimenosee.testutil.TypicalEvents.WITH_ALICE;
import static longtimenosee.testutil.TypicalEvents.WITH_BENSON;
import static longtimenosee.testutil.TypicalEvents.WITH_ELLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.EventBuilder;


public class EventTest {


    @Test
    public void isSameEvent() {

        // same object -> returns true
        assertTrue(WITH_ALICE.isSameEvent(WITH_ALICE));

        // null -> returns false
        assertFalse(WITH_ALICE.isSameEvent(null));

        // not same object -> return false
        assertFalse(WITH_ALICE.isSameEvent(WITH_BENSON));

        // same object except duration different
        Event editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-12")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("12:00__13:00")
                .build();
        assertFalse(editedAlice.isSameEvent(WITH_ALICE));

        // same object except different name
        editedAlice = new EventBuilder().withName("Alice Tan")
                .withDate("2022-12-12")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.isSameEvent(WITH_ALICE));

        // same object except different description
        editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-12")
                .withDescription("Dinner with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.isSameEvent(WITH_ALICE));

        // same object, different date
        editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-13")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.isSameEvent(WITH_ALICE));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceCopy = new EventBuilder(WITH_ALICE).build();
        assertTrue(WITH_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(WITH_ALICE.equals(WITH_ALICE));

        // null -> returns false
        assertFalse(WITH_ALICE.equals(null));

        // different type -> returns false
        assertFalse(WITH_ALICE.equals(5));

        // different person -> returns false
        assertFalse(WITH_ALICE.equals(WITH_ELLE));


        // same object except duration different
        Event editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-12")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("12:00__13:00")
                .build();
        assertFalse(editedAlice.equals(WITH_ALICE));

        // same object except different name
        editedAlice = new EventBuilder().withName("Alice Tan")
                .withDate("2022-12-12")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.equals(WITH_ALICE));

        // same object except different description
        editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-12")
                .withDescription("Dinner with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.equals(WITH_ALICE));

        // same object, different date
        editedAlice = new EventBuilder().withName("Alice Pauline")
                .withDate("2022-12-13")
                .withDescription("Lunch with Alice Pauline")
                .withDuration("10:00__12:00")
                .build();
        assertFalse(editedAlice.equals(WITH_ALICE));


    }
}

