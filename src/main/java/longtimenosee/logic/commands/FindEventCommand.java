package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DATE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.core.Messages;
import longtimenosee.model.Model;
import longtimenosee.model.event.Event;

/**
 * Finds and lists all Events in address book which match the given metrics.
 */
public class FindEventCommand extends Command {

    public static final String COMMAND_WORD = "findEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all events that match the given metrics and "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_NAME + "PERSON_NAME] "
            + "[" + PREFIX_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " desc/meeting n/Alice";

    private final List<Predicate<Event>> predicates;

    /**
     * Creates a FindEventCommand object.
     *
     * @param predicates checks if the specified metrics match any event.
     */
    public FindEventCommand(List<Predicate<Event>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Event> finalPredicate = predicates.get(0);
        for (Predicate<Event> predicate : predicates) {
            if (!predicate.equals(predicates.get(0))) {
                finalPredicate = finalPredicate.and(predicate);
            }
        }
        model.updateFilteredEventList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof FindEventCommand) {
                return predicates.equals(((FindEventCommand) other).predicates);
            } else {
                return false;
            }
        }
    }
}
