package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.testutil.StudentBuilder;


public class UniqueScheduleListTest {

    private final UniqueScheduleList uniqueScheduleList = new UniqueScheduleList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueScheduleList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueScheduleList.add(ALICE);
        assertTrue(uniqueScheduleList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueScheduleList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(uniqueScheduleList.contains(editedAlice));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.add(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        uniqueScheduleList.add(ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueScheduleList.add(ALICE));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setStudent(null, ALICE));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setStudent(ALICE, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueScheduleList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        uniqueScheduleList.add(ALICE);
        uniqueScheduleList.setStudent(ALICE, ALICE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(ALICE);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueScheduleList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        uniqueScheduleList.setStudent(ALICE, editedAlice);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(editedAlice);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueScheduleList.add(ALICE);
        uniqueScheduleList.setStudent(ALICE, BOB);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(BOB);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueScheduleList.add(ALICE);
        uniqueScheduleList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueScheduleList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueScheduleList.remove(ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueScheduleList.add(ALICE);
        uniqueScheduleList.remove(ALICE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudents_nullUniqueScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setStudents((UniqueScheduleList) null));
    }

    @Test
    public void setStudents_uniqueScheduleList_replacesOwnListWithProvidedUniqueScheduleList() {
        uniqueScheduleList.add(ALICE);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(BOB);
        uniqueScheduleList.setStudents(expectedUniqueScheduleList);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueScheduleList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueScheduleList.setStudents(studentList);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(BOB);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueScheduleList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueScheduleList.asUnmodifiableObservableList().remove(0));
    }
}
