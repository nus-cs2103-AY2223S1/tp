package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GE3238_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.OpenLinkCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.link.Link;

public class OpenLinkCommandParserTest {
    private OpenLinkCommandParser parser = new OpenLinkCommandParser();

    @Test
    public void parse_missingPartsLinkAlias_failure() {
        assertParseFailure(parser, " " + PREFIX_MODULE_CODE + VALID_GE3238_MODULE_CODE,
                OpenLinkCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, OpenLinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsEmptyModuleCode_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_MODULE_CODE,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsEmptyModuleAlias_throwsParseException() {
        assertParseFailure(parser, VALID_MODULE_CODE_WITH_PREFIX + " "
                        + PREFIX_MODULE_LINK_ALIAS + " ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    @Test
    public void parse_invalidArgsOnlyWhiteSpace_throwsParseException() {
        assertParseFailure(parser, VALID_MODULE_CODE_WITH_PREFIX + " "
                        + PREFIX_MODULE_LINK_ALIAS + "            ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    //Specific testing of link alias validity performed at ParserUtilTest and LinkTest
    //Integration testing of OpenLinkCommandParser with ParserUtil
    @Test
    public void parse_validLinkAliases_success() {
        String moduleCodeString = VALID_MODULE_CODE_WITH_PREFIX;
        String moduleAliases = " " + PREFIX_MODULE_LINK_ALIAS + VALID_MODULE_LINK_ALIAS
                + " " + PREFIX_MODULE_LINK_ALIAS + VALID_MODULE_LINK_ALIAS_2;
        String userInput = moduleCodeString + moduleAliases;

        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE);
        List<String> expectedLinkAliases = Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2);
        OpenLinkCommand expectedCommand = new OpenLinkCommand(moduleCode, expectedLinkAliases);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
