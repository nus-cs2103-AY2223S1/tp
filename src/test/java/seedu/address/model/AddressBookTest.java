package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalStudents.STUDENT2;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentsAddressBook;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;
import static seedu.address.testutil.TypicalTutors.TUTOR1;
import static seedu.address.testutil.TypicalTutors.TUTOR2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.exceptions.DuplicateStudentException;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.person.tutor.exceptions.DuplicateTutorException;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.model.tuitionclass.exceptions.DuplicateTuitionClassException;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalStudentsAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedStudent = new StudentBuilder(STUDENT1).build();
        List<Student> newStudents = Arrays.asList(STUDENT1, editedStudent);
        List<Tutor> newTutors = Arrays.asList();
        List<TuitionClass> newTuitionClasses = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTutors_throwDuplicatePersonException() {
        Tutor editedTutor = new TutorBuilder(TUTOR1).build();
        List<Student> newStudents = Arrays.asList();
        List<Tutor> newTutors = Arrays.asList(TUTOR1, editedTutor);
        List<TuitionClass> newTuitionClasses = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);

        assertThrows(DuplicateTutorException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTuitionClasses_throwDuplicatePersonException() {
        TuitionClass editedTuitionClass = new TuitionClassBuilder(TUITIONCLASS2).withSubject("MATHEMATICS").build();
        List<Student> newStudents = Arrays.asList();
        List<Tutor> newTutors = Arrays.asList();
        List<TuitionClass> newTuitionClasses = Arrays.asList(TUITIONCLASS2, editedTuitionClass);
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);

        assertThrows(DuplicateTuitionClassException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(STUDENT1));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(TUTOR1);
        assertTrue(addressBook.hasPerson(TUTOR1));
    }

    @Test
    public void hasStudent_studentWithSameFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(STUDENT1);
        Student editedStudent = new StudentBuilder(STUDENT1).build();
        assertTrue(addressBook.hasPerson(editedStudent));
    }

    @Test
    public void editStudent_checkIfEdited() {
        Student editedStudent = new StudentBuilder(STUDENT2).build();
        addressBook.addPerson(STUDENT1);
        addressBook.setPerson(STUDENT1, editedStudent);
        assertTrue(addressBook.hasPerson(STUDENT2));
    }

    @Test
    public void hasTutor_tutorWithSameFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(TUTOR1);
        Tutor editedTutor = new TutorBuilder(TUTOR1).build();
        assertTrue(addressBook.hasPerson(editedTutor));
    }

    @Test
    public void editTutor_checkIfEdited() {
        Tutor editedTutor = new TutorBuilder(TUTOR2).withName("Alice Pauline").build();
        addressBook.addPerson(TUTOR1);
        addressBook.setPerson(TUTOR1, editedTutor);
        assertFalse(addressBook.hasPerson(TUTOR2));
    }

    @Test
    public void hasTuitionClass_tuitionClassWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTuitionClass(TUITIONCLASS2);
        TuitionClass editedTuitionClass = new TuitionClassBuilder(TUITIONCLASS2).withSubject("MATHEMATICS").build();
        assertTrue(addressBook.hasTuitionClass(editedTuitionClass));
    }

    @Test
    public void hasTuitionClass_removeTuitionClass_checkIfRemoved() {
        addressBook.addTuitionClass(TUITIONCLASS2);
        addressBook.removeTuitionClass(TUITIONCLASS2);
        assertFalse(addressBook.hasTuitionClass(TUITIONCLASS2));
    }

    @Test
    public void editTuitionClass_checkIfEdited() {
        TuitionClass editedTuitionClass = new TuitionClassBuilder(TUITIONCLASS2).withName("P2MATH").build();
        addressBook.addTuitionClass(TUITIONCLASS2);
        addressBook.setTuitionClass(TUITIONCLASS2, editedTuitionClass);
        assertTrue(addressBook.hasTuitionClass(TUITIONCLASS1));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void getTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTutorList().remove(0));
    }

    @Test
    public void getTuitionClassList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTuitionClassList().remove(0));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(addressBook.equals(addressBook));

        // same lists of entities -> returns true
        AddressBook addressBook2 = new AddressBook();
        assertTrue(addressBook.equals(addressBook2));

        //different student list -> returns false
        addressBook.addPerson(STUDENT1);
        assertFalse(addressBook.equals(addressBook2));

        // different tutor list -> returns false
        addressBook2.addPerson(STUDENT1);
        addressBook.addPerson(TUTOR1);
        assertFalse(addressBook.equals(addressBook2));

        // different tuition class list -> returns false
        addressBook2.addPerson(TUTOR1);
        addressBook.addTuitionClass(TUITIONCLASS1);
        assertFalse(addressBook.equals(addressBook2));
    }

    @Test
    public void hash() {
        AddressBook addressBook2 = new AddressBook();

        // same lists of entities -> returns true
        assertEquals(addressBook.hashCode(), addressBook2.hashCode());

        // different student list
        addressBook2.addPerson(STUDENT1);
        assertNotEquals(addressBook.hashCode(), addressBook2.hashCode());

        // different tutor list -> returns different hashcode
        addressBook2.removePerson(STUDENT1);
        addressBook2.addPerson(TUTOR1);
        assertNotEquals(addressBook.hashCode(), addressBook2.hashCode());

        // different tuition class list -> returns different hashcode
        addressBook2.removePerson(TUTOR1);
        addressBook2.addTuitionClass(TUITIONCLASS1);
        assertNotEquals(addressBook.hashCode(), addressBook2.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Tutor> tutors = FXCollections.observableArrayList();
        private final ObservableList<TuitionClass> tuitionClasses = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students, Collection<Tutor> tutors,
                        Collection<TuitionClass> tuitionClasses) {
            this.students.setAll(students);
            this.tutors.setAll(tutors);
            this.tuitionClasses.setAll(tuitionClasses);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Tutor> getTutorList() {
            return tutors;
        }

        @Override
        public ObservableList<TuitionClass> getTuitionClassList() {
            return tuitionClasses;
        }
    }

}
