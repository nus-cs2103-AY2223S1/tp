package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_WATCH_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DO_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WATCH_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.task.TaskDescription;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains unit tests for {@code EditCommandParser}.
 */
public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, MODULE_DESC_CS2030, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NO_FIELDS_PROVIDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MODULE_DESC_CS2030, MESSAGE_INVALID_TASK_INDEX);

        // zero index
        assertParseFailure(parser, "0" + MODULE_DESC_CS2030, MESSAGE_INVALID_TASK_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC, ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, "1" + INVALID_TASK_DESCRIPTION_DESC,
            TaskDescription.DESCRIPTION_CONSTRAINTS);

        // invalid module followed by valid description
        assertParseFailure(parser, "1" + INVALID_MODULE_DESC + DESCRIPTION_DESC_DO_TUTORIAL,
            ModuleCode.MODULE_CODE_CONSTRAINTS);

        // valid module followed by invalid module
        assertParseFailure(parser, "1" + MODULE_DESC_CS2030 + INVALID_MODULE_DESC,
            ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid module and invalid description, but only the invalid module is captured
        assertParseFailure(parser, "1" + INVALID_TASK_DESCRIPTION_DESC + INVALID_MODULE_DESC,
            ModuleCode.MODULE_CODE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + MODULE_DESC_CS2030 + DESCRIPTION_DESC_DO_TUTORIAL;

        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, DESC_TUTORIAL);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // only description specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_WATCH_LECTURE;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_WATCH_LECTURE).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // only module specified
        userInput = targetIndex.getOneBased() + MODULE_DESC_CS2040;
        descriptor = new EditTaskDescriptorBuilder()
            .withModule(VALID_MODULE_CS2040).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + MODULE_DESC_CS2030 + DESCRIPTION_DESC_DO_TUTORIAL
            + MODULE_DESC_CS2040 + DESCRIPTION_DESC_WATCH_LECTURE;

        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, DESC_LECTURE);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TASK_DESCRIPTION_DESC + DESCRIPTION_DESC_DO_TUTORIAL;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
            .withDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + MODULE_DESC_CS2030 + INVALID_TASK_DESCRIPTION_DESC
            + DESCRIPTION_DESC_DO_TUTORIAL;
        expectedCommand = new EditTaskCommand(targetIndex, DESC_TUTORIAL);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
