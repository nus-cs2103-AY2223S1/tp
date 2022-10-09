package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS2;
import static seedu.address.testutil.TypicalTutors.TUTOR1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedStudent = new StudentBuilder(STUDENT1).withSchool("Woodlands Primary School").build();
        List<Person> newStudents = Arrays.asList(STUDENT1, editedStudent);
        List<Person> newTutors = Arrays.asList();
        List<TuitionClass> newTuitionClasses = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTutors_throwDuplicatePersonException() {
        Tutor editedTutor = new TutorBuilder(TUTOR1).withPhone("91006745").build();
        List<Person> newStudents = Arrays.asList();
        List<Person> newTutors = Arrays.asList(TUTOR1, editedTutor);
        List<TuitionClass> newTuitionClasses = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTuitionClasses_throwDuplicatePersonException() {
        TuitionClass editedTuitionClass = new TuitionClassBuilder(TUITIONCLASS2).withSubject("MATHEMATICS").build();
        List<Person> newStudents = Arrays.asList();
        List<Person> newTutors = Arrays.asList();
        List<TuitionClass> newTuitionClasses = Arrays.asList(TUITIONCLASS2, editedTuitionClass);
        AddressBookStub newData = new AddressBookStub(newStudents, newTutors, newTuitionClasses);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(TUTOR1);
        assertTrue(addressBook.hasPerson(TUTOR1));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(STUDENT1);
        Student editedStudent = new StudentBuilder(STUDENT1).withSchool("Woodlands Primary School").build();
        assertTrue(addressBook.hasPerson(editedStudent));
    }

    @Test
    public void hasTutor_tutorWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(TUTOR1);
        Tutor editedTutor = new TutorBuilder(TUTOR1).withPhone("91006745").build();
        assertTrue(addressBook.hasPerson(editedTutor));
    }

    @Test
    public void hasTuitionClass_tuitionClassWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTuitionClass(TUITIONCLASS2);
        TuitionClass editedTuitionClass = new TuitionClassBuilder(TUITIONCLASS2).withSubject("MATHEMATICS").build();
        assertTrue(addressBook.hasTuitionClass(editedTuitionClass));
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

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Person> students = FXCollections.observableArrayList();
        private final ObservableList<Person> tutors = FXCollections.observableArrayList();
        private final ObservableList<TuitionClass> tuitionClasses = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> students, Collection<Person> tutors,
                        Collection<TuitionClass> tuitionClasses) {
            this.students.setAll(students);
            this.tutors.setAll(tutors);
            this.tuitionClasses.setAll(tuitionClasses);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Person> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Person> getTutorList() {
            return tutors;
        }

        @Override
        public ObservableList<TuitionClass> getTuitionClassList() {
            return tuitionClasses;
        }
    }

}
