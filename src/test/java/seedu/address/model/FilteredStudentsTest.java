package seedu.address.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Filter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilteredStudentsTest {
    private List<Person> li = new ArrayList<>();
    private ObservableList<Person> emptyList = FXCollections.observableList(li);
    private FilteredStudents filteredStudents = new FilteredStudents(new FilteredList<>(emptyList));

    @Test
    public void hasConciseInfo_initial_returnsTrue() {
        assertTrue(filteredStudents.hasConciseInfo());
    }

    @Test
    public void setConciseInfo_setFalse() {
        filteredStudents.setConciseInfo(false);
        assertFalse(filteredStudents.hasConciseInfo());
    }

    @Test
    public void equals() {
        // Same object, return true
        assertTrue(filteredStudents.equals(filteredStudents));

        // Not an instance of FilteredStudents, return false
        assertFalse(filteredStudents.equals(new Object()));
    }

}