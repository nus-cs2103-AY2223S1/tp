package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMessages.MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.MESSAGE_RECOMMEND_PRODUCT;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateMessageCommand;

class CreateMessageCommandParserTest {
    private CreateMessageCommandParser parser = new CreateMessageCommandParser();

    @Test
    public void parse_validArgs_returnsCreateMessageCommand() {
        assertParseSuccess(parser, MESSAGE_HAPPY_BIRTHDAY, new CreateMessageCommand(VALID_MESSAGE_HAPPY_BIRTHDAY));
        assertParseSuccess(parser, MESSAGE_RECOMMEND_PRODUCT,
                new CreateMessageCommand(VALID_MESSAGE_RECOMMEND_PRODUCT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMessageCommand.MESSAGE_USAGE));
    }
}
