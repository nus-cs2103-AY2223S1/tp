package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.iteration.DeleteIterationCommand;
import seedu.address.logic.parser.iteration.DeleteIterationCommandParser;

public class DeleteIterationCommandParserTest {
    private final DeleteIterationCommandParser parser = new DeleteIterationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCustomerCommand() {
        assertParseSuccess(parser, "1", new DeleteIterationCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIterationCommand.MESSAGE_USAGE));
    }
}
