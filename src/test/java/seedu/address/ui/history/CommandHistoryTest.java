package seedu.address.ui.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code CommandHistory}.
 */
public class CommandHistoryTest {

    @Test
    public void up_previousCommandExist_returnPreviousCommand () {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("first command");
        commandHistory.add("second command");

        assertEquals("second command", commandHistory.up());
    }

    @Test
    public void up_previousCommandAbsent_returnEmptyString () {
        CommandHistory commandHistory = new CommandHistory();

        assertEquals("", commandHistory.up());
    }

    @Test
    public void down_nextCommandExist_returnNextCommand () {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("first command");
        commandHistory.add("second command");
        commandHistory.up();
        commandHistory.up();

        assertEquals("second command", commandHistory.down());
    }

    @Test
    public void down_nextCommandAbsent_returnEmptyString () {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.add("first command");
        commandHistory.add("second command");

        assertEquals("", commandHistory.down());
    }

}
