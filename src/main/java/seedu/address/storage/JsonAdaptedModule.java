package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;

/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String lectureDetails;
    private final String moduleCode;
    private final String tutorialDetails;
    private final String zoomLink;
    private final List<JsonAdaptedAssignmentDetails> assignmentDetails = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("lectureDetails") String lectureDetails,
                             @JsonProperty("moduleCode") String moduleCode,
                             @JsonProperty("tutorialDetails") String tutorialDetails,
                             @JsonProperty("zoomLink") String zoomLink,
                             @JsonProperty("assignmentDetails") List<JsonAdaptedAssignmentDetails> assignmentDetails) {
        this.lectureDetails = lectureDetails;
        this.moduleCode = moduleCode;
        this.tutorialDetails = tutorialDetails;
        this.zoomLink = zoomLink;
        if (assignmentDetails != null) {
            this.assignmentDetails.addAll(assignmentDetails);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        lectureDetails = source.getLectureDetails().value;
        moduleCode = source.getModuleCode().moduleCode;
        tutorialDetails = source.getTutorialDetails().value;
        zoomLink = source.getZoomLink().zoomLink;
        assignmentDetails.addAll(source.getAssignmentDetails().stream()
                .map(JsonAdaptedAssignmentDetails::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        final List<AssignmentDetails> moduleAssignmentDetails = new ArrayList<>();
        for (JsonAdaptedAssignmentDetails assignmentDetails : assignmentDetails) {
            moduleAssignmentDetails.add(assignmentDetails.toModelType());
        }
        if (lectureDetails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LectureDetails.class.getSimpleName()));
        }
        if (!LectureDetails.areValidLectureDetails(lectureDetails)) {
            throw new IllegalValueException(LectureDetails.MESSAGE_CONSTRAINTS);
        }
        final LectureDetails modelLectureDetails = new LectureDetails(lectureDetails);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (tutorialDetails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialDetails.class.getSimpleName()));
        }
        if (!TutorialDetails.areValidTutorialDetails(tutorialDetails)) {
            throw new IllegalValueException(TutorialDetails.MESSAGE_CONSTRAINTS);
        }
        final TutorialDetails modelTutorialDetails = new TutorialDetails(tutorialDetails);

        if (zoomLink == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ZoomLink.class.getSimpleName()));
        }
        if (!ZoomLink.isValidUrl(zoomLink)) {
            throw new IllegalValueException(ZoomLink.MESSAGE_CONSTRAINTS);
        }
        final ZoomLink modelZoomLink = new ZoomLink(zoomLink);

        final Set<AssignmentDetails> modelAssignmentDetails = new HashSet<>(moduleAssignmentDetails);
        return new Module(modelModuleCode, modelLectureDetails, modelTutorialDetails, modelZoomLink,
                modelAssignmentDetails);
    }

}
