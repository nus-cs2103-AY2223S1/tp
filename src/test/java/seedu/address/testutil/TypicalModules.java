package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;


/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module CS2103T = new ModuleBuilder().withLectureDetails("Every friday")
        .withModuleCode("CS2103T").withTutorialDetails("Every wednesday").withZoomLink("https://nus-sg.zoom.us/CS2103T")
        .withAssignmentDetails("hard").withAssignmentDetails("normal").build();

    public static final Module CS2100 = new ModuleBuilder().withModuleCode("CS2100")
        .withLectureDetails("Every tuesday").withTutorialDetails("Every friday")
        .withZoomLink("https://nus-sg.zoom.us/CS2100")
        .withAssignmentDetails("assignment1").build();

    public static final Module CS2109S = new ModuleBuilder().withModuleCode("CS2109S")
        .withLectureDetails("Tuesday").withTutorialDetails("Wednesday").withZoomLink("https://nus-sg.zoom.us/CS2109S")
        .withAssignmentDetails("assignment1").build();

    public static final Module CS3230 = new ModuleBuilder().withModuleCode("CS3230")
        .withLectureDetails("Wednesday").withTutorialDetails("Thursday").withZoomLink("https://nus-sg.zoom.us/CS3230")
        .withAssignmentDetails("assignment1").build();

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2100, CS2109S, CS3230));
    }
}
