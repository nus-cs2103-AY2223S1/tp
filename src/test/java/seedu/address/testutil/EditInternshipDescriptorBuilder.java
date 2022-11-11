package seedu.address.testutil;

import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {

    private final EditInternshipCommand.EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipCommand.EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditInternshipCommand.EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipCommand.EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipCommand.EditInternshipDescriptor();
        descriptor.setCompanyName(internship.getCompanyName());
        descriptor.setInternshipRole(internship.getInternshipRole());
        descriptor.setInternshipStatus(internship.getInternshipStatus());
        descriptor.setInterviewDate(internship.getInterviewDate());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code InternshipRole} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInternshipRole(String role) {
        descriptor.setInternshipRole(new InternshipRole(role));
        return this;
    }

    /**
     * Sets the {@code InternshipStatus} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInternshipStatus(String internshipStatus) {
        descriptor.setInternshipStatus(new InternshipStatus(InternshipStatus.State.valueOf(internshipStatus)));
        return this;
    }

    /**
     * Sets the {@code InterviewDate} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInterviewDate(String interviewDate) {
        descriptor.setInterviewDate(new InterviewDate(interviewDate));
        return this;
    }

    public EditInternshipCommand.EditInternshipDescriptor build() {
        return descriptor;
    }
}
