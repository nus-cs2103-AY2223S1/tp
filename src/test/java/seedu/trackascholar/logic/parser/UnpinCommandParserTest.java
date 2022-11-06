package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.UnPinCommand;
import seedu.trackascholar.model.applicant.Name;

public class UnpinCommandParserTest {
    private static final UnPinCommandParser parser = new UnPinCommandParser();

    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnPinCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsUnpinCommand() {
        // no leading and trailing whitespaces
        Name alexName = new Name("Alex Ong");
        UnPinCommand expectedUnpinCommand =
                new UnPinCommand(alexName);
        assertParseSuccess(parser, "Alex Ong", expectedUnpinCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + "Alex Ong" + " \n", expectedUnpinCommand);
    }

    @Test
    public void parse_emptyArgForUnpinCommand_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }
}
