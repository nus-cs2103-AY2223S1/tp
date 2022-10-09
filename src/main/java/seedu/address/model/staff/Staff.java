package seedu.address.model.staff;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Staff member in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Staff {

    // Identity fields
    private final StaffName name;
    private final StaffContact contact;
    private final StaffTitle title;
    private final StaffDepartment department;
    private final StaffInsurance insurance;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Staff(StaffName name, StaffContact contact, StaffTitle title,
                 StaffDepartment department, StaffInsurance insurance, Set<Tag> tags) {
        requireAllNonNull(name, contact, title, department, insurance, tags);
        this.name = name;
        this.contact = contact;
        this.title = title;
        this.department = department;
        this.insurance = insurance;
        this.tags.addAll(tags);
    }

    public StaffName getStaffName() {
        return name;
    }

    public StaffContact getStaffContact() {
        return contact;
    }

    public StaffTitle getStaffTitle() {
        return title;
    }

    public StaffDepartment getStaffDepartment() {
        return department;
    }

    public StaffInsurance getStaffInsurance() {
        return insurance;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both staff members have the same name.
     * This defines a weaker notion of equality between two staff.
     */
    public boolean isSameStaff(seedu.address.model.staff.Staff otherStaff) {
        if (otherStaff == this) {
            return true;
        }

        return otherStaff != null
                && otherStaff.getStaffName().equals(getStaffName());
    }

    /**
     * Returns true if both staff members have the same identity and data fields.
     * This defines a stronger notion of equality between two staff.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.staff.Staff)) {
            return false;
        }

        seedu.address.model.staff.Staff otherStaff = (seedu.address.model.staff.Staff) other;
        return otherStaff.getStaffName().equals(getStaffName())
                && otherStaff.getStaffContact().equals(getStaffContact())
                && otherStaff.getStaffTitle().equals(getStaffTitle())
                && otherStaff.getStaffDepartment().equals(getStaffDepartment())
                && otherStaff.getStaffInsurance().equals(getStaffInsurance())
                && otherStaff.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, contact, title, department, insurance, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStaffName())
                .append("; Staff Contact: ")
                .append(getStaffContact())
                .append("; Staff Title: ")
                .append(getStaffTitle())
                .append("; Staff Department: ")
                .append(getStaffDepartment())
                .append("; Staff Insurance: ")
                .append(getStaffInsurance());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

