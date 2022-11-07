package seedu.hrpro.testutil;

import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.hrpro.logic.commands.AddStaffCommand;
import seedu.hrpro.logic.commands.EditStaffCommand;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.tag.Tag;

/**
 * A utility class for test cases.
 */
public class StaffUtil {
    /**
     * Returns an add staff command string for adding the {@code staff}.
     */
    public static String getAddCommand(Staff staff) {
        return AddStaffCommand.COMMAND_WORD + " 1 " + getStaffDetails(staff);
    }

    /**
     * Returns the part of command string for the given {@code staff}'s details.
     */
    public static String getStaffDetails(Staff staff) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STAFF_NAME + staff.getStaffName().toString() + " ");
        sb.append(PREFIX_STAFF_CONTACT + staff.getStaffContact().toString() + " ");
        sb.append(PREFIX_STAFF_TITLE + staff.getStaffTitle().toString() + " ");
        sb.append(PREFIX_STAFF_DEPARTMENT + staff.getStaffDepartment().toString() + " ");
        sb.append(PREFIX_STAFF_LEAVE + staff.getStaffLeave().toString() + " ");
        staff.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditStaffDescriptorDetails(EditStaffCommand.EditStaffDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();

        descriptor.getStaffName().ifPresent(staffName -> sb.append(PREFIX_STAFF_NAME)
                .append(staffName).append(" "));

        descriptor.getStaffDepartment().ifPresent(staffDepartment -> sb.append(PREFIX_STAFF_DEPARTMENT)
                .append(staffDepartment).append(" "));

        descriptor.getStaffContact().ifPresent(staffContact -> sb.append(PREFIX_STAFF_CONTACT)
                .append(staffContact).append(" "));

        descriptor.getStaffLeave().ifPresent(staffLeave -> sb.append(PREFIX_STAFF_LEAVE)
                .append(staffLeave).append(" "));

        descriptor.getStaffTitle().ifPresent(staffTitle -> sb.append(PREFIX_STAFF_TITLE)
                .append(staffTitle).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        return sb.toString();
    }
}
