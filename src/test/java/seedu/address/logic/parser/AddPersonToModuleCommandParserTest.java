package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA2001;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonToModuleCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Name;

public class AddPersonToModuleCommandParserTest {
    private AddPersonToModuleCommandParser parser = new AddPersonToModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleCode expectedModuleCode = new ModuleCode(VALID_MA2001_MODULE_CODE);
        Name expectedName = new Name(VALID_NAME_AMY);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + MODULE_CODE_DESC_MA2001 + NAME_DESC_AMY,
                new AddPersonToModuleCommand(expectedModuleCode, expectedName));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2106 + NAME_DESC_AMY + MODULE_CODE_DESC_MA2001,
                new AddPersonToModuleCommand(expectedModuleCode, expectedName));

        // multiple names - last name accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_MA2001 + NAME_DESC_BOB + NAME_DESC_AMY,
                new AddPersonToModuleCommand(expectedModuleCode, expectedName));

        // order of arguments should not matter
        assertParseSuccess(parser, MODULE_CODE_DESC_MA2001 + NAME_DESC_AMY,
                new AddPersonToModuleCommand(expectedModuleCode, expectedName));
        assertParseSuccess(parser, NAME_DESC_AMY + MODULE_CODE_DESC_MA2001,
                new AddPersonToModuleCommand(expectedModuleCode, expectedName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonToModuleCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, VALID_CS2106_MODULE_CODE + NAME_DESC_AMY, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, MODULE_CODE_DESC_CS2106 + VALID_NAME_AMY, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_CS2106_MODULE_CODE + VALID_NAME_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + NAME_DESC_AMY,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, MODULE_CODE_DESC_CS2106 + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only invalid module code reported
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + INVALID_NAME_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_CS2106 + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonToModuleCommand.MESSAGE_USAGE));
    }
}
