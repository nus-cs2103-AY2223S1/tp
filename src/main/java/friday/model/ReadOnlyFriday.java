package friday.model;

import java.util.Map;
import java.util.Set;

import friday.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of FRIDAY.
 */
public interface ReadOnlyFriday {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns a Set view of the mappings contained in alias map.
     * This set will not contain any duplicate aliases.
     */
    Set<Map.Entry<String, String>> getAliasMap();

}
