package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private static final ImportCommandParser parser = new ImportCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);

    private ImportCommandParserTest() {

    }

    @Test
    public void parse_wrongInput_failure() {
        assertParseFailure(parser, "something", ERROR_MESSAGE);
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "1234", ERROR_MESSAGE);
    }

    @Test
    public void parse_correctInput_success() {
        assertParseSuccess(parser, ImportCommand.KEEP, new ImportCommand(ImportCommand.KEEP));
        assertParseSuccess(parser, ImportCommand.REPLACE, new ImportCommand(ImportCommand.REPLACE));
    }
}
