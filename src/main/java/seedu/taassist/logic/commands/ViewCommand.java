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
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;

/**
 * Displays grades of a student for the current module class.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays grades of a student for the current module class. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_HEADER = "Grades for %1$s:";

    private final Index index;

    public ViewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(MESSAGE_NOT_IN_FOCUS_MODE);
        }

        ModuleClass focusedClass = model.getFocusedClass();
        List<Student> lastShownList = model.getFilteredStudentList();

        Student student;
        try {
            student = ParserStudentIndexUtil.parseStudentFromIndex(index, lastShownList);
        } catch (ParseException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX));
        }

        List<SessionData> sessionDataList = student.findStudentModuleData(focusedClass).getSessionData();

        StringJoiner sj = new StringJoiner("\n");
        sj.add(String.format(MESSAGE_HEADER, student.getName()));
        for (int i = 0; i < sessionDataList.size(); ++i) {
            SessionData sessionData = sessionDataList.get(i);
            sj.add(String.format("%d. %s: %s", i + 1, sessionData.getSession(), sessionData.getData()));
        }

        return new CommandResult(sj.toString());
    }
}

