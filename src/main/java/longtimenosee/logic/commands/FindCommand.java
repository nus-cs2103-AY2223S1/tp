package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.core.Messages;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;

/**
 * Finds and lists all persons in address book who matches the given metrics.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons that match the given metrics and "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: (n/NAME)/(p/PHONE)/(e/EMAIL)/(a/ADDRESS)/(t/TAG)…\n"
            + "Example: " + COMMAND_WORD + " n/alice p/12341234";

    private final List<Predicate<Person>> predicates;

    /**
     * Creates a FindCommand object.
     *
     * @param predicates check if the specified metrics match any contact.
     */
    public FindCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> finalPredicate = predicates.get(0);
        for (Predicate<Person> predicate : predicates) {
            if (!predicate.equals(predicates.get(0))) {
                finalPredicate = finalPredicate.and(predicate);
            }
        }
        model.updateFilteredPersonList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
