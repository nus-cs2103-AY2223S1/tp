package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFCONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFDEPARTMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFINSURANCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFFTITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STAFFCONTACT_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFCONTACT_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFINSURANCE_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFINSURANCE_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFNAME_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFNAME_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFTITLE_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFFTITLE_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProjects.BOB;
import static seedu.address.testutil.TypicalStaff.STAFF_JAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandParserTest {

    private AddStaffCommandParser parser = new AddStaffCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        Staff expectedStaff = new StaffBuilder(STAFF_JAY).withTags(VALID_TAG_FRIEND).build();
        Project projectStud = new ProjectBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + NAME_DESC_BOB
                        + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        // multiple staff names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_ANDY + STAFFNAME_DESC_JAY
                + STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        // multiple contacts - last contact accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_ANDY
                + STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        // multiple titles - last title accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_ANDY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        // multiple department - last department accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_ANDY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        // multiple insurance status - last insurance status accepted
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_ANDY + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND, new AddStaffCommand(expectedStaff, projectStud.getProjectName()));

        //multiple tag ok
        Staff expectedStaffWithMultipleTags =
                new StaffBuilder(STAFF_JAY).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND ,
                new AddStaffCommand(expectedStaffWithMultipleTags, projectStud.getProjectName()));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Staff expectedStaffWithNoTags = new StaffBuilder(STAFF_JAY).withTags().build();
        Project projectStud = new ProjectBuilder(BOB).build();

        //no tags ok
        assertParseSuccess(parser, NAME_DESC_BOB + STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY ,
                new AddStaffCommand(expectedStaffWithNoTags, projectStud.getProjectName()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        //Missing project name field
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFNAME_DESC_JAY
                        + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff name
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff contact
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFINSURANCE_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                        + NAME_DESC_BOB, expectedMessage);

        //Missing staff department
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY
                        + NAME_DESC_BOB, expectedMessage);

        //Missing staff title
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFINSURANCE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                        + NAME_DESC_BOB, expectedMessage);

        //Missing staff insurance
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                        + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY
                        + NAME_DESC_BOB, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        //Invalid project name field
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + INVALID_NAME_DESC + STAFFNAME_DESC_JAY
                + STAFFDEPARTMENT_DESC_JAY, ProjectName.MESSAGE_CONSTRAINTS);

        //Invalid staff name
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + INVALID_STAFFNAME_DESC
                + NAME_DESC_BOB, StaffName.MESSAGE_CONSTRAINTS);

        //Invalid staff contact
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + INVALID_STAFFCONTACT_DESC
                + NAME_DESC_BOB, StaffContact.MESSAGE_CONSTRAINTS);

        //Invalid staff department
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + INVALID_STAFFDEPARTMENT_DESC
                + NAME_DESC_BOB, StaffDepartment.MESSAGE_CONSTRAINTS);

        //Invalid staff title
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + INVALID_STAFFTITLE_DESC
                + NAME_DESC_BOB, StaffTitle.MESSAGE_CONSTRAINTS);

        //Invalid staff insurance
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + INVALID_STAFFINSURANCE_DESC
                + NAME_DESC_BOB, StaffInsurance.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE);

        //Missing project name prefix
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFNAME_DESC_JAY + VALID_NAME_BOB
                + STAFFDEPARTMENT_DESC_JAY, expectedMessage);

        //Missing staff name prefix
        assertParseFailure(parser, STAFFCONTACT_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFNAME_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff contact prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFINSURANCE_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFCONTACT_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff department prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFTITLE_DESC_JAY + VALID_STAFFDEPARTMENT_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff title prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFINSURANCE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFTITLE_JAY
                + NAME_DESC_BOB, expectedMessage);

        //Missing staff insurance prefix
        assertParseFailure(parser, STAFFNAME_DESC_JAY + STAFFCONTACT_DESC_JAY
                + STAFFTITLE_DESC_JAY + STAFFDEPARTMENT_DESC_JAY + VALID_STAFFINSURANCE_JAY
                + NAME_DESC_BOB, expectedMessage);
    }
}
