package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STAFFNAME_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteStaffCommand;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.StaffName;

public class DeleteStaffParserTest {

    private DeleteStaffParser parser = new DeleteStaffParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        StaffName staffName = new StaffName(VALID_STAFFNAME_JAY);
        ProjectName projectName = new ProjectName(VALID_NAME_BOB);
        String userInput = STAFFNAME_DESC_JAY + NAME_DESC_BOB;
        assertParseSuccess(parser, userInput, new DeleteStaffCommand(staffName, projectName));
    }

    @Test
    public void parse_missingCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE);

        //Missing project name field
        assertParseFailure(parser, STAFFNAME_DESC_JAY, expectedMessage);

        //Missing staff name field
        assertParseFailure(parser, NAME_DESC_BOB, expectedMessage);

    }

    @Test
    public void parse_missingCompulsoryPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE);

        //Missing project name prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + VALID_NAME_BOB, expectedMessage);

        //Missing staff name prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STAFFNAME_JAY, expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //Invalid project name
        assertParseFailure(parser, STAFFNAME_DESC_JAY + INVALID_NAME_DESC, ProjectName.MESSAGE_CONSTRAINTS);

        //Invalid staff name
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STAFFNAME_DESC, StaffName.MESSAGE_CONSTRAINTS);
    }
}
