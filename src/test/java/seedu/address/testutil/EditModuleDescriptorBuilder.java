package seedu.address.testutil;

import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.assignmentdetails.AssignmentDetails;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class to help with building EditModuleDescriptor objects.
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = new EditModuleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        descriptor = new EditModuleDescriptor();
        descriptor.setModuleCode(module.getModuleCode());
        descriptor.setLecture(module.getLectureDetails());
        descriptor.setTutorial(module.getTutorialDetails());
        descriptor.setLectureZoomLink(module.getLectureZoomLink());
        descriptor.setTutorialZoomLink(module.getTutorialZoomLink());
        descriptor.setAssignments(module.getAssignmentDetails());
    }

    /**
     * Sets the {@code Module Code} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code Lecture Details} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withLectureDetails(String lectureDetails) {
        descriptor.setLecture(new LectureDetails(lectureDetails));
        return this;
    }

    /**
     * Sets the {@code Tutorial Details} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withTutorialDetails(String tutorialDetails) {
        descriptor.setTutorial(new TutorialDetails(tutorialDetails));
        return this;
    }

    /**
     * Sets the {@code Lecture Zoom Link} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withLectureZoomLink(String lectureZoomLink) {
        descriptor.setLectureZoomLink(new ZoomLink(lectureZoomLink));
        return this;
    }

    /**
     * Sets the {@code Lecture Zoom Link} of the {@code EditModuleDescriptor} that we are building.
     */
    public EditModuleDescriptorBuilder withTutorialZoomLink(String tutorialZoomLink) {
        descriptor.setTutorialZoomLink(new ZoomLink(tutorialZoomLink));
        return this;
    }

    /**
     * Parses the {@code assignments} into a {@code Set<AssignmentDetails>} and set it to the {@code EditModuleDescriptor}
     * that we are building.
     */
    public EditModuleDescriptorBuilder withAssignmentDetails(String... assignments) {
        Set<AssignmentDetails> assignmentSet = Stream.of(assignments).map(AssignmentDetails::new).collect(Collectors.toSet());
        descriptor.setAssignments(assignmentSet);
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
