package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_SESSION_NAME;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.SESSION_DESC_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.SESSION_DESC_TUT3;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_TUT3;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.AddsCommand;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;
import seedu.taassist.testutil.SessionBuilder;

public class AddsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddsCommand.COMMAND_WORD, AddsCommand.MESSAGE_USAGE);
    private AddsCommandParser parser = new AddsCommandParser();


    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SESSION_DESC_LAB1, MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_invalidSessionName_failure() {
        assertParseFailure(parser, " " + PREFIX_SESSION + PREAMBLE_WHITESPACE, Session.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_SESSION_NAME, Session.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, SESSION_DESC_LAB1 + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_singleOnlyValidSessionName_success() {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1, new AddsCommand(new HashSet<>(List.of(session))));
    }

    @Test
    public void parse_multipleOnlyValidSessionName_success() {
        Session lab1 = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        Session tut3 = new SessionBuilder().withName(VALID_SESSION_TUT3).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1 + SESSION_DESC_TUT3,
                new AddsCommand(new HashSet<>(List.of(lab1, tut3))));
    }

    @Test
    public void parse_singleValidSessionNameAndValidDate_success() {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).withDate(VALID_DATE).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1 + DATE_DESC,
                new AddsCommand(new HashSet<>(List.of(session))));
    }

    @Test
    public void parse_multipleValidSessionNameAndValidDate_success() {
        Session lab1 = new SessionBuilder().withName(VALID_SESSION_LAB1).withDate(VALID_DATE).build();
        Session tut3 = new SessionBuilder().withName(VALID_SESSION_TUT3).withDate(VALID_DATE).build();
        assertParseSuccess(parser, SESSION_DESC_LAB1 + SESSION_DESC_TUT3 + DATE_DESC,
                new AddsCommand(new HashSet<>(List.of(lab1, tut3))));
    }
}
