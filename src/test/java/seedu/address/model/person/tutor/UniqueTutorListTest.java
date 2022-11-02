package seedu.address.model.person.tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.TUTOR1;
import static seedu.address.testutil.TypicalTutors.TUTOR2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.tutor.exceptions.DuplicateTutorException;
import seedu.address.model.person.tutor.exceptions.TutorNotFoundException;
import seedu.address.testutil.TutorBuilder;

public class UniqueTutorListTest {


    private final UniqueTutorList uniqueTutorList = new UniqueTutorList();

    @Test
    public void contains_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.contains(null));
    }

    @Test
    public void contains_tutorNotInList_returnsFalse() {
        assertFalse(uniqueTutorList.contains(TUTOR1));
    }

    @Test
    public void contains_tutorInList_returnsTrue() {
        uniqueTutorList.add(TUTOR1);
        assertTrue(uniqueTutorList.contains(TUTOR1));
    }

    @Test
    public void contains_tutorWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorList.add(TUTOR1);
        Tutor editedTutor1 = new TutorBuilder(TUTOR1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTutorList.contains(editedTutor1));
    }

    @Test
    public void add_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.add(null));
    }

    @Test
    public void add_duplicateTutor_throwsDuplicateTutorException() {
        uniqueTutorList.add(TUTOR1);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.add(TUTOR1));
    }

    @Test
    public void setTutor_nullTargetTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutor(null, TUTOR1));
    }

    @Test
    public void setTutor_nullEditedTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutor(TUTOR1, null));
    }

    @Test
    public void setTutor_targetTutorNotInList_throwsTutorNotFoundException() {
        assertThrows(TutorNotFoundException.class, () -> uniqueTutorList.setTutor(TUTOR1, TUTOR1));
    }

    @Test
    public void setTutor_editedTutorIsSamePerson_success() {
        uniqueTutorList.add(TUTOR1);
        uniqueTutorList.setTutor(TUTOR1, TUTOR1);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(TUTOR1);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasSameIdentity_success() {
        uniqueTutorList.add(TUTOR1);
        Tutor editedTutor1 = new TutorBuilder(TUTOR1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTutorList.setTutor(TUTOR1, editedTutor1);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(editedTutor1);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasDifferentIdentity_success() {
        uniqueTutorList.add(TUTOR1);
        uniqueTutorList.setTutor(TUTOR1, TUTOR2);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(TUTOR2);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutor_editedTutorHasNonUniqueIdentity_throwsDuplicateTutorException() {
        uniqueTutorList.add(TUTOR1);
        uniqueTutorList.add(TUTOR2);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.setTutor(TUTOR1, TUTOR2));
    }

    @Test
    public void remove_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.remove(null));
    }

    @Test
    public void remove_tutorDoesNotExist_throwsTutorNotFoundException() {
        assertThrows(TutorNotFoundException.class, () -> uniqueTutorList.remove(TUTOR1));
    }

    @Test
    public void remove_existingTutor_removesTutor() {
        uniqueTutorList.add(TUTOR1);
        uniqueTutorList.remove(TUTOR1);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_nullUniqueTutorList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutors((UniqueTutorList) null));
    }

    @Test
    public void setTutors_uniqueTutorList_replacesOwnListWithProvidedUniqueTutorList() {
        uniqueTutorList.add(TUTOR1);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(TUTOR2);
        uniqueTutorList.setTutors(expectedUniqueTutorList);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorList.setTutors((List<Tutor>) null));
    }

    @Test
    public void setTutors_list_replacesOwnListWithProvidedList() {
        uniqueTutorList.add(TUTOR1);
        List<Tutor> tutorList = Collections.singletonList(TUTOR2);
        uniqueTutorList.setTutors(tutorList);
        UniqueTutorList expectedUniqueTutorList = new UniqueTutorList();
        expectedUniqueTutorList.add(TUTOR2);
        assertEquals(expectedUniqueTutorList, uniqueTutorList);
    }

    @Test
    public void setTutors_listWithDuplicateTutors_throwsDuplicateTutorException() {
        List<Tutor> listWithDuplicateTutors = Arrays.asList(TUTOR1, TUTOR1);
        assertThrows(DuplicateTutorException.class, () -> uniqueTutorList.setTutors(listWithDuplicateTutors));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTutorList.asUnmodifiableObservableTutorList().remove(0));
    }

    @Test
    public void sort_default_success() {
        ArrayList<Tutor> expected = new ArrayList<>(Arrays.asList(
                new TutorBuilder().withName("Zedd").build(),
                new TutorBuilder().withName("Adam").build(),
                new TutorBuilder().withName("Macey").build()));
        expected.forEach(uniqueTutorList::add);
        uniqueTutorList.sort(SortCommand.SortBy.ALPHA);
        uniqueTutorList.sort(SortCommand.SortBy.REVERSE);
        uniqueTutorList.sort(SortCommand.SortBy.DEFAULT);
        ArrayList<Tutor> actual = new ArrayList<>(uniqueTutorList.internalList);
        assertEquals(expected, actual);
    }

}
