package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GenerateMessageCommand;

class GenerateMessageCommandParserTest {
    private GenerateMessageCommandParser parser = new GenerateMessageCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMessageCommand() {
        Index index1 = Index.fromOneBased(1);
        assertParseSuccess(parser, "1 1", new GenerateMessageCommand(index1, index1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateMessageCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1.1", GenerateMessageCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1.1 1.1", GenerateMessageCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "0 1", Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
