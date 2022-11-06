package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.LESSON_INDEX;
import static jarvis.logic.commands.CommandTestUtil.NOTE_INDEX;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.commands.CommandTestUtil.STUDENT_INDEX;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.DeleteNoteCommand;

public class DeleteNoteCommandParserTest {
    private static final Index noteIndex = Index.fromZeroBased(0);
    private static final Index lessonIndex = Index.fromZeroBased(0);
    private static final Index studentIndex = Index.fromZeroBased(0);

    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NOTE_INDEX + LESSON_INDEX + STUDENT_INDEX,
                new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex));
        // parsing for overall notes
        assertParseSuccess(parser, NOTE_INDEX + LESSON_INDEX,
                new DeleteNoteCommand(noteIndex, lessonIndex, null));
        // parsing for student notes
        assertParseSuccess(parser, NOTE_INDEX + LESSON_INDEX + STUDENT_INDEX,
                new DeleteNoteCommand(noteIndex, lessonIndex, studentIndex));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE);

        // missing lesson prefix
        assertParseFailure(parser, NOTE_INDEX, expectedMessage);
        assertParseFailure(parser, NOTE_INDEX + STUDENT_INDEX, expectedMessage);

        // missing note prefix
        assertParseFailure(parser, STUDENT_INDEX, expectedMessage);
        assertParseFailure(parser, LESSON_INDEX + STUDENT_INDEX, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NOTE_INDEX + LESSON_INDEX + STUDENT_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
    }
}
