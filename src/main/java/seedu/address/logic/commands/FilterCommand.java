package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipHasApplicationStatusPredicate;

import static java.util.Objects.requireNonNull;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter applications based on status.\n"
            + "Parameters: [STATUS]\n"
            + "Valid filters: \"accepted\" \"applied\" \"interviewed\" \"rejected\"\n"
            + "Example: " + COMMAND_WORD + " accepted";

    private final InternshipHasApplicationStatusPredicate predicate;

    public FilterCommand(InternshipHasApplicationStatusPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand fc = (FilterCommand) other;
        return predicate.equals(fc.predicate);
    }
}
