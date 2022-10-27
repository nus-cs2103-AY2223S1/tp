package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddRateCommand;
import seedu.clinkedin.model.person.Rating;



public class AddRateCommandParserTest {
    private final AddRateCommandParser parser = new AddRateCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // No index given
        assertParseFailure(parser, "rate/4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRateCommand.MESSAGE_USAGE));

        // No valid prefix given
        assertParseFailure(parser, "1 Test", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        AddRateCommand expectedFindCommand = new AddRateCommand(Index.fromOneBased(1), new Rating("4"));
        assertParseSuccess(parser, "1 rate/4", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t1 rate/4\t", expectedFindCommand);
    }
}
