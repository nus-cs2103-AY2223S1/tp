package jarvis.model;

import java.util.Collection;
import java.util.Set;

import jarvis.commons.core.index.Index;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a student book
 */
public interface ReadOnlyStudentBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    Set<Integer> getIndexList(Collection<Student> students);

    Set<Student> studentSetOf(Set<Integer> studentIndexList);

}
