package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.SUBJECT_ARG;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.PredictionCommand;

public class PredictionCommandParserTest {
    private final PredictionCommandParser parser = new PredictionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PredictionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PredictionCommand.MESSAGE_USAGE);

        // missing name prefix and difficulty prefix
        assertParseFailure(parser, SUBJECT_ARG, expectedMessage);

        // missing difficulty prefix
        assertParseFailure(parser, NAME_DESC_AMY + SUBJECT_ARG, expectedMessage);
    }
}
