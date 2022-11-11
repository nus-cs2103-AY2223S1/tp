package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private EditCommand.EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditCommand.EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditCommand.EditInternshipDescriptor descriptor) {
        this.descriptor = new EditCommand.EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditCommand.EditInternshipDescriptor();
        descriptor.setCompany(internship.getCompany());
        descriptor.setLink(internship.getLink());
        descriptor.setApplicationStatus(internship.getApplicationStatus());
        descriptor.setDescription(internship.getDescription());
        descriptor.setAppliedDate(internship.getAppliedDate());
        descriptor.setInterviewDateTime(internship.getInterviewDateTime());
        descriptor.setTags(internship.getTags());
    }

    /**
     * Sets the {@code Company} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withLink(String link) {
        descriptor.setLink(new Link(link));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withDescription(String email) {
        descriptor.setDescription(new Description(email));
        return this;
    }

    /**
     * Sets the {@code AppliedDate} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withAppliedDate(String appliedDate) {
        descriptor.setAppliedDate(new AppliedDate(appliedDate));
        return this;
    }

    /**
     * Sets the {@code InterviewDateTime} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInterviewDateTime(String interviewDateTime) {
        descriptor.setInterviewDateTime(new InterviewDateTime(interviewDateTime));
        descriptor.setApplicationStatus(ApplicationStatus.Shortlisted);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditInternshipDescriptor}
     * that we are building.
     */
    public EditInternshipDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditInternshipDescriptor build() {
        return descriptor;
    }
}
