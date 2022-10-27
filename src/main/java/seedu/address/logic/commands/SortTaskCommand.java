package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Order;
import seedu.address.model.Model;
import seedu.address.model.team.Task;

@CommandLine.Command(name = "task", aliases = {"ta"}, mixinStandardHelpOptions = true)
public class SortTaskCommand extends Command {
    public static final String COMMAND_WORD = "sort task";

    public static final String MESSAGE_SUCCESS = "Sorted tasks successfully";

    @CommandLine.Parameters(arity = "1")
    private Order order;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public SortTaskCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        Comparator<Task> comparator;
        switch (order) {
        case ASC:
            comparator = new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return o1.getName().toString().compareTo(o2.getName().toString());
                }
            };
            break;
        case DSC:
            comparator = new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return o2.getName().toString().compareTo(o1.getName().toString());
                }
            };
            break;
        case RES:
            comparator = null;
            break;
        default:
            throw new CommandException("Should not reach here");
        }

        model.getTeam().sortTasks(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTaskCommand // instanceof handles nulls
                && order.equals(((SortTaskCommand) other).order));
    }
}
