package seedu.watson.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.watson.testutil.Assert.assertThrows;
import static seedu.watson.testutil.TypicalStudents.ALICE;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.exceptions.DuplicateStudentException;
import seedu.watson.testutil.StudentBuilder;

public class DatabaseTest {

    private final Database database = new Database();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), database.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Database newData = getTypicalDatabase();
        database.resetData(newData);
        assertEquals(newData, database);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                                                     .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        DatabaseStub newData = new DatabaseStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> database.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> database.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(database.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        database.addPerson(ALICE);
        assertTrue(database.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        database.addPerson(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                                                     .build();
        assertTrue(database.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> database.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyDatabase whose students list can violate interface constraints.
     */
    private static class DatabaseStub implements ReadOnlyDatabase {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        DatabaseStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getPersonList() {
            return students;
        }
    }

}
