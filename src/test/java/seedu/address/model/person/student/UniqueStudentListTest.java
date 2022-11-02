package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.student.exceptions.DuplicateStudentException;
import seedu.address.model.person.student.exceptions.StudentNotFoundException;
import seedu.address.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(STUDENT1));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        uniqueStudentList.add(STUDENT1);
        assertTrue(uniqueStudentList.contains(STUDENT1));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.add(STUDENT1);
        Student editedStudent1 = new StudentBuilder(STUDENT1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStudentList.contains(editedStudent1));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        uniqueStudentList.add(STUDENT1);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.add(STUDENT1));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, STUDENT1));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(STUDENT1, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.setStudent(STUDENT1, STUDENT1));
    }

    @Test
    public void setStudent_editedStudentIsSamePerson_success() {
        uniqueStudentList.add(STUDENT1);
        uniqueStudentList.setStudent(STUDENT1, STUDENT1);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(STUDENT1);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueStudentList.add(STUDENT1);
        Student editedStudent1 = new StudentBuilder(STUDENT1).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStudentList.setStudent(STUDENT1, editedStudent1);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedStudent1);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.add(STUDENT1);
        uniqueStudentList.setStudent(STUDENT1, STUDENT2);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(STUDENT2);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueStudentList.add(STUDENT1);
        uniqueStudentList.add(STUDENT2);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudent(STUDENT1, STUDENT2));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.remove(STUDENT1));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.add(STUDENT1);
        uniqueStudentList.remove(STUDENT1);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.add(STUDENT1);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(STUDENT2);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(STUDENT1);
        List<Student> studentList = Collections.singletonList(STUDENT2);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(STUDENT2);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(STUDENT1, STUDENT1);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStudentList.asUnmodifiableObservableStudentList().remove(0));
    }

    @Test
    public void sort_default_success() {
        ArrayList<Student> expected = new ArrayList<>(Arrays.asList(
                new StudentBuilder().withName("Zedd").build(),
                new StudentBuilder().withName("Adam").build(),
                new StudentBuilder().withName("Macey").build()));
        expected.forEach(uniqueStudentList::add);
        uniqueStudentList.sort(SortCommand.SortBy.ALPHA);
        uniqueStudentList.sort(SortCommand.SortBy.REVERSE);
        uniqueStudentList.sort(SortCommand.SortBy.DEFAULT);
        ArrayList<Student> actual = new ArrayList<>(uniqueStudentList.internalList);
        assertEquals(expected, actual);
    }
}
