package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import picocli.CommandLine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Order;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

@CommandLine.Command(name = "member", aliases = {"m"}, mixinStandardHelpOptions = true)
public class SortMemberCommand extends Command {

    public static final String COMMAND_WORD = "sort member";

    public static final String MESSAGE_SUCCESS = "Sorted members successfully";

    @CommandLine.Parameters(arity = "1")
    private Order order;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec commandSpec;

    public SortMemberCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandSpec.commandLine().isUsageHelpRequested()) {
            return new CommandResult(commandSpec.commandLine().getUsageMessage());
        }
        requireNonNull(model);
        Comparator<Person> comparator;
        switch (order) {
        case ASC:
            comparator = new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getName().toString().compareTo(o2.getName().toString());
                }
            };
            break;
        case DSC:
            comparator = new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
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

        model.getTeam().sortMembers(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortMemberCommand // instanceof handles nulls
                && order.equals(((SortMemberCommand) other).order));
    }
}
