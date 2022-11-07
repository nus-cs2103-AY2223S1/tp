package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    CommandHistory commandHistory = new CommandHistory();
    String[] commands = {"view 1", "delappt 2", "consult 1", "edit 4 n/Charles", "view 1"};

    @Test
    void addCommand_addFirstCommand_success() {
        commandHistory.addCommand(commands[0]);
        commandHistory.previousCommand();
        assertEquals(commandHistory.getCommand().orElse(""), commands[0]);

        commandHistory = new CommandHistory();
    }

    @Test
    void addCommand_addCommandPointsToLatestCommand_success() {
        commandHistory.addCommand(commands[0]);
        commandHistory.addCommand(commands[1]);
        commandHistory.addCommand(commands[2]);

        commandHistory.previousCommand();
        commandHistory.previousCommand();
        commandHistory.previousCommand();

        commandHistory.addCommand(commands[3]);

        commandHistory.previousCommand();

        assertEquals(commandHistory.getCommand().orElse(""), commands[3]);

        commandHistory = new CommandHistory();
    }

    @Test
    void getCommand_WithoutPreviousGivesEmptyOptional_success() {
        commandHistory.addCommand(commands[0]);
        assertTrue(commandHistory.getCommand().isEmpty());

        commandHistory = new CommandHistory();

    }

    @Test
    void previousCommand_CapsAtPointerZero() {
        commandHistory.addCommand(commands[0]);
        commandHistory.previousCommand();
        commandHistory.previousCommand();
        assertEquals(commandHistory.getCommand().orElse(""), commands[0]);

        commandHistory = new CommandHistory();

    }

    @Test
    void nextCommand_CapsAtArraySize() {
        commandHistory.addCommand(commands[0]);
        commandHistory.addCommand(commands[1]);
        commandHistory.previousCommand();
        commandHistory.previousCommand();
        assertEquals(commandHistory.getCommand().orElse(""), commands[0]);
        commandHistory.nextCommand();
        assertEquals(commandHistory.getCommand().orElse(""), commands[1]);
        commandHistory.nextCommand();
        assertTrue(commandHistory.getCommand().isEmpty());

        commandHistory = new CommandHistory();

    }
}