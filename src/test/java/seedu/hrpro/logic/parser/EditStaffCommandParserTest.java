package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFCONTACT_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFDEPARTMENT_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFLEAVE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFNAME_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_STAFFTITLE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFCONTACT_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFDEPARTMENT_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFLEAVE_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFLEAVE_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFNAME_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFNAME_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFFTITLE_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFF_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFTITLE_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditStaffCommand;
import seedu.hrpro.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.testutil.EditStaffDescriptorBuilder;
import seedu.hrpro.testutil.StaffUtil;

/**
 * Contains test cases for EditStaffCommandParser.
 */
public class EditStaffCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStaffCommand.MESSAGE_USAGE);

    private EditStaffCommandParser parser = new EditStaffCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_AMY + STAFFNAME_DESC_ANDY, MESSAGE_INVALID_FORMAT);

        // no project name specified
        assertParseFailure(parser, "1" + STAFFNAME_DESC_ANDY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1" + NAME_DESC_AMY, EditStaffCommand.MESSAGE_NOT_EDITED);

        // no index and field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 " + INVALID_NAME_DESC,
                ProjectName.MESSAGE_CONSTRAINTS); // invalid project name
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFNAME_DESC,
                StaffName.MESSAGE_CONSTRAINTS); // invalid staff name
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFCONTACT_DESC,
                StaffContact.MESSAGE_CONSTRAINTS); // invalid contact
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFDEPARTMENT_DESC,
                StaffDepartment.MESSAGE_CONSTRAINTS); // invalid department
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFLEAVE_DESC,
                StaffLeave.MESSAGE_CONSTRAINTS); // invalid leave
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFTITLE_DESC,
                StaffTitle.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tags

        // invalid staff name followed by valid staff department
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFNAME_DESC + VALID_STAFFDEPARTMENT_ANDY,
                StaffName.MESSAGE_CONSTRAINTS);

        // valid staff name followed by invalid staff name
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + VALID_STAFFNAME_ANDY + INVALID_STAFFNAME_DESC,
                StaffName.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Staff} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + NAME_DESC_AMY + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + NAME_DESC_AMY + TAG_DESC_FRIEND
                + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + NAME_DESC_AMY + TAG_EMPTY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first is captured
        assertParseFailure(parser, "1 " + NAME_DESC_AMY + INVALID_STAFFNAME_DESC
            + INVALID_STAFFTITLE_DESC + INVALID_STAFFCONTACT_DESC + INVALID_STAFFDEPARTMENT_DESC
            + INVALID_STAFFLEAVE_DESC + INVALID_TAG_DESC, StaffName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        ProjectName validAmy = new ProjectName(VALID_NAME_AMY);
        Index targetIndex = INDEX_FIRST_PROJECT;

        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + " "
                + StaffUtil.getEditStaffDescriptorDetails(STAFF_DESC_ANDY);

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(STAFF_DESC_ANDY).build();
        EditStaffCommand command = new EditStaffCommand(validAmy, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

    }

    @Test
    public void parse_someOptionalFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        ProjectName validProjectName = new ProjectName(VALID_NAME_AMY);

        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + STAFFNAME_DESC_JAY + STAFFDEPARTMENT_DESC_JAY;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_JAY)
                .withDepartment(VALID_STAFFDEPARTMENT_JAY).build();
        EditStaffCommand command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

    }

    @Test
    public void parse_oneFieldSpecified_success() {

        Index targetIndex = INDEX_FIRST_PROJECT;
        ProjectName validProjectName = new ProjectName(VALID_NAME_AMY);
        String userInputStart = targetIndex.getOneBased() + NAME_DESC_AMY;

        // staff name
        String userInput = userInputStart + STAFFNAME_DESC_ANDY;
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY).build();
        EditStaffCommand command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // staff contact
        userInput = userInputStart + STAFFCONTACT_DESC_ANDY;
        descriptor = new EditStaffDescriptorBuilder().withContact(VALID_STAFFCONTACT_ANDY).build();
        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // staff department
        userInput = userInputStart + STAFFDEPARTMENT_DESC_ANDY;
        descriptor = new EditStaffDescriptorBuilder().withDepartment(VALID_STAFFDEPARTMENT_ANDY).build();
        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // staff title
        userInput = userInputStart + STAFFTITLE_DESC_ANDY;
        descriptor = new EditStaffDescriptorBuilder().withTitle(VALID_STAFFTITLE_ANDY).build();
        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // staff leave
        userInput = userInputStart + STAFFLEAVE_DESC_ANDY;
        descriptor = new EditStaffDescriptorBuilder().withLeave(VALID_STAFFLEAVE_ANDY).build();
        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // tags
        userInput = userInputStart + TAG_DESC_FRIEND;
        descriptor = new EditStaffDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        ProjectName validProjectName = new ProjectName(VALID_NAME_AMY);

        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY
                + STAFFNAME_DESC_ANDY + STAFFLEAVE_DESC_ANDY + TAG_DESC_FRIEND
                + STAFFNAME_DESC_JAY + STAFFLEAVE_DESC_JAY;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withName(VALID_STAFFNAME_JAY).withLeave(VALID_STAFFLEAVE_JAY)
                .withTags(VALID_TAG_FRIEND).build();


        EditStaffCommand command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PROJECT;
        ProjectName validProjectName = new ProjectName(VALID_NAME_AMY);

        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + INVALID_STAFFNAME_DESC + STAFFNAME_DESC_ANDY;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY).build();
        EditStaffCommand command = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, command);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY + INVALID_STAFFNAME_DESC
                + STAFFNAME_DESC_ANDY + STAFFCONTACT_DESC_ANDY + STAFFDEPARTMENT_DESC_ANDY
                + STAFFTITLE_DESC_ANDY + STAFFLEAVE_DESC_ANDY;

        descriptor = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY)
                .withContact(VALID_STAFFCONTACT_ANDY).withTitle(VALID_STAFFTITLE_ANDY)
                .withDepartment(VALID_STAFFDEPARTMENT_ANDY).withLeave(VALID_STAFFLEAVE_ANDY).build();

        command = new EditStaffCommand(validProjectName, targetIndex, descriptor);

        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        ProjectName validProjectName = new ProjectName(VALID_NAME_AMY);
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + TAG_EMPTY;
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withTags().build();
        EditStaffCommand expectedCommand = new EditStaffCommand(validProjectName, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
