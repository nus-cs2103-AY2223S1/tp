package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.GRADE_VALUE_DESC_12345;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_GRADE_VALUE_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.SESSION_DESC_LAB1;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_GRADE_VALUE_12345;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_SESSION_LAB1;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.taassist.logic.parser.ParserUtil.parseGrade;
import static seedu.taassist.testutil.TestUtil.joinWithSpace;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.GradeCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.SessionData;
import seedu.taassist.testutil.SessionBuilder;

public class GradeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.COMMAND_WORD, GradeCommand.MESSAGE_USAGE);
    private GradeCommandParser parser = new GradeCommandParser();



    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = INVALID_INDEX + SESSION_DESC_LAB1 + GRADE_VALUE_DESC_12345;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidGrade_failure() {
        String userInput = INDEX_FIRST_STUDENT + SESSION_DESC_LAB1 + INVALID_GRADE_VALUE_DESC;
        assertParseFailure(parser, userInput, SessionData.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptySession_failure() {
        String userInput = joinWithSpace(INDEX_FIRST_STUDENT.toString(), PREFIX_SESSION + PREAMBLE_WHITESPACE,
                GRADE_VALUE_DESC_12345);
        assertParseFailure(parser, userInput, Session.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validIndexGradeAndSession_success() throws ParseException {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        String userInput = INDEX_FIRST_STUDENT + SESSION_DESC_LAB1 + GRADE_VALUE_DESC_12345;
        Double grade = parseGrade(VALID_GRADE_VALUE_12345);
        assertParseSuccess(parser, userInput, new GradeCommand(new ArrayList<>(List.of(INDEX_FIRST_STUDENT)),
                session , grade));
    }

    @Test
    public void parse_validIndicesGradeAndSession_success() throws ParseException {
        Session session = new SessionBuilder().withName(VALID_SESSION_LAB1).build();
        String userInput = INDEX_FIRST_STUDENT + " " + INDEX_THIRD_STUDENT + SESSION_DESC_LAB1 + GRADE_VALUE_DESC_12345;
        List<Index> indices = new ArrayList<>(List.of(INDEX_FIRST_STUDENT, INDEX_THIRD_STUDENT));
        Double grade = parseGrade(VALID_GRADE_VALUE_12345);
        assertParseSuccess(parser, userInput, new GradeCommand(indices, session, grade));
    }
}
