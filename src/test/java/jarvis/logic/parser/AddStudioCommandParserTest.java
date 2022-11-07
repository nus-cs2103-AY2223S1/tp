package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_DESC;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_END_DATE;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_END_TIME;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_START_DATE;
import static jarvis.logic.commands.CommandTestUtil.INVALID_LESSON_START_TIME;
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
import static jarvis.testutil.LessonBuilder.DEFAULT_STUDIO_DESC;
import static jarvis.testutil.LessonBuilder.DEFAULT_TIME_PERIOD;

import org.junit.jupiter.api.Test;

import jarvis.logic.commands.AddStudioCommand;
import jarvis.model.LessonDesc;
import jarvis.model.TimePeriod;

public class AddStudioCommandParserTest {

    private AddStudioCommandParser parser = new AddStudioCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LESSON_DESC_STUDIO + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_DATE + LESSON_END_TIME,
                new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD));

        // multiple desc - last desc accepted
        assertParseSuccess(parser, LESSON_DESC_STUDIO + LESSON_DESC_STUDIO + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_DATE + LESSON_END_TIME,
                new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD));
    }

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        // without end date
        assertParseSuccess(parser, LESSON_DESC_STUDIO + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_TIME,
                new AddStudioCommand(DEFAULT_STUDIO_DESC, DEFAULT_TIME_PERIOD));

        // without lesson desc
        assertParseSuccess(parser, LESSON_START_DATE + LESSON_START_TIME + LESSON_END_DATE + LESSON_END_TIME,
                new AddStudioCommand(null, DEFAULT_TIME_PERIOD));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudioCommand.MESSAGE_USAGE);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME, expectedMessage);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_END_TIME, expectedMessage);

        assertParseFailure(parser, LESSON_START_TIME + LESSON_END_TIME, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid desc
        assertParseFailure(parser, INVALID_LESSON_DESC + LESSON_START_DATE + LESSON_START_TIME
                + LESSON_END_TIME, LessonDesc.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_LESSON_START_DATE + LESSON_START_TIME
                + LESSON_END_TIME, TimePeriod.MESSAGE_CONSTRAINTS_DATE);

        assertParseFailure(parser, LESSON_START_DATE + INVALID_LESSON_START_TIME
                + LESSON_END_TIME, TimePeriod.MESSAGE_CONSTRAINTS_TIME);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + INVALID_LESSON_END_DATE
                + LESSON_END_TIME, TimePeriod.MESSAGE_CONSTRAINTS_DATE);

        assertParseFailure(parser, LESSON_START_DATE + LESSON_START_TIME + INVALID_LESSON_END_TIME,
                TimePeriod.MESSAGE_CONSTRAINTS_TIME);

        // non empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_DESC_STUDIO + LESSON_START_DATE
                        + LESSON_START_TIME + LESSON_END_TIME + LESSON_STUDENT_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudioCommand.MESSAGE_USAGE));
    }
}
