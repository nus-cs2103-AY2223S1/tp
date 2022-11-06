package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFCONTACT_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFDEPARTMENT_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFLEAVE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFNAME_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFTITLE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFCONTACT_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFCONTACT_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFLEAVE_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFLEAVE_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFNAME_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFNAME_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFTITLE_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFTITLE_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFTITLE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalProjects.BOB;
import static seedu.hrpro.testutil.TypicalStaff.STAFF_JAY;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.AddStaffCommand;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

/**
 * Contains test cases for AddStaffCommandParser.
 */
public class AddStaffCommandParserTest {

    private AddStaffCommandParser parser = new AddStaffCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        Staff expectedStaff = new StaffBuilder(STAFF_JAY).withTags(VALID_TAG_FRIEND).build();
        Project projectStud = new ProjectBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                        + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        // multiple staff names - last name accepted
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_ANDY + STAFFNAME_DESC_JAY
                + STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        // multiple contacts - last contact accepted
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_ANDY
                + STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        // multiple titles - last title accepted
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_ANDY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        // multiple department - last department accepted
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_ANDY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        // multiple leave status - last leave status accepted
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_ANDY + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, INDEX_FIRST_PROJECT));

        //multiple tag ok
        Staff expectedStaffWithMultipleTags =
                new StaffBuilder(STAFF_JAY).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND ,
                new AddStaffCommand(expectedStaffWithMultipleTags, INDEX_FIRST_PROJECT));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Staff expectedStaffWithNoTags = new StaffBuilder(STAFF_JAY).withTags().build();
        Project projectStud = new ProjectBuilder(BOB).build();

        //no tags ok
        assertParseSuccess(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY ,
                new AddStaffCommand(expectedStaffWithNoTags, INDEX_FIRST_PROJECT));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        //Missing index field
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFNAME_DESC_JAY
                        + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff name
        assertParseFailure(parser, " 1 " + STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff contact
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFLEAVE_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff department
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY, expectedMessage);

        //Missing staff title
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFLEAVE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff leave
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        //Invalid index
        assertParseFailure(parser, " 0 " + STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFNAME_DESC_JAY + STAFFDEPARTMENT_DESC_JAY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));

        //Invalid staff name
        assertParseFailure(parser, " 1 " + STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + INVALID_STAFFNAME_DESC, StaffName.MESSAGE_CONSTRAINTS);

        //Invalid staff contact
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + INVALID_STAFFCONTACT_DESC, StaffContact.MESSAGE_CONSTRAINTS);

        //Invalid staff department
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY
                + INVALID_STAFFDEPARTMENT_DESC, StaffDepartment.MESSAGE_CONSTRAINTS);

        //Invalid staff title
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + INVALID_STAFFTITLE_DESC, StaffTitle.MESSAGE_CONSTRAINTS);

        //Invalid staff LEAVE
        assertParseFailure(parser, " 1 " + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + INVALID_STAFFLEAVE_DESC, StaffLeave.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        //Missing project name prefix
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFNAME_DESC_JAY + VALID_NAME_BOB
                + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff name prefix
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFNAME_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff contact prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFLEAVE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFCONTACT_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff department prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFTITLE_DESC_JAY + VALID_STAFFDEPARTMENT_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff title prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFLEAVE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFTITLE_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff leave prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFLEAVE_JAY
                + NAME_DESC_BOB, expectedMessage);
    }
}
