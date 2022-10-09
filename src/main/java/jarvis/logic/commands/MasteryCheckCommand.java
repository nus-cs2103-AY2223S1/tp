package jarvis.logic.commands;

import jarvis.commons.core.Messages;
import jarvis.commons.core.index.Index;
import jarvis.logic.commands.exceptions.CommandException;
import jarvis.logic.parser.MasteryCheckCommandParser;
import jarvis.model.MasteryCheckStatus;
import jarvis.model.Model;
import jarvis.model.Student;

import java.util.List;

import static jarvis.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

public class MasteryCheckCommand extends Command {

    public static final String COMMAND_WORD = "mc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a student's mastery check result.\n"
            + "Usage: " + COMMAND_WORD + " STUDENT_INDEX "
            + PREFIX_MC_NUM + "MC_NUM " + PREFIX_MC_RES + "MC_RESULT\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MC_NUM + "1 " + PREFIX_MC_RES + "PASS";

    private final Index index;
    private final MasteryCheckStatus.MasteryCheckResult mcResult;

    public MasteryCheckCommand(Index index, MasteryCheckStatus.MasteryCheckResult mcResult) {
        this.index = index;
        this.mcResult = mcResult;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        studentToEdit.updateMcStatus(mcResult);
        return new CommandResult(String.format("Updated " + mcResult + " for " + studentToEdit));
    }
}
