package seedu.hrpro.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalProjects.APPLE;
import static seedu.hrpro.testutil.TypicalProjects.BOB;

import org.junit.jupiter.api.Test;

import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

public class ProjectTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> project.getTags().remove(0));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(APPLE.isSameProject(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameProject(null));

        // same name, all other attributes different -> returns true
        Project editedApple = new ProjectBuilder(APPLE).withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_BOB)
                .withStaff(new StaffBuilder().build())
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(APPLE.isSameProject(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new ProjectBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.isSameProject(editedApple));

        // name differs in case, all other attributes same -> returns true
        Project editedBob = new ProjectBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameProject(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ProjectBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameProject(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project appleCopy = new ProjectBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different project -> returns false
        assertFalse(APPLE.equals(BOB));

        // different name -> returns false
        Project editedApple = new ProjectBuilder(APPLE).withName(VALID_NAME_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different budget -> returns false
        editedApple = new ProjectBuilder(APPLE).withBudget(VALID_BUDGET_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different deadline -> returns false
        editedApple = new ProjectBuilder(APPLE).withDeadline(VALID_DEADLINE_BOB).build();
        assertFalse(APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new ProjectBuilder(APPLE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
