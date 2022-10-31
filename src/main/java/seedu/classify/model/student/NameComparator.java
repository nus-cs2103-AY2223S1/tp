package seedu.classify.model.student;

import java.util.Comparator;

/**
 * Compares the names of two students lexicographically
 */
public class NameComparator implements Comparator<Student> {

    private static NameComparator instance = null;

    private NameComparator() {
    }

    /**
     * Returns the single instance of the NameComparator.
     */
    public static NameComparator getNameComparator() {
        if (instance == null) {
            instance = new NameComparator();
        }
        return instance;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getStudentName().toString().compareTo(o2.getStudentName().toString());
    }
}
