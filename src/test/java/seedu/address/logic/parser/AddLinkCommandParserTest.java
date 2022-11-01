package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_WITH_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_LINK_URL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.link.Link;

public class AddLinkCommandParserTest {
    private AddLinkCommandParser parser = new AddLinkCommandParser();

    @Test
    public void parse_missingPartsLinkAliasAndUrl_failure() {
        assertParseFailure(parser,
                VALID_MODULE_CODE_WITH_PREFIX, AddLinkCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddLinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsEmptyModuleCode_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_MODULE_CODE, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingModuleCode_throwsParseException() {
        assertParseFailure(parser, VALID_MODULE_LINK_CS2103T, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddLinkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsEmptyModuleUrlAndAliasWithFlags_throwsParseException() {
        assertParseFailure(parser, VALID_MODULE_CODE_WITH_PREFIX + " "
                + PREFIX_MODULE_LINK_URL + " " + PREFIX_MODULE_LINK_ALIAS + " ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    //Specific testing of link alias/url validity performed at ParserUtilTest and LinkTest
    //Integration testing of AddLinkCommandParser with ParserUtil
    //Checks that link alias exception is thrown before link url exception.
    @Test
    public void parse_invalidArgsOnlyWhiteSpace_throwsParseException() {
        assertParseFailure(parser, VALID_MODULE_CODE_WITH_PREFIX + " "
                + PREFIX_MODULE_LINK_URL + "              " + PREFIX_MODULE_LINK_ALIAS + "            ",
                Link.MESSAGE_CONSTRAINTS_ALIAS);
    }

    //Integration testing of AddLinkCommandParser with ParserUtil
    @Test
    public void parse_validLinkUrlAndAlias_success() {
        String moduleCodeString = VALID_MODULE_CODE_WITH_PREFIX;
        String moduleLinksWithAlias = VALID_MODULE_LINK_CS2103T;
        String userInput = moduleCodeString + moduleLinksWithAlias;

        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE);
        Link expectedLink = new Link(VALID_MODULE_LINK_ALIAS, VALID_MODULE_LINK_URL);
        Set<Link> expectedLinks = new HashSet<Link>();
        expectedLinks.add(expectedLink);
        AddLinkCommand expectedCommand = new AddLinkCommand(moduleCode, expectedLinks);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
