package seedu.address.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Parent class of the 3 list commands
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";
    public static final String MESSAGE_USAGE = "To list all tasks in list: ls -a\n"
            + "To list all task under <module>: \n"
            + "EXAMPLE: ls --module cs2103t (case-insensitive for module name)\n"
            + "To list all unmarked (uncompleted tasks): ls -u\n"
            + "To list all marked (completed tasks): ls -m\n"
            + "To list all task with <tag>: \n"
            + "EXAMPLE: ls -t urgent \n"
            + "To list all tasks with deadline on or after <date>: \n"
            + "EXAMPLE: ls -d 2022-10-28";

    public final List<Predicate<Task>> predicates;

    public ListCommand(List<Predicate<Task>> predicates) {
        this.predicates = predicates;
    };

    public ListCommand() {
        this.predicates = List.of(PREDICATE_SHOW_ALL_PERSONS);
    };

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Task> predicate = predicates.stream()
                .reduce(x -> true, Predicate::and);
        model.updateFilteredPersonList(predicate);
        model.updateFilterStatus("Showing all tasks", true);
        predicates.stream().forEach(p -> {
            if (p.equals(PREDICATE_SHOW_ALL_PERSONS)) {
                model.updateFilterStatus("Showing all tasks");
            } else {
                model.updateFilterStatus(p.toString());
            }
        });
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && predicates.equals(((ListCommand) other).predicates)); // state check
    }
}
