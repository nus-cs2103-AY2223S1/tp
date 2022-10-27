package seedu.taassist.logic.commands;

import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class SessionCommandTest {

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SessionCommand(null));
    }
}
