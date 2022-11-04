package seedu.hrpro.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.model.util.SampleDataUtil;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_STAFF_NAME = "Thomas Train";
    public static final String DEFAULT_STAFF_CONTACT = "91234567";
    public static final String DEFAULT_STAFF_TITLE = "Senior Software Engineer";
    public static final String DEFAULT_STAFF_LEAVE = "true";
    public static final String DEFAULT_STAFF_DEPARTMENT = "IT Department";

    private StaffName staffName;
    private StaffContact staffContact;
    private StaffTitle staffTitle;
    private StaffLeave staffLeave;
    private StaffDepartment staffDepartment;
    private Set<Tag> tags;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        staffName = new StaffName(DEFAULT_STAFF_NAME);
        staffContact = new StaffContact(DEFAULT_STAFF_CONTACT);
        staffTitle = new StaffTitle(DEFAULT_STAFF_TITLE);
        staffLeave = new StaffLeave(DEFAULT_STAFF_LEAVE);
        staffDepartment = new StaffDepartment(DEFAULT_STAFF_DEPARTMENT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        staffName = staffToCopy.getStaffName();
        staffContact = staffToCopy.getStaffContact();
        staffTitle = staffToCopy.getStaffTitle();
        staffLeave = staffToCopy.getStaffLeave();
        staffDepartment = staffToCopy.getStaffDepartment();
        tags = new HashSet<>(staffToCopy.getTags());
    }

    /**
     * Sets the {@code StaffName} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffName(String name) {
        this.staffName = new StaffName(name);
        return this;
    }


    /**
     * Sets the {@code StaffContact} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffContact(String contact) {
        this.staffContact = new StaffContact(contact);
        return this;
    }

    /**
     * Sets the {@code StaffDepartment} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffDepartment(String department) {
        this.staffDepartment = new StaffDepartment(department);
        return this;
    }

    /**
     * Sets the {@code StaffLeave} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffLeave(String leaveStatus) {
        this.staffLeave = new StaffLeave(leaveStatus);
        return this;
    }

    /**
     * Sets the {@code StaffTitle} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffTitle(String title) {
        this.staffTitle = new StaffTitle(title);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Staff build() {
        return new Staff(staffName, staffContact, staffTitle, staffDepartment, staffLeave, tags);
    }

}
