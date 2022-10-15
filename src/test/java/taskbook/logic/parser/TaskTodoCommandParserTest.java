package taskbook.logic.parser;

import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_TO_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_TO_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_STUDY;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_WORK;
import static taskbook.logic.commands.CommandTestUtil.INVALID_ASSIGN_FROM_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_ASSIGN_TO_DESC;
import static taskbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STUDY;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WORK;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.parser.tasks.TaskTodoCommandParser;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskTodoCommandParserTest {

    private TaskTodoCommandParser parser = new TaskTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedNameAmy = new Name(VALID_NAME_AMY);
        Name expectedNameBob = new Name(VALID_NAME_BOB);
        Description expectedDescriptionWork = new Description(VALID_DESCRIPTION_WORK);
        Description expectedDescriptionEat = new Description(VALID_DESCRIPTION_STUDY);
        Assignment expectedAssignmentFrom = Assignment.FROM;
        Assignment expectedAssignmentTo = Assignment.TO;


        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK,
                new TaskTodoCommand(expectedNameAmy, expectedDescriptionWork, expectedAssignmentFrom));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ASSIGN_TO_DESC_AMY + DESCRIPTION_DESC_WORK,
                new TaskTodoCommand(expectedNameAmy, expectedDescriptionWork, expectedAssignmentTo));

        // multiple names - last name accepted
        assertParseSuccess(parser, ASSIGN_FROM_DESC_AMY + ASSIGN_FROM_DESC_BOB + DESCRIPTION_DESC_WORK,
                new TaskTodoCommand(expectedNameBob, expectedDescriptionWork, expectedAssignmentFrom));

        assertParseSuccess(parser, ASSIGN_TO_DESC_AMY + ASSIGN_TO_DESC_BOB + DESCRIPTION_DESC_WORK,
                new TaskTodoCommand(expectedNameBob, expectedDescriptionWork, expectedAssignmentTo));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DESCRIPTION_DESC_STUDY,
                new TaskTodoCommand(expectedNameAmy, expectedDescriptionEat, expectedAssignmentFrom));

        assertParseSuccess(parser, ASSIGN_TO_DESC_AMY + DESCRIPTION_DESC_WORK + DESCRIPTION_DESC_STUDY,
                new TaskTodoCommand(expectedNameAmy, expectedDescriptionEat, expectedAssignmentTo));
    }

    @Test
    public void parse_doubleAssignmentFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TaskTodoCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                ASSIGN_FROM_DESC_AMY + ASSIGN_TO_DESC_BOB + DESCRIPTION_DESC_WORK,
                expectedMessage);

        assertParseFailure(parser,
                ASSIGN_TO_DESC_BOB + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TaskTodoCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + DESCRIPTION_DESC_WORK, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, ASSIGN_FROM_DESC_AMY + VALID_DESCRIPTION_WORK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_ASSIGN_FROM_DESC + DESCRIPTION_DESC_WORK, Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_ASSIGN_TO_DESC + DESCRIPTION_DESC_WORK, Name.MESSAGE_CONSTRAINTS);
    }
}
