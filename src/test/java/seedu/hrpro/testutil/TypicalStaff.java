package seedu.hrpro.testutil;

import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFLEAVE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFTITLE_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFTITLE_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hrpro.model.staff.Staff;

/**
 * A utility class containing a list of {@code Staff} objects to be used in tests.
 */
public class TypicalStaff {

    public static final Staff STAFF_1 = new StaffBuilder().withStaffName("Alice Pauline")
            .withStaffContact("94351253").withStaffTitle("Project Manager")
            .withStaffLeave("true").withStaffDepartment("Human resources")
            .withTags("friends").build();
    public static final Staff STAFF_2 = new StaffBuilder().withStaffName("Benson Meier")
            .withStaffContact("98765432").withStaffTitle("Senior Software Engineer")
            .withStaffLeave("false").withStaffDepartment("IT Department")
            .withTags("owesMoney", "friends").build();
    public static final Staff STAFF_3 = new StaffBuilder().withStaffName("Coconut")
            .withStaffContact("95352563").withStaffTitle("Software Engineer")
            .withStaffLeave("true").withStaffDepartment("IT Department")
            .build();
    public static final Staff STAFF_4 = new StaffBuilder().withStaffName("Daniel Meier")
            .withStaffContact("87652533").withStaffTitle("UX Designer")
            .withStaffLeave("true").withStaffDepartment("IT Department")
            .withTags("friends").build();
    public static final Staff STAFF_5 = new StaffBuilder().withStaffName("Elle Meyer")
            .withStaffContact("64822246").withStaffTitle("Senior UX Designer")
            .withStaffLeave("false").withStaffDepartment("IT Department")
            .build();
    public static final Staff STAFF_6 = new StaffBuilder().withStaffName("Fiona Kunz")
            .withStaffContact("94824271").withStaffTitle("Marketing Manager")
            .withStaffLeave("true").withStaffDepartment("Marketing Department")
            .build();
    public static final Staff STAFF_7 = new StaffBuilder().withStaffName("George Best")
            .withStaffContact("94824426").withStaffTitle("Senior Marketing Manager")
            .withStaffLeave("false").withStaffDepartment("Marketing Department")
            .build();

    // Manually added
    public static final Staff STAFF_HOON = new StaffBuilder().withStaffName("Hungry")
            .withStaffContact("84824245").withStaffTitle("Hello").withStaffDepartment("Any department")
            .withStaffLeave("true").build();
    public static final Staff STAFF_IDA = new StaffBuilder().withStaffName("Ida Mueller")
            .withStaffContact("84821315").withStaffTitle("Bye").withStaffDepartment("This department")
            .withStaffLeave("false").build();

    // Manually added - Staff's details found in {@code CommandTestUtil}
    public static final Staff STAFF_ANDY = new StaffBuilder().withStaffName(VALID_STAFFNAME_ANDY)
            .withStaffContact(VALID_STAFFCONTACT_ANDY).withStaffTitle(VALID_STAFFTITLE_ANDY)
            .withStaffDepartment(VALID_STAFFDEPARTMENT_ANDY).withStaffLeave(VALID_STAFFLEAVE_ANDY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Staff STAFF_JAY = new StaffBuilder().withStaffName(VALID_STAFFNAME_JAY)
            .withStaffContact(VALID_STAFFCONTACT_JAY).withStaffTitle(VALID_STAFFTITLE_JAY)
            .withStaffDepartment(VALID_STAFFDEPARTMENT_JAY).withStaffLeave(VALID_STAFFLEAVE_JAY)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_HUNGRY = "Hungry"; // A keyword that matches HUNGRY

    private TypicalStaff() {
    } // prevents instantiation

    public static List<Staff> getTypicalStaff() {
        return new ArrayList<>(Arrays.asList(STAFF_1, STAFF_2, STAFF_3, STAFF_4, STAFF_5, STAFF_6, STAFF_7));
    }
}
