package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ModulesLeftCommand;

public class ModulesLeftCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModulesLeftCommand.MESSAGE_USAGE);

    private ModulesLeftCommandParser parser = new ModulesLeftCommandParser();

    @Test
    public void parse_validIndex_returnsModulesLeftCommand() {
        assertParseSuccess(parser, "1", new ModulesLeftCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "bob", MESSAGE_INVALID_FORMAT);
    }
}
