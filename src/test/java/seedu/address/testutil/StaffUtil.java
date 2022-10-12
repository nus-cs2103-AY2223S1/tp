package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddStaffCommand;
import seedu.address.model.staff.Staff;

/**
 * A utility class for test cases.
 */
public class StaffUtil {
    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddCommand(Staff staff) {
        //todo
        return AddStaffCommand.COMMAND_WORD + " " + getStaffDetails(staff);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getStaffDetails(Staff staff) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_STAFF_NAME + staff.getStaffName().toString() + " ");
        sb.append(PREFIX_STAFF_CONTACT + staff.getStaffContact().toString() + " ");
        sb.append(PREFIX_STAFF_TITLE + staff.getStaffTitle().toString() + " ");
        sb.append(PREFIX_STAFF_DEPARTMENT + staff.getStaffDepartment().toString() + " ");
        sb.append(PREFIX_STAFF_INSURANCE + staff.getStaffInsurance().toString() + " ");
        staff.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditStaffDescriptorDetails() {
        //todo
        return new String();
    }
}
