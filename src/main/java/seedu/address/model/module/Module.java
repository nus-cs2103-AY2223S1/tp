package seedu.address.model.module;

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

    public static final String INVALID_MODULE_MESSAGE = "Module needs a module code";

    // Identity fields
    private final ModuleCode moduleCode;

    // Data fields
    private final LectureDetails lectureDetails;
    private final TutorialDetails tutorialDetails;
    private final ZoomLink lectureZoomLink;
    private final ZoomLink tutorialZoomLink;
    private final Set<AssignmentDetails> assignmentDetails = new HashSet<>();

    /**
     * Module code must be present and not null.
     */
    public Module(ModuleCode moduleCode, LectureDetails lectureDetails,
                  TutorialDetails tutorialDetails, ZoomLink lectureZoomLink, ZoomLink tutorialZoomLink,
                  Set<AssignmentDetails> assignmentDetails) {
        requireAllNonNull(moduleCode, lectureDetails, tutorialDetails, lectureZoomLink, tutorialZoomLink,
            assignmentDetails);
        assert moduleCode.moduleCode != null; // There should be no way to reach here with module code as null
        this.moduleCode = moduleCode;
        this.lectureDetails = lectureDetails;
        this.tutorialDetails = tutorialDetails;
        this.lectureZoomLink = lectureZoomLink;
        this.tutorialZoomLink = tutorialZoomLink;
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

    public ZoomLink getLectureZoomLink() {
        return lectureZoomLink;
    }

    public ZoomLink getTutorialZoomLink() {
        return tutorialZoomLink;
    }

    /**
     * Returns an immutable assignment detail set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
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

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals(getModuleCode())
            && otherModule.getLectureDetails().equals(getLectureDetails())
            && otherModule.getTutorialDetails().equals(getTutorialDetails())
            && otherModule.getLectureZoomLink().equals(getLectureZoomLink())
            && otherModule.getTutorialZoomLink().equals(getTutorialZoomLink())
            && otherModule.getAssignmentDetails().equals(getAssignmentDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCode, lectureDetails, tutorialDetails,
            lectureZoomLink, tutorialZoomLink, assignmentDetails);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode());

        if (!(getLectureDetails().toString().equals(""))) {
            builder.append("; Lecture Details: ")
                .append(getLectureDetails());
        }
        if (!(getTutorialDetails().toString().equals(""))) {
            builder.append("; Tutorial Details: ")
                .append(getTutorialDetails());
        }
        if (!(getLectureZoomLink().toString().equals(""))) {
            builder.append("; Lecture Zoom Link: ")
                .append(getLectureZoomLink());
        }
        if (!(getTutorialZoomLink().toString().equals(""))) {
            builder.append("; Tutorial Zoom Link: ")
                .append(getTutorialZoomLink());
        }

        Set<AssignmentDetails> assignmentDetails = getAssignmentDetails();
        if (!assignmentDetails.isEmpty()) {
            builder.append("; Assignment Details: ");
            assignmentDetails.forEach(builder::append);
        }
        return builder.toString();
    }
}
