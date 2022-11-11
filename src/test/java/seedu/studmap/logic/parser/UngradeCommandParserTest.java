package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.studmap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.UngradeCommand;
import seedu.studmap.model.student.Assignment;

public class UngradeCommandParserTest {

    private UngradeCommandParser parser = new UngradeCommandParser();

    @Test
    public void parse_validArgsAssignment_returnUngradeCommand() {
        String assignmentName = "A01";
        assertParseSuccess(parser, "1 a/   " + assignmentName,
                new UngradeCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                        new UngradeCommand.UngradeCommandStudentEditor(
                                new Assignment(assignmentName, Assignment.Status.NEW))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngradeCommand.MESSAGE_USAGE));
    }
}
