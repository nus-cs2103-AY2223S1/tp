package hobbylist.logic.parser;

import static hobbylist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.Messages;
import hobbylist.logic.commands.FindStatusCommand;
import hobbylist.model.activity.StatusMatchesGivenStatus;

public class FindStatusCommandParserTest {
    private FindStatusCommandParser parser = new FindStatusCommandParser();

    @Test
    public void parse_emptyString_throwsParseException() {
        String expectedString = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindStatusCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "       ", expectedString);
    }

    @Test
    public void parse_success_returnsFindStatusCommand() {
        FindStatusCommand expectedCommand = new FindStatusCommand(new StatusMatchesGivenStatus("UPCOMING"));
        assertParseSuccess(parser, "UPCOMING", expectedCommand);
    }
}
