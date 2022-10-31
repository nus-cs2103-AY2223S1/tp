package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_ADD_STUDENTS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DELETE_STUDENTS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_STUDENTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_STUDENT_ADAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.testutil.EditTaskDescriptorBuilder;

class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_TITLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // only one specified
        assertParseFailure(parser, INDEX_FIRST_TASK + VALID_TASK_TITLE, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_TITLE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 a/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecifiedToDo_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_TITLE_DESC + TASK_DESCRIPTION_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedDeadline_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_TITLE_DESC + TASK_DESCRIPTION_DESC
                + DEADLINE_DATE_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).withDate(VALID_DEADLINE_DATE).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedAssignmentAddStudents_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_TITLE_DESC + TASK_DESCRIPTION_DESC
                + ASSIGNMENT_ADD_STUDENTS_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).withAddStudents(VALID_ASSIGNMENT_STUDENTS).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedAssignmentDeleteStudents_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_TITLE_DESC + TASK_DESCRIPTION_DESC
                + ASSIGNMENT_ADD_STUDENTS_DESC + ASSIGNMENT_DELETE_STUDENTS_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).withAddStudents(VALID_ASSIGNMENT_STUDENTS)
                .withDeleteStudents(VALID_ASSIGNMENT_STUDENT_ADAM).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
