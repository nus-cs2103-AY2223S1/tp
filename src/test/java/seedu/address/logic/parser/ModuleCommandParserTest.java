package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CURRENT_MODULE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.CURRENT_MODULE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CURRENT_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_TO_REMOVE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLANNED_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREVIOUS_MODULE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TO_REMOVE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TO_REMOVE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PLANNED_MODULE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PLANNED_MODULE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.PREVIOUS_MODULE_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PREVIOUS_MODULE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_6;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_8;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ModuleCommand;
import seedu.address.logic.commands.ModuleIndexCommand;
import seedu.address.logic.commands.ModuleUserCommand;
import seedu.address.model.module.Module;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class ModuleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCommand.MESSAGE_USAGE);

    private ModuleCommandParser parser = new ModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // index no field specified
        assertParseFailure(parser, "1", ModuleCommand.MESSAGE_NOT_EDITED);

        // user no field specified
        assertParseFailure(parser, "user", ModuleCommand.MESSAGE_NOT_EDITED);

        // no index/user and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 n/Bobby McBobface", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_indexInvalidValue_failure() {
        // invalid current module name
        assertParseFailure(parser, "1" + INVALID_CURRENT_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid previous module name
        assertParseFailure(parser, "1" + INVALID_PREVIOUS_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid planned module name
        assertParseFailure(parser, "1" + INVALID_PLANNED_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid module to remove name
        assertParseFailure(parser, "1" + INVALID_MODULE_TO_REMOVE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid previous module followed by valid current module
        assertParseFailure(parser, "1" + INVALID_PREVIOUS_MODULE_DESC + CURRENT_MODULE_DESC_1,
                Module.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_userInvalidValue_failure() {
        // invalid current module name
        assertParseFailure(parser, "user" + INVALID_CURRENT_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid previous module name
        assertParseFailure(parser, "user" + INVALID_PREVIOUS_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid planned module name
        assertParseFailure(parser, "user" + INVALID_PLANNED_MODULE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid module to remove name
        assertParseFailure(parser, "user" + INVALID_MODULE_TO_REMOVE_DESC, Module.MESSAGE_CONSTRAINTS);

        // invalid previous module followed by valid current module
        assertParseFailure(parser, "user" + INVALID_PREVIOUS_MODULE_DESC + CURRENT_MODULE_DESC_1,
                Module.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_indexAllFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + CURRENT_MODULE_DESC_1 + CURRENT_MODULE_DESC_2
                + PREVIOUS_MODULE_DESC_1 + PREVIOUS_MODULE_DESC_2 + PLANNED_MODULE_DESC_1 + PLANNED_MODULE_DESC_2
                + MODULE_TO_REMOVE_DESC_1 + MODULE_TO_REMOVE_DESC_2;

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3, VALID_MODULE_4)
                .withPlannedModules(VALID_MODULE_6, VALID_MODULE_5)
                .withModToRemove(VALID_MODULE_7, VALID_MODULE_8).build();
        ModuleIndexCommand expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_userAllFieldsSpecified_success() {
        String userInput = "user" + CURRENT_MODULE_DESC_1 + CURRENT_MODULE_DESC_2 + PREVIOUS_MODULE_DESC_1
                + PREVIOUS_MODULE_DESC_2 + PLANNED_MODULE_DESC_1 + PLANNED_MODULE_DESC_2 + MODULE_TO_REMOVE_DESC_1
                + MODULE_TO_REMOVE_DESC_2;

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_1, VALID_MODULE_2)
                .withPreviousModules(VALID_MODULE_3, VALID_MODULE_4)
                .withPlannedModules(VALID_MODULE_6, VALID_MODULE_5)
                .withModToRemove(VALID_MODULE_7, VALID_MODULE_8).build();
        ModuleUserCommand expectedCommand = new ModuleUserCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSomeFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CURRENT_MODULE_DESC_1 + PLANNED_MODULE_DESC_1;

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_1).withPlannedModules(VALID_MODULE_5).build();
        ModuleIndexCommand expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_userSomeFieldsSpecified_success() {
        String userInput = "user" + PREVIOUS_MODULE_DESC_1 + MODULE_TO_REMOVE_DESC_1;

        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withPreviousModules(VALID_MODULE_3).withModToRemove(VALID_MODULE_7).build();
        ModuleUserCommand expectedCommand = new ModuleUserCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexOneFieldSpecified_success() {
        // current module
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + CURRENT_MODULE_DESC_2;
        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_2).build();
        ModuleIndexCommand expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // previous module
        userInput = targetIndex.getOneBased() + PREVIOUS_MODULE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withPreviousModules(VALID_MODULE_4).build();
        expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // planned module
        userInput = targetIndex.getOneBased() + PLANNED_MODULE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withPlannedModules(VALID_MODULE_6).build();
        expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module to remove
        userInput = targetIndex.getOneBased() + MODULE_TO_REMOVE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withModToRemove(VALID_MODULE_8).build();
        expectedCommand = new ModuleIndexCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_userOneFieldSpecified_success() {
        // current module
        String userInput = "user" + CURRENT_MODULE_DESC_2;
        ModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCurrentModules(VALID_MODULE_2).build();
        ModuleUserCommand expectedCommand = new ModuleUserCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // previous module
        userInput = "user" + PREVIOUS_MODULE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withPreviousModules(VALID_MODULE_4).build();
        expectedCommand = new ModuleUserCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // planned module
        userInput = "user" + PLANNED_MODULE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withPlannedModules(VALID_MODULE_6).build();
        expectedCommand = new ModuleUserCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module to remove
        userInput = "user" + MODULE_TO_REMOVE_DESC_2;
        descriptor = new EditModuleDescriptorBuilder().withModToRemove(VALID_MODULE_8).build();
        expectedCommand = new ModuleUserCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
