package jarvis.model.student;

import static jarvis.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static jarvis.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalPersons.ALICE;
import static jarvis.testutil.TypicalPersons.BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.model.student.exceptions.DuplicatePersonException;
import jarvis.model.student.exceptions.PersonNotFoundException;
import jarvis.testutil.PersonBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStudentList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueStudentList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueStudentList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStudentList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(ALICE);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStudentList.setStudent(ALICE, editedAlice);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedAlice);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, BOB);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueStudentList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStudentList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.remove(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueStudentList.add(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }
}
