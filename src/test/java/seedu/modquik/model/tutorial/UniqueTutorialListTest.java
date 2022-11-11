package seedu.modquik.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modquik.testutil.Assert.assertThrows;
import static seedu.modquik.testutil.TypicalTutorials.TUTORIAL1;
import static seedu.modquik.testutil.TypicalTutorials.TUTORIAL2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.modquik.model.tutorial.exceptions.DuplicateTutorialException;
import seedu.modquik.model.tutorial.exceptions.TutorialNotFoundException;

public class UniqueTutorialListTest {

    private final UniqueTutorialList uniqueTutorialList = new UniqueTutorialList();

    @Test
    public void contains_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.contains(null));
    }

    @Test
    public void contains_tutorialNotInList_returnsFalse() {
        assertFalse(uniqueTutorialList.contains(TUTORIAL1));
    }

    @Test
    public void contains_tutorialInList_returnsTrue() {
        uniqueTutorialList.add(TUTORIAL1);
        assertTrue(uniqueTutorialList.contains(TUTORIAL1));
    }

    @Test
    public void add_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.add(null));
    }

    @Test
    public void add_duplicateTutorial_throwsDuplicateTutorialException() {
        uniqueTutorialList.add(TUTORIAL1);
        assertThrows(DuplicateTutorialException.class, () -> uniqueTutorialList.add(TUTORIAL1));
    }

    @Test
    public void setTutorial_nullTargetTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorial(null, TUTORIAL1));
    }

    @Test
    public void setTutorial_nullEditedTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorial(TUTORIAL1, null));
    }

    @Test
    public void setTutorial_targetTutorialNotInList_throwsTutorialNotFoundException() {
        assertThrows(TutorialNotFoundException.class, () -> uniqueTutorialList.setTutorial(TUTORIAL1, TUTORIAL1));
    }

    @Test
    public void setTutorial_editedTutorialIsSameTutorial_success() {
        uniqueTutorialList.add(TUTORIAL1);
        uniqueTutorialList.setTutorial(TUTORIAL1, TUTORIAL1);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(TUTORIAL1);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void remove_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.remove(null));
    }

    @Test
    public void remove_tutorialDoesNotExist_throwsTutorialNotFoundException() {
        assertThrows(TutorialNotFoundException.class, () -> uniqueTutorialList.remove(TUTORIAL1));
    }

    @Test
    public void remove_existingTutorial_removesTutorial() {
        uniqueTutorialList.add(TUTORIAL1);
        uniqueTutorialList.remove(TUTORIAL1);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorials_list_replacesOwnListWithProvidedList() {
        uniqueTutorialList.add(TUTORIAL1);
        List<Tutorial> tutorialList = Collections.singletonList(TUTORIAL2);
        uniqueTutorialList.setTutorials(tutorialList);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(TUTORIAL2);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorials_listWithDuplicateTutorials_throwsDuplicateTutorialException() {
        List<Tutorial> listWithDuplicateTutorials = Arrays.asList(TUTORIAL1, TUTORIAL1);
        assertThrows(DuplicateTutorialException.class, () ->
                uniqueTutorialList.setTutorials(listWithDuplicateTutorials));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTutorialList.asUnmodifiableObservableList().remove(0));
    }
}
