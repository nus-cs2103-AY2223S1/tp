package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.GradeCommand;
import seedu.watson.model.student.subject.Subject;

public class GradeCommandParserTest {
    private final GradeCommandParser parser = new GradeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

        // missing assessment difficulty
        assertParseFailure(parser, " MATH_Quiz1_10_0.5", expectedMessage);

        // missing subject
        assertParseFailure(parser, " Quiz1_10_0.5_3.5", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, Subject.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, " MAT2_Quiz1_10_0.5_3.5", expectedMessage);
    }
}
