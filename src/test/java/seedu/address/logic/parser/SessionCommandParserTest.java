package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSION_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SessionCommand;
import seedu.address.model.person.Session;

public class SessionCommandParserTest {

    private static final String INVALID_DAY_SESSION = "Tues 09:00";
    private static final String INVALID_HOUR_SESSION = "Tue 25:00";
    private static final String INVALID_MINUTE_SESSION = "Tue 09:60";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SessionCommand.MESSAGE_USAGE);

    private SessionCommandParser parser = new SessionCommandParser();

    @Test
    public void parse_validArgs_returnsAttendanceCommand() {
        String userInput = "1 " + PREFIX_SESSION + VALID_SESSION_AMY;
        SessionCommand expectedCommand =
                new SessionCommand(INDEX_FIRST_PERSON, new Session(VALID_SESSION_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noPrefixField_failure() {
        assertParseFailure(parser, "1 " + VALID_SESSION_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFormat_failure() {
        // input "Tues" instead of the correct "Tue"
        assertParseFailure(parser, "1 " + PREFIX_SESSION + INVALID_DAY_SESSION,
                Session.MESSAGE_CONSTRAINTS);

        // invalid hour 25
        assertParseFailure(parser, "1 " + PREFIX_SESSION + INVALID_HOUR_SESSION,
                Session.MESSAGE_CONSTRAINTS);

        // invalid minute 60
        assertParseFailure(parser, "1 " + PREFIX_SESSION + INVALID_MINUTE_SESSION,
                Session.MESSAGE_CONSTRAINTS);
    }
}
