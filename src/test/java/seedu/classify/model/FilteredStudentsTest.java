package seedu.classify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.classify.model.student.Student;

class FilteredStudentsTest {
    private List<Student> li = new ArrayList<>();
    private ObservableList<Student> emptyList = FXCollections.observableList(li);
    private FilteredStudents filteredStudents = new FilteredStudents(new FilteredList<>(emptyList));

    @Test
    public void hasConciseInfo_initial_returnsFalse() {
        assertFalse(filteredStudents.hasConciseInfo());
    }

    @Test
    public void toggleConciseInfo_toggleTrue() {
        filteredStudents.toggleConciseInfo();
        assertTrue(filteredStudents.hasConciseInfo());
    }

    @Test
    public void equals() {
        // Same object, return true
        assertEquals(filteredStudents, filteredStudents);

        // Not an instance of FilteredStudents, return false
        assertNotEquals(filteredStudents, new Object());
    }

}
