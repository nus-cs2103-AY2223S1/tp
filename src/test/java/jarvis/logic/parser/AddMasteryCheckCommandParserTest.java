package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_DESC;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_END_DATE;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_END_TIME;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_START_DATE;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_START_TIME;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_STUDENT_INDEX;
import static jarvis.logic.commands.CommandTestUtil.LESSON_DESC_MASTERY_CHECK;
import static jarvis.logic.commands.CommandTestUtil.LESSON_DESC_STUDIO;
import static jarvis.logic.commands.CommandTestUtil.LESSON_END_DATE;
import static jarvis.logic.commands.CommandTestUtil.LESSON_END_TIME;
import static jarvis.logic.commands.CommandTestUtil.LESSON_START_DATE;
import static jarvis.logic.commands.CommandTestUtil.LESSON_START_TIME;
import static jarvis.logic.commands.CommandTestUtil.LESSON_STUDENT_INDEX;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static jarvis.testutil.LessonBuilder.DEFAULT_MASTERY_CHECK_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_TIME_PERIOD;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.model.LessonDesc;
import jarvis.model.TimePeriod;

public class AddMasteryCheckCommandParserTest {

    private AddMasteryCheckCommandParser parser = new AddMasteryCheckCommandParser();

    private Set<Index> getFirstStudentIndex() {
        Set<Index> studentIndex = new HashSet<>();
        studentIndex.add(Index.fromOneBased(Integer.parseInt("1")));
        return studentIndex;
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LESSON_DESC_MASTERY_CHECK + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_DATE + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, getFirstStudentIndex()));

        // multiple desc - last desc accepted
        assertParseSuccess(parser, LESSON_DESC_STUDIO + LESSON_DESC_MASTERY_CHECK + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_DATE + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, getFirstStudentIndex()));
    }

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        // without end date
        assertParseSuccess(parser, LESSON_DESC_MASTERY_CHECK + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                new AddMasteryCheckCommand(DEFAULT_MASTERY_CHECK_DESC, DEFAULT_TIME_PERIOD, getFirstStudentIndex()));

        // without lesson desc
        assertParseSuccess(parser, LESSON_START_DATE + LESSON_START_TIME + LESSON_END_DATE
                        + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                new AddMasteryCheckCommand(null, DEFAULT_TIME_PERIOD, getFirstStudentIndex()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMasteryCheckCommand.MESSAGE_USAGE);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + LESSON_END_TIME, expectedMessage);

        assertParseFailure(parser,
                LESSON_START_DATE + LESSON_START_TIME + LESSON_STUDENT_INDEX, expectedMessage);

        assertParseFailure(parser,
                LESSON_START_DATE + LESSON_END_TIME + LESSON_STUDENT_INDEX, expectedMessage);

        assertParseFailure(parser,
                LESSON_START_TIME + LESSON_END_TIME + LESSON_STUDENT_INDEX, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid desc
        assertParseFailure(parser, INVALID_LESSON_DESC + LESSON_START_DATE + LESSON_START_TIME
                + LESSON_END_TIME + LESSON_STUDENT_INDEX, LessonDesc.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_LESSON_START_DATE + LESSON_START_TIME
                + LESSON_END_TIME + LESSON_STUDENT_INDEX, TimePeriod.MESSAGE_CONSTRAINTS_DATE);

        assertParseFailure(parser, LESSON_START_DATE + INVALID_LESSON_START_TIME
                + LESSON_END_TIME + LESSON_STUDENT_INDEX, TimePeriod.MESSAGE_CONSTRAINTS_TIME);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + INVALID_LESSON_END_DATE
                + LESSON_END_TIME + LESSON_STUDENT_INDEX, TimePeriod.MESSAGE_CONSTRAINTS_DATE);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + INVALID_LESSON_END_TIME
                + LESSON_STUDENT_INDEX, TimePeriod.MESSAGE_CONSTRAINTS_TIME);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + LESSON_END_TIME
                + INVALID_LESSON_STUDENT_INDEX, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

        // non empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_DESC_MASTERY_CHECK + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMasteryCheckCommand.MESSAGE_USAGE));

    }
}
