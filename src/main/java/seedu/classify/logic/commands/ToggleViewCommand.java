package seedu.classify.logic.commands;

import seedu.classify.model.Model;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Student;

import static seedu.classify.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

/**
 * Toggles application between showing and hiding students' parent details
 */
public class ToggleViewCommand extends Command {
    public static final String COMMAND_WORD = "toggleView";

    public static final String MESSAGE_SUCCESS_HIDE = "Parent details are hidden";

    public static final String MESSAGE_SUCCESS_SHOW = "Parent details are shown";

    public static final String MESSAGE_TEMP= "Dummy text";

    @Override
    public CommandResult execute(Model model) {
//        model.toggleStudentListInfoConcise();
//        boolean isShowingParentDetails = !model.isStudentListInfoConcise();
//
//        if (isShowingParentDetails) {
//            return new CommandResult(MESSAGE_SUCCESS_SHOW, false, true, false);
//        }
//        return new CommandResult(MESSAGE_SUCCESS_HIDE, false, true, false);

        // I try to add and delete dummy variable?
        model.toggleStudentListInfoConcise();
        Student tempStudent = Student.dummyStudent();
        model.addStudent(tempStudent);
        model.deleteStudent(tempStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_TEMP);
    }

}
