package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class ClassStorageTest {

    @Test
    public void execute_hasConflictSuccess() {
        assertTrue(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(13, 0), LocalTime.of(15, 0)));
        assertTrue(ClassStorage.hasConflict(LocalTime.of(11, 0), LocalTime.of(15, 30),
                LocalTime.of(13, 0), LocalTime.of(14, 0)));
    }

    @Test
    public void execute_noConflictSuccess() {
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(13, 30), LocalTime.of(15, 0)));
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(11, 0), LocalTime.of(12, 30)));
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(11, 0), LocalTime.of(12, 0)));
        assertFalse(ClassStorage.hasConflict(null, null, null, null));
    }

}
