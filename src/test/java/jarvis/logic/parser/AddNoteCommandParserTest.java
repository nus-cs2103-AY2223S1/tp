package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static jarvis.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static jarvis.logic.parser.CliSyntax.PREFIX_NOTE;
import static jarvis.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jarvis.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jarvis.commons.core.index.Index;
import jarvis.logic.commands.AddNoteCommand;

public class AddNoteCommandParserTest {
    private static final Index lessonIndex = Index.fromZeroBased(0);
    private static final Index studentIndex = Index.fromZeroBased(0);
    private static final String validNote = "valid note";
    private static final String emptyNote = "";
    private static final String blankNote = "    ";
    private static final String STUDENT_INDEX = " " + PREFIX_STUDENT_INDEX + lessonIndex.getOneBased();
    private static final String LESSON_INDEX = " " + PREFIX_LESSON_INDEX + studentIndex.getOneBased();
    private static final String NOTE_DESC = " " + PREFIX_NOTE + validNote;
    private static final String EMPTY_NOTE_DESC = " " + PREFIX_NOTE + emptyNote;
    private static final String BLANK_NOTE_DESC = " " + PREFIX_NOTE + blankNote;

    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LESSON_INDEX + STUDENT_INDEX + NOTE_DESC,
                new AddNoteCommand(lessonIndex, studentIndex, validNote));
        // parsing for overall notes
        assertParseSuccess(parser, LESSON_INDEX + NOTE_DESC,
                new AddNoteCommand(lessonIndex, null, validNote));
        // parsing for student notes
        assertParseSuccess(parser, LESSON_INDEX + STUDENT_INDEX + NOTE_DESC,
                new AddNoteCommand(lessonIndex, studentIndex, validNote));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

        // missing lesson prefix
        assertParseFailure(parser, NOTE_DESC, expectedMessage);
        assertParseFailure(parser, STUDENT_INDEX + NOTE_DESC, expectedMessage);

        // missing note prefix
        assertParseFailure(parser, LESSON_INDEX, expectedMessage);
        assertParseFailure(parser, LESSON_INDEX + STUDENT_INDEX, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_INDEX + STUDENT_INDEX + NOTE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        // blank note
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_INDEX + STUDENT_INDEX + BLANK_NOTE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        // empty note
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_INDEX + STUDENT_INDEX + EMPTY_NOTE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }
}
