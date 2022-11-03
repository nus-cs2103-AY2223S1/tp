package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;

import java.util.List;
import java.util.StringJoiner;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.SessionData;
import seedu.taassist.model.student.Student;

/**
 * Displays grades of a student for the current module class.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = "> Displays the grades of a student for the current module class.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Grades for [ %1$s ]:";
    public static final String MESSAGE_EMPTY_GRADES_LIST = "No grades have been given to [ %1$s ]. "
            + "Add grades with [ " + GradeCommand.COMMAND_WORD + " ] command.";

    private final Index index;

    /**
     * Returns a ViewCommand object with the specified index of the student to view.
     *
     * @param index Index of the student to view.
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();
        List<Student> lastShownList = model.getFilteredStudentList();

        Student student;
        try {
            student = ParserStudentIndexUtil.parseStudentFromIndex(index, lastShownList);
        } catch (ParseException e) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        List<SessionData> sessionDataList = student.findStudentModuleData(focusedClass)
                .orElseThrow(AssertionError::new)
                .getSessionDataList();

        return new CommandResult(getCommandMessage(sessionDataList, student));
    }

    public static String getCommandMessage(List<SessionData> sessionDataList, Student student) {
        if (sessionDataList.isEmpty()) {
            return String.format(MESSAGE_EMPTY_GRADES_LIST, student.getName());
        }
        StringJoiner sj = new StringJoiner("\n");
        sj.add(String.format(MESSAGE_SUCCESS, student.getName()));
        for (int i = 0; i < sessionDataList.size(); ++i) {
            SessionData sessionData = sessionDataList.get(i);
            sj.add(String.format("%d. %s: %s", i + 1, sessionData.getSessionName(), sessionData.getGrade()));
        }
        return sj.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && index.equals(((ViewCommand) other).index));
    }
}
