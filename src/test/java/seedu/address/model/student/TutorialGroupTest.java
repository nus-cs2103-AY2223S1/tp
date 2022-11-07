package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_DAVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_TIFFANI;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class TutorialGroupTest {

    private static TutorialGroup tiffaniTutorialGroup;
    private static TutorialGroup daveTutorialGroup;

    @BeforeAll
    public static void before() {
        tiffaniTutorialGroup = new TutorialGroup(VALID_TUTORIAL_GROUP_TIFFANI);
        daveTutorialGroup = new TutorialGroup(VALID_TUTORIAL_GROUP_DAVE);
    }

    @Test
    public void equals_sameTutorialGroup() {
        // same object -> returns true
        // has non-null tutorial group
        assertEquals(tiffaniTutorialGroup, tiffaniTutorialGroup);
    }

    @Test
    public void equals_nullTutorialGroupObject() {
        // null -> returns false
        assertNotEquals(tiffaniTutorialGroup, null);
    }

    @Test
    public void equals_differentTutorialGroup() {
        // different tutorial group -> returns false
        assertNotEquals(tiffaniTutorialGroup, daveTutorialGroup);
    }

    @Test
    public void isValidName_noT() {
        assertFalse(TutorialGroup.isValidTutorialGroup("S01"));
    }

    @Test
    public void isValidName_singleDigit() {
        assertFalse(TutorialGroup.isValidTutorialGroup("T1"));
    }

    @Test
    public void isValidName_validTutorialGroup() {
        assertTrue(TutorialGroup.isValidTutorialGroup("T10"));
    }

    @Test
    public void isValidName_letterInsteadOfDigit() {
        assertFalse(TutorialGroup.isValidTutorialGroup("Ta0"));
    }

    @Test
    public void isValidName_letterInsteadOfDigit2() {
        assertFalse(TutorialGroup.isValidTutorialGroup("T0a"));
    }

    @Test
    public void isValidName_lettersInsteadOfDigits() {
        assertFalse(TutorialGroup.isValidTutorialGroup("Tab"));
    }

    @Test
    public void isValidName_lowerCase() {
        assertFalse(TutorialGroup.isValidTutorialGroup("t01"));
    }

    @Test
    public void isValidName_threeDigits() {
        assertFalse(TutorialGroup.isValidTutorialGroup("T100"));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialGroup(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialGroup(invalidName));
    }
}
