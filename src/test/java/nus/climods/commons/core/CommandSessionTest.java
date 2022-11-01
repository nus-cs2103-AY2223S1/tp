package nus.climods.commons.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.storage.exceptions.StorageException;

class CommandSessionTest {

    private static final CommandSession.CommandExecutor dummyCommandExecutor = commandText -> null;

    @Test
    public void verify_commandHistory_correctOrderTest1() throws CommandException, ParseException, StorageException {
        CommandSession commandSession = new CommandSession(dummyCommandExecutor);
        commandSession.execute("command 1");
        commandSession.execute("command 2");
        commandSession.execute("command 3");

        assertEquals(commandSession.getPreviousCommand(), "command 3");
        assertEquals(commandSession.getPreviousCommand(), "command 2");
        assertEquals(commandSession.getPreviousCommand(), "command 1");
        assertEquals(commandSession.getPreviousCommand(), "");
        assertEquals(commandSession.getNextCommand(), "command 1");
        assertEquals(commandSession.getNextCommand(), "command 2");
        assertEquals(commandSession.getNextCommand(), "command 3");
        assertEquals(commandSession.getNextCommand(), "");
    }

    @Test
    public void verify_commandHistory_correctOrderTest2() throws CommandException, ParseException, StorageException {
        CommandSession commandSession = new CommandSession(dummyCommandExecutor);
        commandSession.execute("command 1");
        commandSession.execute("command 2");

        assertEquals(commandSession.getPreviousCommand(), "command 2");
        assertEquals(commandSession.getNextCommand(), "");

        assertEquals(commandSession.getPreviousCommand(), "command 2");
        assertEquals(commandSession.getPreviousCommand(), "command 1");
        assertEquals(commandSession.getNextCommand(), "command 2");

        assertEquals(commandSession.getPreviousCommand(), "command 1");
        assertEquals(commandSession.getPreviousCommand(), "");
        assertEquals(commandSession.getNextCommand(), "command 1");
        assertEquals(commandSession.getNextCommand(), "command 2");
        assertEquals(commandSession.getNextCommand(), "");
    }
}
