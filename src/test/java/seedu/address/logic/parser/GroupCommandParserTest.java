package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.PersonContainsKeywords;

public class GroupCommandParserTest {

    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // not alpha numeric
        assertParseFailure(parser, "..,.,", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        // has spaces
        assertParseFailure(parser, "a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGroupCommand() {
        GroupCommand expectedGroupCommand =
            new GroupCommand(new Group("groupName"));
        assertParseSuccess(parser, "groupName", expectedGroupCommand);
    }

}
