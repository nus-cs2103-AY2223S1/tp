package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TASKDEADLINE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TASKDESCRIPTION_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDEADLINE_DESC_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.TASKDESCRIPTION_DESC_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_BRAVO;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditTaskCommand;
import seedu.hrpro.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASKDESCRIPTION_DESC_ALPHA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASKDESCRIPTION_DESC_ALPHA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASKDESCRIPTION_DESC,
                TaskDescription.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_TASKDEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS); // invalid deadline

        //invalid deadline followed by valid description
        assertParseFailure(parser, "1" + INVALID_TASKDEADLINE_DESC
                + VALID_TASKDESCRIPTION_ALPHA, Deadline.MESSAGE_CONSTRAINTS);

        // valid description followed by invalid description. The test case for
        // invalid description followed by valid description is tested at
        // {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TASKDESCRIPTION_DESC_ALPHA
                + INVALID_TASKDEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASKDESCRIPTION_DESC
                + INVALID_TASKDEADLINE_DESC, TaskDescription.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASKDESCRIPTION_DESC_ALPHA
                + TASKDEADLINE_DESC_ALPHA;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA)
                .withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TASKDESCRIPTION_DESC_ALPHA;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TASKDESCRIPTION_DESC_ALPHA;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + TASKDEADLINE_DESC_ALPHA;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_TASKDEADLINE_ALPHA).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TASKDEADLINE_DESC_ALPHA
                + TASKDESCRIPTION_DESC_ALPHA + TASKDESCRIPTION_DESC_BRAVO + TASKDEADLINE_DESC_BRAVO;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_BRAVO)
                .withDeadline(VALID_TASKDEADLINE_BRAVO)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TASKDESCRIPTION_DESC
                + TASKDESCRIPTION_DESC_ALPHA;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TASKDEADLINE_DESC_BRAVO
                + INVALID_TASKDESCRIPTION_DESC + TASKDESCRIPTION_DESC_ALPHA;
        descriptor = new EditTaskDescriptorBuilder()
                .withDescription(VALID_TASKDESCRIPTION_ALPHA)
                .withDeadline(VALID_TASKDEADLINE_BRAVO)
                .build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
