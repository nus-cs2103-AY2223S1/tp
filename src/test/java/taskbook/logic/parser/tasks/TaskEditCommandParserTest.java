package taskbook.logic.parser.tasks;

import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_TO_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.DATE_DESC_1999;
import static taskbook.logic.commands.CommandTestUtil.DATE_DESC_2022;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_STUDY;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_WORK;
import static taskbook.logic.commands.CommandTestUtil.VALID_DATE_1999;
import static taskbook.logic.commands.CommandTestUtil.VALID_DATE_2022;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STUDY;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WORK;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.commands.tasks.TaskEditCommand.MESSAGE_ASSIGNOR_ASSIGNEE;
import static taskbook.logic.commands.tasks.TaskEditCommand.MESSAGE_NOT_EDITED;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static taskbook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.commons.core.index.Index;
import taskbook.logic.commands.tasks.TaskEditCommand;
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.enums.Assignment;
import taskbook.testutil.EditTaskDescriptorBuilder;
import taskbook.testutil.TypicalIndexes;

public class TaskEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE);

    private final TaskEditCommandParser parser = new TaskEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " i/1", MESSAGE_NOT_EDITED);

        // no index, no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // both assign to and assign from is specified (and vice versa)
        Index index = TypicalIndexes.INDEX_FIRST_TASK;
        String userInput = indexAsInput(index) + ASSIGN_FROM_DESC_AMY + ASSIGN_TO_DESC_BOB;
        assertParseFailure(parser, userInput, MESSAGE_ASSIGNOR_ASSIGNEE);
        userInput = indexAsInput(index) + ASSIGN_TO_DESC_BOB + ASSIGN_FROM_DESC_AMY;
        assertParseFailure(parser, userInput, MESSAGE_ASSIGNOR_ASSIGNEE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index index = TypicalIndexes.INDEX_FIRST_TASK;
        String userInput = indexAsInput(index) + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .withAssignment(Assignment.FROM)
            .withDescription(VALID_DESCRIPTION_WORK)
            .withDate(VALID_DATE_2022)
            .build();
        TaskEditCommand expectedCommand = new TaskEditCommand(index, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index index = TypicalIndexes.INDEX_SECOND_TASK;
        String userInput = indexAsInput(index) + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .withAssignment(Assignment.FROM)
            .withDescription(VALID_DESCRIPTION_WORK)
            .build();
        TaskEditCommand expectedCommand = new TaskEditCommand(index, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // assigned to
        Index index = TypicalIndexes.INDEX_THIRD_TASK;
        String userInput = indexAsInput(index) + ASSIGN_TO_DESC_BOB;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_BOB)
            .withAssignment(Assignment.TO)
            .build();
        TaskEditCommand expectedCommand = new TaskEditCommand(index, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // assigned from
        userInput = indexAsInput(index) + ASSIGN_FROM_DESC_BOB;
        descriptor = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_BOB)
            .withAssignment(Assignment.FROM)
            .build();
        expectedCommand = new TaskEditCommand(index, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = indexAsInput(index) + DESCRIPTION_DESC_STUDY;
        descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_STUDY)
            .build();
        expectedCommand = new TaskEditCommand(index, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = indexAsInput(index) + DATE_DESC_1999;
        descriptor = new EditTaskDescriptorBuilder()
            .withDate(VALID_DATE_1999)
            .build();
        expectedCommand = new TaskEditCommand(index, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_negativeIndex_throwsException() {
        assertParseFailure(parser, " i/-5", MESSAGE_INVALID_INDEX);
    }

    private String indexAsInput(Index index) {
        return " i/" + index.getOneBased();
    }
}
