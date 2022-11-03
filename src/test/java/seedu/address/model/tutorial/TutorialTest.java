package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTURIAL2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_TUTORIAL2;
import static seedu.address.testutil.TypicalTutorials.TUTORIAL1;
import static seedu.address.testutil.TypicalTutorials.TUTORIAL2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialBuilder;

public class TutorialTest {
    @Test
    public void isSameTutorial() {
        // same object -> returns true
        assertTrue(TUTORIAL1.isSameTutorial(TUTORIAL1));

        // null -> returns false
        assertFalse(TUTORIAL1.isSameTutorial(null));

        // same time, all other attributes different -> returns true
        Tutorial editedTutorial1 = new TutorialBuilder(TUTORIAL1).withContent(VALID_CONTENT_TUTORIAL1)
                .withGroup(VALID_GROUP_TUTORIAL1).build();
        assertTrue(TUTORIAL1.isSameTutorial(editedTutorial1));

        // different time, all other attributes same -> returns false
        editedTutorial1 = new TutorialBuilder(TUTORIAL1).withTime(VALID_TIME_TUTORIAL2).build();
        assertFalse(TUTORIAL1.isSameTutorial(editedTutorial1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tutorial tutorial1Copy = new TutorialBuilder(TUTORIAL1).build();

        assertTrue(TUTORIAL1.equals(tutorial1Copy));

        // same object -> returns true
        assertTrue(TUTORIAL1.equals(TUTORIAL1));

        // null -> returns false
        assertFalse(TUTORIAL1.equals(null));

        // different type -> returns false
        assertFalse(TUTORIAL1.equals(5));

        // different tutorial -> returns false
        assertFalse(TUTORIAL1.equals(TUTORIAL2));

        // different time -> return false
        Tutorial editedTutorial1 = new TutorialBuilder(TUTORIAL1).withTime(VALID_TIME_TUTORIAL2).build();
        assertFalse(TUTORIAL1.equals(editedTutorial1));

        // different group -> returns false
        editedTutorial1 = new TutorialBuilder(TUTORIAL1).withGroup(VALID_GROUP_TUTURIAL2).build();
        assertFalse(TUTORIAL1.equals(editedTutorial1));

        // different content -> returns false
        editedTutorial1 = new TutorialBuilder(TUTORIAL1).withContent(VALID_CONTENT_TUTORIAL1).build();
        assertFalse(TUTORIAL1.equals(editedTutorial1));

    }
}
