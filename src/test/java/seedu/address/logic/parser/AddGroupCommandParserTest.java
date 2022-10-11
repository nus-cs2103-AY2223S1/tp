package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;

public class AddGroupCommandParserTest {
    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_groupFormat_success() {
        Group expectedGroup = new Group(VALID_GROUP_NAME, VALID_GROUP_NAME);
        assertParseSuccess(parser, VALID_GROUP_NAME, new AddGroupCommand(expectedGroup));
    }

    @Test
    public void parse_groupFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        Group group = new Group(INVALID_GROUP_NAME, INVALID_GROUP_NAME);
        assertParseFailure(parser, INVALID_GROUP_NAME, expectedMessage);
    }
}
