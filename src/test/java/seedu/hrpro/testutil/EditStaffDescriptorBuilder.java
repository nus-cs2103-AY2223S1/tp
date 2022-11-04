package seedu.hrpro.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.hrpro.logic.commands.EditStaffCommand;
import seedu.hrpro.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.model.tag.Tag;
/**
 * A utility class to help with building EditStaffDescriptor objects.
 */
public class EditStaffDescriptorBuilder {

    private EditStaffCommand.EditStaffDescriptor descriptor;

    public EditStaffDescriptorBuilder() {
        descriptor = new EditStaffDescriptor();
    }

    public EditStaffDescriptorBuilder(EditStaffDescriptor descriptor) {
        this.descriptor = new EditStaffDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStaffDescriptor} with fields containing {@code Staff}'s
     * details.
     */
    public EditStaffDescriptorBuilder(Staff staff) {
        descriptor = new EditStaffDescriptor();
        descriptor.setStaffName(staff.getStaffName());
        descriptor.setStaffContact(staff.getStaffContact());
        descriptor.setStaffDepartment(staff.getStaffDepartment());
        descriptor.setStaffTitle(staff.getStaffTitle());
        descriptor.setStaffLeave(staff.getStaffLeave());
        descriptor.setTags(staff.getTags());
    }

    /**
     * Sets the {@code StaffName} of the {@code EditStaffDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withName(String name) {
        descriptor.setStaffName(new StaffName(name));
        return this;
    }

    /**
     * Sets the {@code StaffTitle} of the {@code EditStaffDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withTitle(String title) {
        descriptor.setStaffTitle(new StaffTitle(title));
        return this;
    }

    /**
     * Sets the {@code StaffLeave} of the {@code EditStaffDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withLeave(String leave) {
        descriptor.setStaffLeave(new StaffLeave(leave));
        return this;
    }

    /**
     * Sets the {@code StaffDepartment} of the {@code EditStaffDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withDepartment(String department) {
        descriptor.setStaffDepartment(new StaffDepartment(department));
        return this;
    }

    /**
     * Sets the {@code StaffContact} of the {@code EditStaffDescriptor} that we are building.
     */
    public EditStaffDescriptorBuilder withContact(String contact) {
        descriptor.setStaffContact(new StaffContact(contact));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStaffDescriptor}
     * that we are building.
     */
    public EditStaffDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStaffDescriptor build() {
        return descriptor;
    }

}
