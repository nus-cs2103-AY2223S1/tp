package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_LIST_SIZE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MARK_ATTENDANCE;
import static seedu.address.logic.parser.AttendanceCommandParser.ATTENDANCE_ERROR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceAddCommand;
import seedu.address.logic.commands.AttendanceDeleteCommand;
import seedu.address.logic.commands.AttendanceMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AttendanceCommandParserTest {

    private final AttendanceCommandParser parser = new AttendanceCommandParser();

    @Test
    public void parseCommand_attendance_add() throws Exception {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = AttendanceAddCommand.COMMAND_WORD + " "
                + targetIndex.getOneBased() + CLASS_GROUP_DESC_BOB
                + VALID_ATTENDANCE_LIST_SIZE;

        assertTrue(parser.parse(userInput) instanceof AttendanceAddCommand);
    }

    @Test
    public void parseCommand_attendance_delete() throws Exception {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = AttendanceDeleteCommand.COMMAND_WORD + " "
                + targetIndex.getOneBased();

        assertTrue(parser.parse(userInput) instanceof AttendanceDeleteCommand);
    }
    @Test
    public void parseCommand_attendance_mark() throws Exception {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = AttendanceMarkCommand.COMMAND_WORD + " "
                + targetIndex.getOneBased() + VALID_LESSON_ONE
                + VALID_MARK_ATTENDANCE;

        assertTrue(parser.parse(userInput) instanceof AttendanceMarkCommand);
    }
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, ATTENDANCE_ERROR, ()
                -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, ATTENDANCE_ERROR, () -> parser.parse("unknownCommand"));
    }
}
