package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.TaskCommandTestUtil.DEADLINE_DESC_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.DEADLINE_DESC_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_PROJECT_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.PROJECT_DESC_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.PROJECT_DESC_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.TITLE_DESC_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_DEADLINE_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_PROJECT_HOMEWORK;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_PROJECT_WORKSHOP;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_TITLE_HOMEWORK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Project;
import seedu.address.model.task.Title;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private final EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_HOMEWORK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_HOMEWORK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_HOMEWORK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);
        // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_PARSE_FAILURE);
        // invalid project
        assertParseFailure(parser, "1" + INVALID_PROJECT_DESC, Project.MESSAGE_CONSTRAINTS);

        // invalid deadline followed by valid project
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + PROJECT_DESC_HOMEWORK,
                Deadline.MESSAGE_PARSE_FAILURE);

        // valid project followed by invalid project. The test case for invalid project followed by valid project
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DEADLINE_DESC_WORKSHOP + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_PARSE_FAILURE);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_PROJECT_DESC + VALID_DEADLINE_HOMEWORK,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_WORKSHOP
            + PROJECT_DESC_HOMEWORK + TITLE_DESC_HOMEWORK;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_HOMEWORK)
            .withDeadline(VALID_DEADLINE_WORKSHOP).withProject(VALID_PROJECT_HOMEWORK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_WORKSHOP + PROJECT_DESC_HOMEWORK;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDeadline(VALID_DEADLINE_WORKSHOP).withProject(VALID_PROJECT_HOMEWORK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_HOMEWORK;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_HOMEWORK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_HOMEWORK;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_HOMEWORK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // project
        userInput = targetIndex.getOneBased() + PROJECT_DESC_HOMEWORK;
        descriptor = new EditTaskDescriptorBuilder().withProject(VALID_PROJECT_HOMEWORK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_HOMEWORK + PROJECT_DESC_HOMEWORK
            + DEADLINE_DESC_HOMEWORK + PROJECT_DESC_HOMEWORK + DEADLINE_DESC_WORKSHOP + PROJECT_DESC_WORKSHOP;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_WORKSHOP)
            .withProject(VALID_PROJECT_WORKSHOP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_WORKSHOP;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_WORKSHOP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + PROJECT_DESC_WORKSHOP + INVALID_DEADLINE_DESC + DEADLINE_DESC_WORKSHOP;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_WORKSHOP)
                .withProject(VALID_PROJECT_WORKSHOP).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
