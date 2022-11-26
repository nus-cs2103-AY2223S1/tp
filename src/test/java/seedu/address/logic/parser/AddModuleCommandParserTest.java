package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_allFieldsPresentAndValid_success() {
        String correctArguments = " c/cs2105 m/networking mc/4";
        Module module = new ModuleBuilder().withModuleCode("cs2105")
                .withModuleCredit(4).withModuleName("networking").build();
        assertParseSuccess(parser, correctArguments, new AddModuleCommand(module));
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleCommand.MESSAGE_USAGE);

        //Missing module code prefix
        String missingModuleCode = " cs2105 m/networking mc/4";
        assertParseFailure(parser, missingModuleCode, expectedMessage);

        //Missing module name prefix
        String missingModuleName = " c/cs2105 networking mc/4";
        assertParseFailure(parser, missingModuleName, expectedMessage);

        //Missing module credit prefix
        String missingModuleCredit = " c/cs2105 m/networking 4";
        assertParseFailure(parser, missingModuleCredit, expectedMessage);
    }

    @Test
    public void parse_invalidInputs_failure() {
        //Empty module code
        String emptyModuleCode = " c/ m/networking mc/4";
        assertParseFailure(parser, emptyModuleCode, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //Empty module name
        String emptyModuleName = " c/cs2105 m/ mc/4";
        assertParseFailure(parser, emptyModuleName, ModuleName.MODULE_NAME_CONSTRAINTS);

        //Empty module credit
        String emptyModuleCredit = " c/cs2105 m/networking mc/";
        assertParseFailure(parser, emptyModuleCredit, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

        //Module code with less than 6 characters
        String moduleCodeLessThan6Char = " c/a m/networking mc/4";
        assertParseFailure(parser, moduleCodeLessThan6Char, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //Module code with invalid characters
        String moduleCodeInvalidChar = " c/##@absa m/networking mc/5";
        assertParseFailure(parser, moduleCodeInvalidChar, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //Module code with invalid first two characters
        String moduleCodeInvalidFirstTwoChar = " c/1234567 m/networking mc/7";
        assertParseFailure(parser, moduleCodeInvalidFirstTwoChar, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //Module Credit less than 0
        String moduleCreditLessThanZero = " c/cs2100 m/computer organisation mc/-1";
        assertParseFailure(parser, moduleCreditLessThanZero, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);

        //Module Credit greater than 0
        String moduleCreditExtremelyLarge = " c/cs2100 m/computer organisation "
                + "mc/9999999999999999999999999";
        assertParseFailure(parser, moduleCreditExtremelyLarge, ModuleCredit.MODULE_CREDIT_CONSTRAINTS);
    }
}
