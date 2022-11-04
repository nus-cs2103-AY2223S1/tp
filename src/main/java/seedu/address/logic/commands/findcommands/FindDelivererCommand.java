package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindDelivererCommand extends FindCommand {

    /**
     * Constructs a FindDelivererCommand, which has three predicates.
     * Keyword matching is case insensitive.
     *
     * @param dPredicate A Predicate for Deliverers.
     * @return FindDelivererCommand.
     */
    public FindDelivererCommand(Predicate<Deliverer> dPredicate) {
        super(null, dPredicate, null, PersonCategory.DELIVERER);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDelivererList(getDelivererPredicate());
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                model.getFilteredDelivererList().size()));
    }
}
