package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_STAFF_NAME = "Thomas Train";
    public static final String DEFAULT_STAFF_CONTACT = "91234567";
    public static final String DEFAULT_STAFF_TITLE = "Senior Software Engineer";
    public static final String DEFAULT_STAFF_INSURANCE = "true";
    public static final String DEFAULT_STAFF_DEPARTMENT = "IT Department";

    private StaffName staffName;
    private StaffContact staffContact;
    private StaffTitle staffTitle;
    private StaffInsurance staffInsurance;
    private StaffDepartment staffDepartment;
    private Set<Tag> tags;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        staffName = new StaffName(DEFAULT_STAFF_NAME);
        staffContact = new StaffContact(DEFAULT_STAFF_CONTACT);
        staffTitle = new StaffTitle(DEFAULT_STAFF_TITLE);
        staffInsurance = new StaffInsurance(DEFAULT_STAFF_INSURANCE);
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
        staffInsurance = staffToCopy.getStaffInsurance();
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
    public StaffBuilder withStaffContact(String budget) {
        this.staffContact = new StaffContact(budget);
        return this;
    }

    /**
     * Sets the {@code StaffDepartment} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffDepartment(String deadline) {
        this.staffDepartment = new StaffDepartment(deadline);
        return this;
    }

    /**
     * Sets the {@code StaffInsurance} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffInsurance(String deadline) {
        this.staffInsurance = new StaffInsurance(deadline);
        return this;
    }

    /**
     * Sets the {@code StaffTitle} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffTitle(String deadline) {
        this.staffTitle = new StaffTitle(deadline);
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
        return new Staff(staffName, staffContact, staffTitle, staffDepartment, staffInsurance, tags);
    }

}
