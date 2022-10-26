package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSIGNMENT_DETAILS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_CODE = "CS2103T%";
    private static final String INVALID_LECTURE = " ";
    private static final String INVALID_TUTORIAL = " ";
    private static final String INVALID_LECTURE_ZOOM = "google.com";
    private static final String INVALID_TUTORIAL_ZOOM = "yahoo.com";

    private static final String VALID_MODULE_CODE = CS2103T.getModuleCode().toString();
    private static final String VALID_LECTURE = CS2103T.getLectureDetails().toString();
    private static final String VALID_TUTORIAL = CS2103T.getTutorialDetails().toString();
    private static final String VALID_LECTURE_ZOOM = CS2103T.getLectureZoomLink().toString();
    private static final String VALID_TUTORIAL_ZOOM = CS2103T.getTutorialZoomLink().toString();
    private static final List<JsonAdaptedAssignmentDetails> VALID_ASSIGNMENT_DETAILS = CS2103T.getAssignmentDetails()
            .stream()
            .map(JsonAdaptedAssignmentDetails::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsPerson() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T);
        assertEquals(CS2103T, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_CODE, VALID_LECTURE, VALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(null, VALID_LECTURE, VALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = String.format(JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT,
                ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidLecture_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, INVALID_LECTURE, VALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = LectureDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTutorial_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, VALID_LECTURE, INVALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = TutorialDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }


    @Test
    public void toModelType_invalidLectureZoom_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, VALID_LECTURE, VALID_TUTORIAL, INVALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = ZoomLink.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTutorialZoom_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, VALID_LECTURE, VALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        INVALID_TUTORIAL_ZOOM, VALID_ASSIGNMENT_DETAILS);
        String expectedMessage = ZoomLink.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedAssignmentDetails> invalidAssignmentDetails = new ArrayList<>(VALID_ASSIGNMENT_DETAILS);
        invalidAssignmentDetails.add(new JsonAdaptedAssignmentDetails(INVALID_ASSIGNMENT_DETAILS));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_MODULE_CODE, VALID_LECTURE, VALID_TUTORIAL, VALID_LECTURE_ZOOM,
                        VALID_TUTORIAL_ZOOM, invalidAssignmentDetails);
        assertThrows(IllegalValueException.class, module::toModelType);
    }
}
