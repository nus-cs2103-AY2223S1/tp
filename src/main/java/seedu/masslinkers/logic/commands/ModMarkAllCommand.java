package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.Student;

//@@author carriezhengjr
/**
 * Marks all mods of every batchmate as taken.
 */
public class ModMarkAllCommand extends ModCommand {

    public static final String COMMAND_WORD = "mark all";
    public static final String MESSAGE_SUCCESS = "Successfully marked all mods of every batchmate as taken.";

    /**
     * Constructs a command that marks all mods of every batchmate as taken.
     */
    public ModMarkAllCommand() {}

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();

        for (Student student : lastShownList) {
            student.markAllMods();
        }

        return new CommandResult(
                MESSAGE_SUCCESS, false, false, false, false);
    }

}
