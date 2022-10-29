package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.watson.model.Model;
import seedu.watson.model.student.Student;

/**
 * Marks the attendance of persons in address book with studentClass and indexNumber corresponding to the
 * user's arguments.
 */
public class MarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "markAtt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks attendance of all students in the "
                                               + "specified class with index numbers matching the user input\n"
                                               + "Parameters: d/DATE (DD/MM/YYYY) "
                                               + "c/CLASS (only one class should be entered)"
                                               + "ind/INDEX_NUMBERS (index numbers of students, separated by spaces)\n"
                                               + "Example: " + COMMAND_WORD + " d/26/10/2022 c/1A ind/1 5 8 10\n"
                                               + "Example: " + COMMAND_WORD + " d/05/08/2022 c/1B ind/4 7 9 10";

    private static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Attendance for students have been marked.";
    private static final String MESSAGE_MARK_ATTENDANCE_NONE = "No student's attendance has been marked.";

    private String date;
    private String studentClass;
    private List<String> indexNumbers;

    /**
     * Creates a MarkAttendanceCommand to mark the attendance of persons with studentClass and indexNumbers
     * maching the arguments.
     *
     * @param date The date to mark attendance.
     * @param studentClass The class that the student(s) belong to.
     * @param indexNumbers The list of index numbers of students to mark attendance for.
     */
    public MarkAttendanceCommand(String date, String studentClass, List<String> indexNumbers) {
        this.date = date;
        this.studentClass = studentClass;
        this.indexNumbers = indexNumbers;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    public CommandResult execute(Model model) {
        boolean anyStudentsAttendanceMarked = false;
        String markedAttendance = "";
        requireNonNull(model);
        // Now we're iterating through the whole list of saved people in Watsons and checking against their
        // indexNumber and studentClass to check if they should be marked attendance. Not the most efficient.

        //Predicate<Student> personIsInClassPredicate = model.createPersonIsInClassPredicate(studentClass);
        //model.updateFilteredPersonList(personIsInClassPredicate);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        List<Student> latestList = model.getFilteredPersonList();
        for (Student student : latestList) {
            if (indexNumbers.stream().anyMatch(ind -> ind.equals(student.getIndexNumberValue()))) {
                if (student.getStudentClass().isSameClass(studentClass)) {
                    student.getAttendance().updateAttendance(date + " 1");
                    anyStudentsAttendanceMarked = true;
                    markedAttendance = markedAttendance + student.getName().toString()
                                       + " " + student.getAttendance().toString() + ", ";
                }
            }
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return (anyStudentsAttendanceMarked)
               ? new CommandResult(MESSAGE_MARK_ATTENDANCE_SUCCESS + " " + markedAttendance)
               : new CommandResult(MESSAGE_MARK_ATTENDANCE_NONE);
    }
}
