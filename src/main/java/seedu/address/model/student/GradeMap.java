package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.exceptions.DuplicateGradeKeyException;
import seedu.address.model.student.exceptions.GradeKeyNotFoundException;


/**
 * A map that maps a {@code GradeKey} to a {@code Grade}
 */
public class GradeMap implements Iterable<Grade> {

    private final ObservableMap<GradeKey, Grade> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<GradeKey, Grade> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);

    /**
     * Returns true if the map contains an equivalent {@code GradeKey} as the given argument.
     */
    public boolean contains(GradeKey gradeKey) {
        requireNonNull(gradeKey);
        return internalMap.containsKey(gradeKey);
    }

    /**
     * Adds or updates {@code (GradeKey, Grade)} entry to the map.
     * The {@code GradeKey} must not already exist in the map.
     */
    public void add(GradeKey gradeKey, Grade grade) {
        requireAllNonNull(gradeKey, grade);
        internalMap.put(gradeKey, grade);
    }


    /**
     * Removes entry with the equivalent {@code GradeKey} key from the map.
     * The GradeKey must exist in the list.
     */
    public void remove(GradeKey toRemove) {
        requireNonNull(toRemove);
        if (!internalMap.containsKey(toRemove)) {
            throw new GradeKeyNotFoundException();
        }
        internalMap.remove(toRemove);
    }

    public void setGradeMap(GradeMap replacement) {
        requireNonNull(replacement);
        internalMap.putAll(replacement.internalMap);
    }

    /**
     * Replaces the contents of this list with {@code grades}.
     * {@code grades} will not contain duplicate .
     */
    public void setGradeMapWithMap(Map<GradeKey, Grade> grades) {
        requireAllNonNull(grades);
        if (!gradesAreUnique(grades)) {
            throw new DuplicateGradeKeyException();
        }
        internalMap.putAll(grades);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableMap<GradeKey, Grade> asUnmodifiableObservableMap() {
        return internalUnmodifiableMap;
    }

    @Override
    public Iterator<Grade> iterator() {
        return internalMap.values().iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeMap // instanceof handles nulls
                && internalMap.equals(((GradeMap) other).internalMap));
    }

    @Override
    public int hashCode() {
        return internalMap.hashCode();
    }

    /**
     * Returns true if {@code grades} contains only unique @code{GradeKey}s.
     */
    private boolean gradesAreUnique(Map<GradeKey, Grade> grades) {
        return true;
    }
}
