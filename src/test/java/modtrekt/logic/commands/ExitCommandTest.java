package modtrekt.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modtrekt.testutil.ModelStub;

public class ExitCommandTest {

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        ExitCommand cmd = new ExitCommand();
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommandSuccess() {
        ModelStub model = new ModelStub();
        ExitCommand cmd = new ExitCommand();
        assertDoesNotThrow(() -> cmd.execute(model));
    }
}
