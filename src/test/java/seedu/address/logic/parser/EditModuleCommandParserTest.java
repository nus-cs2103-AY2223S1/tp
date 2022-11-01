package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TITLE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TITLE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleCommandParser parser = new EditModuleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CS2106_MODULE_CODE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, MODULE_CODE_DESC_CS2106, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditModuleCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MODULE_CODE_DESC_CS2106, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + MODULE_CODE_DESC_CS2106, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid module code followed by valid module title
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC + MODULE_TITLE_DESC_CS2106,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // valid module code followed by invalid module code.
        // The test case for invalid module code followed by valid module code
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + MODULE_CODE_DESC_MA2001 + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC + MODULE_CODE_DESC_MA2001
                + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_MA2001 + MODULE_TITLE_DESC_CS2106;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MA2001_MODULE_CODE)
                .withModuleTitle(VALID_CS2106_MODULE_TITLE)
                .build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_TITLE_DESC_MA2001;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleTitle(VALID_MA2001_MODULE_TITLE)
                .build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // module code
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_CS2106;
        EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleCode(VALID_CS2106_MODULE_CODE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module title
        userInput = targetIndex.getOneBased() + MODULE_TITLE_DESC_CS2106;
        descriptor =
                new EditModuleDescriptorBuilder().withModuleTitle(VALID_CS2106_MODULE_TITLE).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased()
                + MODULE_CODE_DESC_CS2106 + MODULE_TITLE_DESC_CS2106
                + MODULE_CODE_DESC_CS2106 + MODULE_TITLE_DESC_CS2106
                + MODULE_CODE_DESC_MA2001 + MODULE_TITLE_DESC_MA2001;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MA2001_MODULE_CODE)
                .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + INVALID_MODULE_CODE_DESC + MODULE_CODE_DESC_MA2001;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MA2001_MODULE_CODE).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + MODULE_TITLE_DESC_MA2001 + INVALID_MODULE_CODE_DESC
                + MODULE_CODE_DESC_MA2001;
        descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_MA2001_MODULE_CODE)
                .withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
