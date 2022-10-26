package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Marks all mods of all batchmates as taken.
 */
public class ModMarkAllCommand extends ModCommand {

    public static final String COMMAND_WORD = "mark all";
    public static final String MESSAGE_SUCCESS = "Successfully marked all mods of all batchmates as taken.\n";

    /**
     * Constructs a command that marks all mods of all batchmates as taken.
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

        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person person : lastShownList) {
            person.markAllMods();
        }

        return new CommandResult(
                MESSAGE_SUCCESS, false, false, false, false);
    }

}
