package seedu.address.testutil;

import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2103T = new ModuleBuilder().withLectureDetails("Every friday")
            .withModuleCode("CS2103T").withTutorialDetails("Every wednesday").withZoomLink("https://nus-sg.zoom.us")
            .withAssignmentDetails("hard").build();

    private TypicalModules() {} // prevents instantiation

}
