package seedu.address.storage;

import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.List;
import java.util.stream.Collectors;

public class JsonAdaptedModuleTest {
    private static final String INVALID_LECTURE_DETAILS = "TODO";
    private static final String INVALID_MODULE_CODE = "TODO";
    private static final String INVALID_TUTORIAL_DETAILS = "TODO";
    private static final String INVALID_ZOOM_LINK = "TODO";
    private static final String INVALID_ASSIGNMENT_DETAILS = "TODO";

    private static final String VALID_LECTURE_DETAILS = CS2103T.getLectureDetails().toString();
    private static final String VALID_MODULE_CODE = CS2103T.getModuleCode().toString();
    private static final String VALID_TUTORIAL_DETAILS = CS2103T.getTutorialDetails().toString();
    private static final String VALID_ZOOM_LINK = CS2103T.getZoomLink().toString();
    private static final List<JsonAdaptedAssignmentDetails> VALID_ASSIGNMENT_DETAILS = CS2103T.getAssignmentDetails()
            .stream()
            .map(JsonAdaptedAssignmentDetails::new)
            .collect(Collectors.toList());
}
