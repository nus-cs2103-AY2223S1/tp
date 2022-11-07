package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.watson.model.Model;

public class CommandTest {

    @Test
    public void testCommand() {
        Command testCommand = new Command() {
            @Override
            public CommandResult execute(Model model) {
                return null;
            }
        };
        assertEquals("", testCommand.toString());
    }
}
