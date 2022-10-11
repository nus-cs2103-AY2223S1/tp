package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLinkCommand;
import seedu.address.model.link.Link;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;

public class DeleteLinkCommandParserTest {
    private DeleteLinkCommandParser parser = new DeleteLinkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, "1", DeleteLinkCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLinkCommand.MESSAGE_USAGE));
    }

    //Specific testing of link compatability performed at ParserUtilTest
    //Integration testing of AddLinkCommandParser with ParserUtil
    @Test
    public void parse_links_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_LINK_CS2103T;

        Set<Link> expectedLinks = new HashSet<Link>(Arrays.asList(new Link(VALID_MODULE_LINK)));
        DeleteLinkCommand expectedCommand = new DeleteLinkCommand(targetIndex, expectedLinks);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
