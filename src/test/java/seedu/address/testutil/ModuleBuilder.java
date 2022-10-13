package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_LECTURE_DETAILS = "lectureDetail";
    public static final String DEFAULT_MODULE_CODE = "moduleCode";
    public static final String DEFAULT_TUTORIAL_DETAILS = "tutorialDetail";
    public static final String DEFAULT_ZOOM_LINK = "https://nus-sg.zoom.us";

    private LectureDetails lectureDetails;
    private ModuleCode moduleCode;
    private TutorialDetails tutorialDetails;
    private ZoomLink zoomLink;
    private Set<AssignmentDetails> assignmentDetails;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        lectureDetails = new LectureDetails(DEFAULT_LECTURE_DETAILS);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        tutorialDetails = new TutorialDetails(DEFAULT_TUTORIAL_DETAILS);
        zoomLink = new ZoomLink(DEFAULT_ZOOM_LINK);
        assignmentDetails = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        lectureDetails = moduleToCopy.getLectureDetails();
        moduleCode = moduleToCopy.getModuleCode();
        tutorialDetails = moduleToCopy.getTutorialDetails();
        zoomLink = moduleToCopy.getZoomLink();
        assignmentDetails = new HashSet<>(moduleToCopy.getAssignmentDetails());
    }

    /**
     * Sets the {@code LectureDetails} of the {@code Module} that we are building.
     */
    public ModuleBuilder withLectureDetails(String lectureDetails) {
        this.lectureDetails = new LectureDetails(lectureDetails);
        return this;
    }

    /**
     * Parses the {@code AssignmentDetails} into a {@code Set<AssignmentDetails>} and set it to the
     * {@code Module} that we are building.
     */
    public ModuleBuilder withAssignmentDetails(String... assignmentDetails) {
        this.assignmentDetails = SampleDataUtil.getAssignmentDetailsSet(assignmentDetails);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code TutorialDetails} of the {@code Module} that we are building.
     */
    public ModuleBuilder withTutorialDetails(String tutorialDetails) {
        this.tutorialDetails = new TutorialDetails(tutorialDetails);
        return this;
    }

    /**
     * Sets the {@code ZoomLink} of the {@code Module} that we are building.
     */
    public ModuleBuilder withZoomLink(String zoomLink) {
        this.zoomLink = new ZoomLink(zoomLink);
        return this;
    }

    public Module build() {
        return new Module(moduleCode, lectureDetails, tutorialDetails, zoomLink, assignmentDetails);
    }

}
