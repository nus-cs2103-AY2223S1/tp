package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UngroupCommand;
import seedu.address.model.group.Group;

public class UngroupCommandParserTest {

    private UngroupCommandParser parser = new UngroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // missing group name
        assertParseFailure(parser, "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
        // missing index
        assertParseFailure(parser, "ab", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
        // missing group name with space
        assertParseFailure(parser, "2 ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
        // missing index with space
        assertParseFailure(parser, " ab", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
        // invalid group name (has space)
        assertParseFailure(parser, "2 a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
        // invalid group name (not alphanumeric)
        assertParseFailure(parser, "2 ,.,.,", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UngroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGroupCommand() {
        UngroupCommand expectedUngroupCommand =
            new UngroupCommand(Index.fromOneBased(2), new Group("groupname"));
        assertParseSuccess(parser, "2 groupname", expectedUngroupCommand);
    }

}
