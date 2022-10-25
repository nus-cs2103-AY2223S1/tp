package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.OpenLinkCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.link.Link;

import java.util.Arrays;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GE3238_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class OpenLinkCommandParserTest {
    private OpenLinkCommandParser parser = new OpenLinkCommandParser();

    @Test
    public void parse_missingParts_failure() {
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
        assertParseFailure(parser, " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE + " "
                        + PREFIX_MODULE_LINK_ALIAS + " ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    @Test
    public void parse_invalidArgsOnlyWhiteSpace_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE + " "
                        + PREFIX_MODULE_LINK_ALIAS + "            ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    //Specific testing of link alias validity performed at ParserUtilTest and LinkTest ==========
    //Integration testing of OpenLinkCommandParser with ParserUtil
    @Test
    public void parse_links_success() {
        String moduleCodeString = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE;
        String moduleAliases = " " + PREFIX_MODULE_LINK_ALIAS + VALID_MODULE_LINK_ALIAS
                + " " + PREFIX_MODULE_LINK_ALIAS + VALID_MODULE_LINK_ALIAS_2;
        String userInput = moduleCodeString + moduleAliases;

        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE);
        List<String> expectedLinkAliases = Arrays.asList(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_ALIAS_2);
        OpenLinkCommand expectedCommand = new OpenLinkCommand(moduleCode, expectedLinkAliases);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
