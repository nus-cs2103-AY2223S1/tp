package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2100;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getAssignmentDetails().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2103T.isSameModule(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameModule(null));

        // same name, all other attributes different -> returns true
        Module editedCS2103T = new ModuleBuilder(CS2103T)
                .withLectureDetails(VALID_LECTURE_DETAILS_CS2100)
                .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2100)
                .withZoomLink(VALID_ZOOM_LINK_CS2100)
                .build();
        assertTrue(CS2103T.isSameModule(editedCS2103T));

        // different name, all other attributes same -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withModuleCode(VALID_MODULE_CODE_CS2100).build();
        assertFalse(CS2103T.isSameModule(editedCS2103T));

        // name differs in case, all other attributes same -> returns false
        Module editedCS2100 = new ModuleBuilder(CS2100).withModuleCode(VALID_MODULE_CODE_CS2100.toLowerCase()).build();
        assertFalse(CS2100.isSameModule(editedCS2100));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_MODULE_CODE_CS2100 + " ";
        editedCS2100 = new ModuleBuilder(CS2100).withModuleCode(nameWithTrailingSpaces).build();
        assertFalse(CS2100.isSameModule(editedCS2100));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module CS2103TCopy = new ModuleBuilder(CS2103T).build();
        assertTrue(CS2103T.equals(CS2103TCopy));

        // same object -> returns true
        assertTrue(CS2103T.equals(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.equals(null));

        // different type -> returns false
        assertFalse(CS2103T.equals(5));

        // different Module -> returns false
        assertFalse(CS2103T.equals(CS2100));

        // different module code -> returns false
        Module editedCS2103T = new ModuleBuilder(CS2103T).withModuleCode(VALID_MODULE_CODE_CS2100).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different lecture details -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withLectureDetails(VALID_LECTURE_DETAILS_CS2100).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different tutorial details -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2100).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different zoom link -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withZoomLink(VALID_ZOOM_LINK_CS2100).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different assignment details -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withAssignmentDetails("easy").build();
        assertFalse(CS2103T.equals(editedCS2103T));
    }
}
