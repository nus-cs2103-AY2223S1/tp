package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CREDIT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CREDIT_ONE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CREDIT_TWO;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CREDIT_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CREDIT_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_TWO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, MODULE_CREDIT_ONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditModuleCommand.MESSAGE_NO_FIELDS_PROVIDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no prefix
        assertParseFailure(parser, "1 " + VALID_MODULE_CREDIT_ONE, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-3" + MODULE_NAME_ONE, MESSAGE_INVALID_MODULE_INDEX);

        // zero index
        assertParseFailure(parser, "0" + MODULE_NAME_ONE, MESSAGE_INVALID_MODULE_INDEX);

        // positive signed index
        assertParseFailure(parser, "+3", MESSAGE_INVALID_MODULE_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "2 random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "2 p/string", MESSAGE_INVALID_FORMAT);

        // number bigger than max integer
        assertParseFailure(parser, "9999999999999999999999", MESSAGE_INVALID_MODULE_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {

        //one field
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE, ModuleCode.MODULE_CODE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_MODULE_CREDIT, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_MODULE_NAME, ModuleName.MODULE_NAME_CONSTRAINTS);

        //two fields
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE
                + MODULE_NAME_ONE, ModuleCode.MODULE_CODE_CONSTRAINTS);
        assertParseFailure(parser, "1" + MODULE_NAME_ONE
                + INVALID_MODULE_CREDIT, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

        //invalid module code followed by valid module credit and module name
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE
                + MODULE_CREDIT_ONE + MODULE_NAME_ONE, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //invalid module credit followed by valid module code and module name
        assertParseFailure(parser, "1" + INVALID_MODULE_CREDIT
                + MODULE_CODE_ONE + MODULE_NAME_ONE, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

        // valid module name and module credit followed by invalid module code.
        assertParseFailure(parser, "1" + MODULE_NAME_ONE
                + MODULE_CREDIT_ONE + INVALID_MODULE_CODE, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //valid module name and module code followed by invalid module credit
        assertParseFailure(parser, "1" + MODULE_NAME_ONE
                + MODULE_CODE_ONE + INVALID_MODULE_CREDIT, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

        //valid module credit, then invalid module code followed by module name
        assertParseFailure(parser, "1" + MODULE_CREDIT_ONE
                + INVALID_MODULE_CODE + MODULE_NAME_ONE, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //valid module code, then invalid module credit then module name
        assertParseFailure(parser, "1" + MODULE_CODE_ONE
                + INVALID_MODULE_CREDIT + MODULE_NAME_ONE, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_ONE + MODULE_NAME_ONE + MODULE_CREDIT_ONE;

        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_ONE)
                        .withModuleName(VALID_MODULE_NAME_ONE).withModuleCredit(VALID_MODULE_CREDIT_ONE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_ONE + MODULE_CREDIT_ONE;

        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MODULE_CODE_ONE)
                .withModuleCredit(VALID_MODULE_CREDIT_ONE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecifiedTestTwo_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_NAME_ONE + MODULE_CREDIT_ONE;

        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleName(VALID_MODULE_NAME_ONE)
                .withModuleCredit(VALID_MODULE_CREDIT_ONE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // module code
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_ONE;
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MODULE_CODE_ONE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module name
        userInput = targetIndex.getOneBased() + MODULE_NAME_ONE;
        descriptor = new EditModuleDescriptorBuilder()
                .withModuleName(VALID_MODULE_NAME_ONE).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module credit
        userInput = targetIndex.getOneBased() + MODULE_CREDIT_ONE;
        descriptor = new EditModuleDescriptorBuilder()
                .withModuleCredit(VALID_MODULE_CREDIT_ONE).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased()
                + MODULE_CODE_ONE + MODULE_NAME_ONE + MODULE_CREDIT_ONE
                + MODULE_CODE_TWO + MODULE_NAME_TWO + MODULE_CREDIT_TWO;

        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_TWO)
                        .withModuleName(VALID_MODULE_NAME_TWO).withModuleCredit(VALID_MODULE_CREDIT_TWO).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // one invalid value followed by one valid value
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased()
                + INVALID_MODULE_CODE + MODULE_CODE_ONE;
        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_ONE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // one invalid value in the middle of multiple valid values
        userInput = targetIndex.getOneBased() + MODULE_CODE_ONE
                + INVALID_MODULE_CREDIT + MODULE_NAME_ONE
                + MODULE_CREDIT_ONE;
        descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_ONE)
                .withModuleName(VALID_MODULE_NAME_ONE).withModuleCredit(VALID_MODULE_CREDIT_ONE).build();

        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
