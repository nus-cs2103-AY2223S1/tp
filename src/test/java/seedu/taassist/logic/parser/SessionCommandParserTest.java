package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.SESSION_DESC_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.SessionCommand;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;
import seedu.taassist.testutil.SessionBuilder;

public class SessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SessionCommand.COMMAND_WORD, SessionCommand.MESSAGE_USAGE);
    private SessionCommandParser parser = new SessionCommandParser();

    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SESSION_DESC_LAB1, MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_emptySessionName_failure() {
        assertParseFailure(parser, " " + PREFIX_SESSION + PREAMBLE_WHITESPACE, Session.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, SESSION_DESC_LAB1 + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_onlyValidSessionName_success() {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1, new SessionCommand(session));
    }

    @Test
    public void parse_validSessionNameAndValidDate_success() {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).withDate(VALID_DATE).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1 + DATE_DESC, new SessionCommand(session));
    }
}
