package seedu.taassist.logic.parser;

import java.util.ArrayList;
import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.student.Student;

/**
 * Contains utility methods used for retrieving students from a list of students based on their indices.
 * These utility methods are used in the various *Parser classes.
 */
public class ParserStudentIndexUtil {

    /**
     * Retrieves the students at the relative one-based indices from the list.
     *
     * @param indices One-based indices.
     * @param students Full list of students.
     * @return List containing only students specified by their index position in {@code students}.
     * @throws ParseException If any of the indices are out of range.
     */
    public static List<Student> parseStudentsFromIndices(List<Index> indices,
                                                         List<Student> students) throws ParseException {
        final int listSize = students.size();

        List<Student> indexedStudents = new ArrayList<>();
        for (Index index : indices) {
            int zeroBasedIndex = index.getZeroBased();
            if (zeroBasedIndex < listSize) {
                indexedStudents.add(students.get(zeroBasedIndex));
            } else {
                throw new ParseException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }
        return indexedStudents;
    }

    /**
     * Retrieves the student at the relative one-based index from the list.
     *
     * @param index One-based index.
     * @param students Full list of students.
     * @return Student at index position {@code index} of {@code students}.
     * @throws ParseException If {@code index} is out of range.
     */
    public static Student parseStudentFromIndex(Index index, List<Student> students) throws ParseException {
        final int listSize = students.size();
        int zeroBasedIndex = index.getZeroBased();
        if (zeroBasedIndex < listSize) {
            return students.get(zeroBasedIndex);
        } else {
            throw new ParseException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
    }
}
