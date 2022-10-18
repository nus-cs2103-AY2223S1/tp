package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMessages.MESSAGE_HAPPY_BIRTHDAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateMessageCommand;
import seedu.address.logic.commands.DeleteMessageCommand;
import seedu.address.logic.commands.GenerateMessageCommand;
import seedu.address.logic.commands.ListMessageCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class MessageCommandGroupParserTest {
    private MessageCommandGroupParser parser = new MessageCommandGroupParser();

    @Test
    public void parse_validArgs_returnsMessageCommandGroupParserTest() throws ParseException {

        assertParseSuccess(parser, "create " + MESSAGE_HAPPY_BIRTHDAY,
                new CreateMessageCommandParser().parse(MESSAGE_HAPPY_BIRTHDAY));

        assertParseSuccess(parser, "delete 1", new DeleteMessageCommandParser().parse("1"));

        assertParseSuccess(parser, "generate 1 1", new GenerateMessageCommandParser().parse("1 1"));

        assertParseSuccess(parser, "list", new ListMessageCommandParser().parse(""));
        assertParseSuccess(parser, "list sometext", new ListMessageCommandParser().parse("sometext"));

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String errorMessage = String.format("%s\n\n%s\n\n%s\n\n%s",
                ListMessageCommand.MESSAGE_USAGE,
                CreateMessageCommand.MESSAGE_USAGE,
                DeleteMessageCommand.MESSAGE_USAGE,
                GenerateMessageCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        assertParseFailure(parser, "create",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMessageCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "delete",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMessageCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "generate",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateMessageCommand.MESSAGE_USAGE));
    }
}
