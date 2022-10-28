package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * Finds and list all eatries in food guide whose price matches the argument keyword.
 */
public class FindPriceCommand extends Command {

    public static final String COMMAND_WORD = "findPrice";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eatries whose price matches "
            + "the specified keywords and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " $$";

    private final Predicate<Eatery> predicate;

    public FindPriceCommand(Predicate<Eatery> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEateryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EATERIES_LISTED_OVERVIEW, model.getFilteredEateryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
        || (other instanceof FindPriceCommand // instanceof handles nulls
        && predicate.equals(((FindPriceCommand) other).predicate)); // state check
    }
}
