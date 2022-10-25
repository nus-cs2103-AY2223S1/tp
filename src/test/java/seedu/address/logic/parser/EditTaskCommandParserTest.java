package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_PREF;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LAB_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_FINISH_TP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_LAB_2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_NAME_FINISH_TP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_NAME_DESC_FINISH_TP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_NAME_DESC_FINISH_TP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_PREF, TaskName.MESSAGE_CONSTRAINTS); // invalid taskname
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, Module.MESSAGE_CONSTRAINTS); // invalid module
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        // invalid module followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC + DEADLINE_DESC_FINISH_TP,
                Module.MESSAGE_CONSTRAINTS);

        //valid module followed by invalid deadline
        assertParseFailure(parser, "1" + MODULE_DESC_FINISH_TP + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_PREF + INVALID_MODULE_DESC + INVALID_DEADLINE_DESC,
                TaskName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_FINISH_TP + DEADLINE_DESC_FINISH_TP
                + MODULE_DESC_FINISH_TP;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_FINISH_TP)
                .withDeadline(VALID_DEADLINE_FINISH_TP).withModule(VALID_MODULE_FINISH_TP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + MODULE_DESC_FINISH_TP + DEADLINE_DESC_FINISH_TP;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_FINISH_TP)
                .withDeadline(VALID_DEADLINE_FINISH_TP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_FINISH_TP;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_FINISH_TP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //module
        userInput = targetIndex.getOneBased() + MODULE_DESC_FINISH_TP;
        descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_FINISH_TP).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_FINISH_TP;
        descriptor = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_FINISH_TP).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_FINISH_TP + MODULE_DESC_LAB_2
                + DEADLINE_DESC_FINISH_TP + DEADLINE_DESC_LAB_2 + DEADLINE_DESC_FINISH_TP + TASK_NAME_DESC_LAB_2
                + MODULE_DESC_FINISH_TP + MODULE_DESC_LAB_2;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_LAB_2)
                .withModule(VALID_MODULE_LAB_2).withDeadline(VALID_DEADLINE_FINISH_TP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_MODULE_DESC + MODULE_DESC_FINISH_TP;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_FINISH_TP).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TASK_NAME_DESC_FINISH_TP + INVALID_MODULE_DESC + MODULE_DESC_FINISH_TP
                + DEADLINE_DESC_FINISH_TP;
        descriptor = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_FINISH_TP)
                .withModule(VALID_MODULE_FINISH_TP).withDeadline(VALID_DEADLINE_FINISH_TP).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
