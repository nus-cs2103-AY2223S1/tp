package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.INVALID_STUDIO_PARTICIPATION;
import static jarvis.logic.commands.CommandTestUtil.LESSON_INDEX;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.commands.CommandTestUtil.STUDENT_INDEX;
import static jarvis.logic.commands.CommandTestUtil.STUDIO_PARTICIPATION;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddParticipationCommand;
import jarvis.testutil.TypicalIndexes;

public class AddParticipationCommandParserTest {
    private static final Index lessonIndex = TypicalIndexes.INDEX_FIRST;
    private static final Index studentIndex = TypicalIndexes.INDEX_FIRST;
    private static final int participation = 100;

    private AddParticipationCommandParser parser = new AddParticipationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STUDIO_PARTICIPATION + LESSON_INDEX + STUDENT_INDEX,
                new AddParticipationCommand(participation, lessonIndex, studentIndex));

        assertParseSuccess(parser, STUDIO_PARTICIPATION + LESSON_INDEX + STUDENT_INDEX,
                new AddParticipationCommand(participation, lessonIndex, studentIndex));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParticipationCommand.MESSAGE_USAGE);

        // missing lesson prefix
        assertParseFailure(parser, STUDIO_PARTICIPATION + STUDENT_INDEX, expectedMessage);

        // missing student prefix
        assertParseFailure(parser, STUDIO_PARTICIPATION + LESSON_INDEX, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STUDIO_PARTICIPATION + LESSON_INDEX + STUDENT_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParticipationCommand.MESSAGE_USAGE));
        // invalid participation value
        assertParseFailure(parser, INVALID_STUDIO_PARTICIPATION + LESSON_INDEX + STUDENT_INDEX,
                Messages.MESSAGE_INVALID_PARTICIPATION);
    }
}
