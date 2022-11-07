package friday.model;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import friday.logic.commands.CommandTestUtil;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;
import friday.model.student.Student;
import friday.model.student.exceptions.DuplicateStudentException;
import friday.testutil.StudentBuilder;
import friday.testutil.TypicalStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FridayTest {

    private final Friday friday = new Friday();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friday.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friday.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFriday_replacesData() {
        Friday newData = TypicalStudents.getTypicalFriday();
        friday.resetData(newData);
        assertEquals(newData, friday);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE)
                .withMasteryCheck(CommandTestUtil.VALID_MASTERYCHECK_DATE_BOB, false)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(TypicalStudents.ALICE, editedAlice);
        FridayStub newData = new FridayStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> friday.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friday.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInFriday_returnsFalse() {
        assertFalse(friday.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void hasStudent_studentInFriday_returnsTrue() {
        friday.addStudent(TypicalStudents.ALICE);
        assertTrue(friday.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInFriday_returnsTrue() {
        friday.addStudent(TypicalStudents.ALICE);
        Student editedAlice = new StudentBuilder(TypicalStudents.ALICE)
                .withMasteryCheck(CommandTestUtil.VALID_MASTERYCHECK_DATE_BOB, false)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertTrue(friday.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friday.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyFriday whose students list can violate interface constraints.
     */
    private static class FridayStub implements ReadOnlyFriday {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        FridayStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public Set<Map.Entry<Alias, ReservedKeyword>> getAliasMap() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
