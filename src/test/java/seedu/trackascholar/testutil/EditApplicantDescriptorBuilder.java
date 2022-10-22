package seedu.trackascholar.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.trackascholar.logic.commands.EditCommand;
import seedu.trackascholar.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.Email;
import seedu.trackascholar.model.applicant.Name;
import seedu.trackascholar.model.applicant.Phone;
import seedu.trackascholar.model.applicant.Scholarship;
import seedu.trackascholar.model.tag.Tag;

/**
 * A utility class to help with building EditApplicantDescriptor objects.
 */
public class EditApplicantDescriptorBuilder {

    private EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditCommand.EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicantDescriptor} with fields containing {@code applicant}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setScholarship(applicant.getScholarship());
        descriptor.setApplicationStatus(applicant.getApplicationStatus());
        descriptor.setTags(applicant.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Scholarship} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withScholarship(String name) {
        descriptor.setScholarship(new Scholarship(name));
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withApplicationStatus(String applicationStatus) {
        descriptor.setApplicationStatus(new ApplicationStatus(applicationStatus));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public EditApplicantDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditApplicantDescriptor build() {
        return descriptor;
    }
}
