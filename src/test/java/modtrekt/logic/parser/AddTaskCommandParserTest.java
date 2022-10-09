package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.commands.CommandTestUtil.VALID_DESC_2;
import static modtrekt.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.AddTaskCommand;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // prefix missing
        assertParseFailure(parser, VALID_DESC_2,
                expectedMessage);
    }
}
