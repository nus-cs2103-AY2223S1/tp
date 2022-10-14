package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_PROJECT_STATUS_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_STATUS_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_STATUS_DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_PROJECT_STATUS_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseFailure;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.StudentId;

public class MarkCommandParserTest {

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        // normal
        assertParseSuccess(parser, STUDENT_ID_DESC_AMY + PROJECT_STATUS_DESC_AMY,
                new MarkCommand(new StudentId(VALID_STUDENT_ID_AMY), new ProjectStatus(VALID_PROJECT_STATUS_AMY)));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, STUDENT_ID_DESC_AMY + PROJECT_STATUS_DESC_BOB + PROJECT_STATUS_DESC_AMY,
                new MarkCommand(new StudentId(VALID_STUDENT_ID_AMY), new ProjectStatus(VALID_PROJECT_STATUS_AMY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing prefixes
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));

        // missing student ID
        assertParseFailure(parser, VALID_STUDENT_ID_AMY + PROJECT_STATUS_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));

        // missing status
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + VALID_PROJECT_STATUS_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));

        // invalid student ID
        assertParseFailure(parser, INVALID_STUDENT_ID_DESC + PROJECT_STATUS_DESC_AMY,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + INVALID_PROJECT_STATUS_DESC,
                ProjectStatus.MESSAGE_CONSTRAINTS);
    }
}
