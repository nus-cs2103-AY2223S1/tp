package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_TP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUPNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_TP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.testutil.GroupBuilder;

public class AddGroupCommandParserTest {
    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new GroupBuilder().withName(VALID_GROUPNAME_TP).withMembers().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUPNAME_DESC_TP, new AddGroupCommand(expectedGroup));

        // multiple names - last name accepted
        assertParseSuccess(parser, GROUPNAME_DESC_TP + GROUPNAME_DESC_TP, new AddGroupCommand(expectedGroup));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_GROUPNAME_TP, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_GROUPNAME_DESC, GroupName.MESSAGE_CONSTRAINTS);
    }

}
