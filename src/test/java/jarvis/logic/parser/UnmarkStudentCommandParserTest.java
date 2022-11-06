package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_INDEX;
import static jarvis.logic.commands.CommandTestUtil.INVALID_STUDENT_INDEX;
import static jarvis.logic.commands.CommandTestUtil.LESSON_INDEX;
import static jarvis.logic.commands.CommandTestUtil.STUDENT_INDEX;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.UnmarkStudentCommand;
import jarvis.testutil.TypicalIndexes;

public class UnmarkStudentCommandParserTest {
    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST;
    private static final Index studentIndex = TypicalIndexes.INDEX_FIRST;

    private UnmarkStudentCommandParser parser = new UnmarkStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, LESSON_INDEX + STUDENT_INDEX,
                new UnmarkStudentCommand(lessonIndex, studentIndex));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertParseFailure(parser, LESSON_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkStudentCommand.MESSAGE_USAGE)); // missing student index
        assertParseFailure(parser, STUDENT_INDEX, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkStudentCommand.MESSAGE_USAGE)); // missing lesson index
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_LESSON_INDEX + STUDENT_INDEX,
                ParserUtil.MESSAGE_INVALID_INDEX);
        // invalid lesson index
        assertParseFailure(parser, LESSON_INDEX + INVALID_STUDENT_INDEX,
                ParserUtil.MESSAGE_INVALID_INDEX);
        // missing student index

    }
}
