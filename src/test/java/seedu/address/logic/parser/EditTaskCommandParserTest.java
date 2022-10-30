package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FIRST_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SECOND_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_FIRST_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_SECOND_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION_DESC_TWO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "m/cs2030", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NO_FIELDS_PROVIDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 m/cs2030", MESSAGE_INVALID_TASK_INDEX);

        // zero index
        assertParseFailure(parser, "0 m/cs2030", MESSAGE_INVALID_TASK_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, ModuleCode.MODULE_CODE_CONSTRAINTS); // invalid module
        assertParseFailure(parser, "1" + INVALID_TASK_DESCRIPTION_DESC, TaskDescription.DESCRIPTION_CONSTRAINTS); // invalid description

        // invalid module followed by valid description
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC + VALID_TASK_DESCRIPTION_DESC, ModuleCode.MODULE_CODE_CONSTRAINTS);

        // valid module followed by invalid module
        assertParseFailure(parser, "1" + VALID_MODULE_DESC + INVALID_MODULE_DESC, ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid module and invalid description, but only the invalid module is captured
        assertParseFailure(parser, "1" + INVALID_TASK_DESCRIPTION_DESC + INVALID_MODULE_DESC,
            ModuleCode.MODULE_CODE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + VALID_MODULE_DESC + VALID_TASK_DESCRIPTION_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_FIRST_TASK)
            .withDescription(VALID_DESCRIPTION_FIRST_TASK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // description
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + VALID_TASK_DESCRIPTION_DESC;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_FIRST_TASK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + VALID_MODULE_DESC;
        descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_FIRST_TASK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + VALID_MODULE_DESC + VALID_TASK_DESCRIPTION_DESC
            + VALID_MODULE_DESC_TWO + VALID_TASK_DESCRIPTION_DESC_TWO;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_SECOND_TASK)
            .withDescription(VALID_DESCRIPTION_SECOND_TASK)
            .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TASK_DESCRIPTION_DESC + VALID_TASK_DESCRIPTION_DESC;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_FIRST_TASK).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VALID_MODULE_DESC + INVALID_TASK_DESCRIPTION_DESC
            + VALID_TASK_DESCRIPTION_DESC;
        descriptor = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_FIRST_TASK)
            .withDescription(VALID_DESCRIPTION_FIRST_TASK).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
