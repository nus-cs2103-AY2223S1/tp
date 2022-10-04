package seedu.address.model.module;

// Module has a zoom link, tutorial venue and timing, lecture venue and timing, assignment name and deadline
// Add /m CS1101S /l I3-AUDI Monday 12:00 /t COM1 B1-103 Tuesday 14:00
// /a Functional Expressionism ONLY ONE WEEK /z https://www.zoom.sg/12891
// Module has ModuleCode, LectureDetails, TutorialDetails, AssignmentDetails, ZoomLink
// Only AssignmentDetails are optional, the rest should be provided by the user.

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.assignmentdetails.AssignmentDetails;

/**
 * Represents a Module in the address book
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleCode moduleCode;

    // Data fields
    private final LectureDetails lectureDetails;
    private final TutorialDetails tutorialDetails;
    private final ZoomLink zoomLink;
    private final Set<AssignmentDetails> assignmentDetails = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Module(ModuleCode moduleCode, LectureDetails lectureDetails,
                  TutorialDetails tutorialDetails, ZoomLink zoomLink, Set<AssignmentDetails> assignmentDetails) {
        requireAllNonNull(moduleCode, lectureDetails, tutorialDetails, zoomLink, assignmentDetails);
        this.moduleCode = moduleCode;
        this.lectureDetails = lectureDetails;
        this.tutorialDetails = tutorialDetails;
        this.zoomLink = zoomLink;
        this.assignmentDetails.addAll(assignmentDetails);
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public LectureDetails getLectureDetails() {
        return lectureDetails;
    }

    public TutorialDetails getTutorialDetails() {
        return tutorialDetails;
    }

    public ZoomLink getZoomLink() {
        return zoomLink;
    }

    /**
     * Returns an immutable assignment detail set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * @return
     */
    public Set<AssignmentDetails> getAssignmentDetails() {
        return Collections.unmodifiableSet(assignmentDetails);
    }

    /**
     * Returns true if both modules have the same module code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode());
    }

    /**
     * Returns true if both modules have the same code and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof  Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getLectureDetails().equals(getLectureDetails())
                && otherModule.getTutorialDetails().equals(getTutorialDetails())
                && otherModule.getZoomLink().equals(getZoomLink())
                && otherModule.getAssignmentDetails().equals(getAssignmentDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, lectureDetails, tutorialDetails, zoomLink, assignmentDetails);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Lecture Details: ")
                .append(getLectureDetails())
                .append("; Tutorial Details: ")
                .append(getTutorialDetails())
                .append("; Zoom Link: ")
                .append(getZoomLink());

        Set<AssignmentDetails> assignmentDetails = getAssignmentDetails();
        if (!assignmentDetails.isEmpty()) {
            builder.append("; Assignment Details: ");
            assignmentDetails.forEach(builder::append);
        }
        return builder.toString();
    }
}
