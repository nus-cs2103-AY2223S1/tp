package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.model.tutor.exceptions.DuplicateTutorException;
import tuthub.model.tutor.exceptions.TutorNotFoundException;
import tuthub.testutil.TutorBuilder;

public class UniqueTutorListTest {

    private final UniqueTutorList uniqueTutorList = new UniqueTutorList();

    @Test
    public void contains_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.contains(null));
    }

    @Test
    public void contains_tutorNotInList_returnsFalse() {
        assertFalse(uniqueTutorList.contains(ALICE));
    }

    @Test
    public void contains_tutorInList_returnsTrue() {
        uniqueTutorList.add(ALICE);
        assertTrue(uniqueTutorList.contains(ALICE));
    }

    @Test
    public void contains_tutorWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorList.add(ALICE);
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTutorList.contains(editedAlice));
    }

    @Test
    public void add_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.add(null));
    }

    @Test
    public void add_duplicateTutor_throwsDuplicateTutorException() {
        uniqueTutorList.add(ALICE);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.add(ALICE));
    }

    @Test
    public void setTutor_nullTargetTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutor(null, ALICE));
    }

    @Test
    public void setTutor_nullEditedTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutor(ALICE, null));
    }

    @Test
    public void setTutor_targetTutorNotInList_throwsTutorNotFoundException() {
        assertThrows(TutorNotFoundException.class, () -> uniqueTutorList.setTutor(ALICE, ALICE));
    }

    @Test
    public void setTutor_editedTutorIsSameTutor_success() {
        uniqueTutorList.add(ALICE);
        uniqueTutorList.setTutor(ALICE, ALICE);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(ALICE);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasSameIdentity_success() {
        uniqueTutorList.add(ALICE);
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTutorList.setTutor(ALICE, editedAlice);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(editedAlice);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasDifferentIdentity_success() {
        uniqueTutorList.add(ALICE);
        uniqueTutorList.setTutor(ALICE, BOB);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(BOB);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasNonUniqueIdentity_throwsDuplicateTutorException() {
        uniqueTutorList.add(ALICE);
        uniqueTutorList.add(BOB);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.setTutor(ALICE, BOB));
    }

    @Test
    public void remove_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.remove(null));
    }

    @Test
    public void remove_tutorDoesNotExist_throwsTutorNotFoundException() {
        assertThrows(TutorNotFoundException.class, () -> uniqueTutorList.remove(ALICE));
    }

    @Test
    public void remove_existingTutor_removesTutor() {
        uniqueTutorList.add(ALICE);
        uniqueTutorList.remove(ALICE);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_nullUniqueTutorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutors((UniqueTutorList) null));
    }

    @Test
    public void setTutors_uniqueTutorList_replacesOwnListWithProvidedUniqueTutorList() {
        uniqueTutorList.add(ALICE);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(BOB);
        uniqueTutorList.setTutors(expectedUniqueTutorList);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutors((List<Tutor>) null));
    }

    @Test
    public void setTutors_list_replacesOwnListWithProvidedList() {
        uniqueTutorList.add(ALICE);
        List<Tutor> tutorList = Collections.singletonList(BOB);
        uniqueTutorList.setTutors(tutorList);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(BOB);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_listWithDuplicateTutors_throwsDuplicateTutorException() {
        List<Tutor> listWithDuplicateTutors = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.setTutors(listWithDuplicateTutors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTutorList.asUnmodifiableObservableList().remove(0));
    }
}
