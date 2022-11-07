package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SUPPLY_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteItemCommand;

public class DeleteItemCommandParserTest {
    private DeleteItemCommandParser parser = new DeleteItemCommandParser();

    @Test
    public void parse_validArgs_returnsUnMarkTaskCommand() {
        assertParseSuccess(parser, "1", new DeleteItemCommand(INDEX_FIRST_SUPPLY_ITEM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItemCommand.MESSAGE_USAGE));
    }
}
