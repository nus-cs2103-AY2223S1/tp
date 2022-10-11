package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFINSURANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.staff.Staff;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalStaff {

    public static final Staff NEVER = new StaffBuilder().withStaffName("Alice Pauline")
            .withStaffContact("94351253").withStaffTitle("Project Manager")
            .withStaffInsurance("true").withStaffDepartment("Human resources")
            .withTags("friends").build();
    public static final Staff GONNA = new StaffBuilder().withStaffName("Benson Meier")
            .withStaffContact("98765432").withStaffTitle("Senior Software Engineer")
            .withStaffInsurance("false").withStaffDepartment("IT Department")
            .withTags("owesMoney", "friends").build();
    public static final Staff GIVE = new StaffBuilder().withStaffName("Coconut")
            .withStaffContact("95352563").withStaffTitle("Software Engineer")
            .withStaffInsurance("true").withStaffDepartment("IT Department")
            .build();
    public static final Staff YOU = new StaffBuilder().withStaffName("Daniel Meier")
            .withStaffContact("87652533").withStaffTitle("UX Designer")
            .withStaffInsurance("true").withStaffDepartment("IT Department")
            .withTags("friends").build();
    public static final Staff UP = new StaffBuilder().withStaffName("Elle Meyer")
            .withStaffContact("9482224").withStaffTitle("Senior UX Designer")
            .withStaffInsurance("false").withStaffDepartment("IT Department")
            .build();
    public static final Staff SOMEBODY = new StaffBuilder().withStaffName("Fiona Kunz")
            .withStaffContact("9482427").withStaffTitle("Marketing Manager")
            .withStaffInsurance("true").withStaffDepartment("Marketing Department")
            .build();
    public static final Staff SHREK = new StaffBuilder().withStaffName("George Best")
            .withStaffContact("9482442").withStaffTitle("Senior Marketing Manager")
            .withStaffInsurance("false").withStaffDepartment("Marketing Department")
            .build();

    // Manually added
    public static final Staff HOON = new StaffBuilder().withStaffName("Hungry")
            .withStaffContact("8482424").withStaffTitle("Hello").withStaffDepartment("Any department")
            .withStaffInsurance("true").build();
    public static final Staff IDA = new StaffBuilder().withStaffName("Ida Mueller")
            .withStaffContact("8482131").withStaffTitle("Bye").withStaffDepartment("This department")
            .withStaffInsurance("false").build();

    // Manually added - Staff's details found in {@code CommandTestUtil}
    public static final Staff AMY = new StaffBuilder().withStaffName(VALID_STAFFNAME_AMY)
            .withStaffContact(VALID_STAFFCONTACT_AMY).withStaffTitle(VALID_STAFFTITLE_AMY)
            .withStaffDepartment(VALID_STAFFDEPARTMENT_AMY).withStaffInsurance(VALID_STAFFINSURANCE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Staff BOB = new StaffBuilder().withStaffName(VALID_STAFFNAME_BOB)
            .withStaffContact(VALID_STAFFCONTACT_BOB).withStaffTitle(VALID_STAFFTITLE_BOB)
            .withStaffDepartment(VALID_STAFFDEPARTMENT_BOB).withStaffInsurance(VALID_STAFFINSURANCE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_HUNGRY = "Hungry"; // A keyword that matches HUNGRY

    private TypicalStaff() {
    } // prevents instantiation

    public static List<Staff> getTypicalStaff() {
        return new ArrayList<>(Arrays.asList(NEVER, GONNA, GIVE, YOU, UP, SOMEBODY, SHREK));
    }
}
