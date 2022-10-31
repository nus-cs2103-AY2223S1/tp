package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.watson.model.Model;
import seedu.watson.model.student.Student;

/**
 * Marks the attendance of persons in address book with studentClass and indexNumber corresponding to the
 * user's arguments.
 */
public class AttendanceCommand extends Command {

    // test command: markAtt d/31/10/2022 c/1.2 ind/1 2

    public static final String COMMAND_WORD = "markAtt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks attendance of all students in the "
                                               + "specified class with index numbers matching the user input\n"
                                               + "Parameters: d/DATE (DD/MM/YYYY) "
                                               + "c/CLASS (only one class should be entered)"
                                               + "ind/INDEX_NUMBERS (index numbers of students, separated by spaces)\n"
                                               + "Example: " + COMMAND_WORD + " d/26/10/2022 c/1A ind/1 5 8 10\n"
                                               + "Example: " + COMMAND_WORD + " d/05/08/2022 c/1B ind/4 7 9 10";

    private static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Attendance for the specified"
        + " students has been marked!";
    private static final String MESSAGE_MARK_ATTENDANCE_NONE = "No student's attendance has been marked.";

    private final String date;
    private final String studentClass;
    private final List<Integer> indexNumbers;

    /**
     * Creates a AttendanceCommand to mark the attendance of persons with studentClass and indexNumbers
     * matching the arguments.
     *
     * @param date The date to mark attendance.
     * @param studentClass The class that the student(s) belong to.
     * @param indexNumbers The list of index numbers of students to mark attendance for.
     */
    public AttendanceCommand(String date, String studentClass, List<String> indexNumbers) {
        this.date = date;
        this.studentClass = studentClass;

        List<Integer> ls = new ArrayList<>();

        String[] split = indexNumbers.get(0).split(" ");

        for (String s : split) {
            ls.add(Integer.parseInt(s));
        }
        this.indexNumbers = ls;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StringBuilder markedAttendance = new StringBuilder();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        int curIdx = 1;
        List<Student> latestList = model.getFilteredPersonList();
        boolean anyStudentsAttendanceMarked = !latestList.isEmpty();

        for (Student student : latestList) {
            if (student.getStudentClass().toString().equals(studentClass)) {
                if (indexNumbers.contains(curIdx)) {
                    student.getAttendance().updateAttendance(date + " 1");

                    markedAttendance.append("- ")
                        .append(student.getName().toString())
                        .append(": Present")
                        .append("\n");
                } else {
                    student.getAttendance().updateAttendance(date + " 0");

                    markedAttendance.append("- ")
                        .append(student.getName().toString())
                        .append(": Absent")
                        .append("\n");
                }
                model.setPerson(student, student);
            }
            curIdx++;
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return (anyStudentsAttendanceMarked)
               ? new CommandResult(MESSAGE_MARK_ATTENDANCE_SUCCESS
            + "\n" + markedAttendance.toString().trim())
               : new CommandResult(MESSAGE_MARK_ATTENDANCE_NONE);
    }
}
