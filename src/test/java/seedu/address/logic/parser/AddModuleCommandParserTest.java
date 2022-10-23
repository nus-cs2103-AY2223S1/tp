package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TITLE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_TITLE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(MA2001).withModuleTitle(VALID_MA2001_MODULE_TITLE).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_MA2001 + MODULE_TITLE_DESC_MA2001,
                new AddModuleCommand(expectedModule));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2106 + MODULE_CODE_DESC_MA2001
                + MODULE_TITLE_DESC_MA2001, new AddModuleCommand(expectedModule));

        // multiple module titles - last module title accepted
        assertParseSuccess(parser, MODULE_TITLE_DESC_CS2106 + MODULE_CODE_DESC_MA2001
                + MODULE_TITLE_DESC_MA2001, new AddModuleCommand(expectedModule));

        // order of arguments should not matter
        assertParseSuccess(parser, MODULE_CODE_DESC_MA2001 + MODULE_TITLE_DESC_MA2001,
                new AddModuleCommand(expectedModule));
        assertParseSuccess(parser, MODULE_TITLE_DESC_MA2001 + MODULE_CODE_DESC_MA2001,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no module title
        Module expectedModule = new ModuleBuilder(CS2106).withModuleTitle("").build();
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2106, new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, VALID_CS2106_MODULE_CODE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CS2106_MODULE_CODE + VALID_CS2106_MODULE_TITLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + MODULE_TITLE_DESC_MA2001,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_MA2001 + MODULE_TITLE_DESC_MA2001,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
