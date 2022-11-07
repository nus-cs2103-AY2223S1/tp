package modtrekt.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modtrekt.testutil.ModelStub;

public class HelpCommandTest {

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        HelpCommand cmd = new HelpCommand();
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommandSuccess() {
        ModelStub model = new ModelStub();
        HelpCommand cmd = new HelpCommand();
        assertDoesNotThrow(() -> cmd.execute(model));
    }
}
