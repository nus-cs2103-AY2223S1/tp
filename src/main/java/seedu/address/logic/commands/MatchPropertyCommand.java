package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.FilterBuyerByPricePredicate;
import seedu.address.model.buyer.FilterBuyerContainingAnyCharacteristicPredicate;
import seedu.address.model.property.Property;

/**
 * Matches {@code properties} to {@code buyers} that either has a price within the buyer's price range,
 * or has at least 1 characteristic that the buyer has as well.
 */
public class MatchPropertyCommand extends Command {

    public static final String COMMAND_WORD = "matchprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            // TODO: Change description?
            + ": Matches a property with all buyers who are most suitable to purchase the property\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MATCHED_PROPERTY_SUCCESS = "Matched Property: %1$s";

    private final Index targetIndex;

    public MatchPropertyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToMatch = lastShownList.get(targetIndex.getZeroBased());

        // Start constructing the predicate
        Predicate<Buyer> pricePredicate = new FilterBuyerByPricePredicate(propertyToMatch.getPrice());
        Predicate<Buyer> propertyPredicate =
                propertyToMatch.getCharacteristics().isPresent()
                ? new FilterBuyerContainingAnyCharacteristicPredicate(propertyToMatch.getCharacteristics().get())
                : null;
        Predicate<Buyer> logicalOrPredicate = pricePredicate.or(propertyPredicate);
        // N.B. Execution of a command within a command - does this break abstraction?
        new MultiFlagFilterBuyersCommand(logicalOrPredicate).execute(model);

        return new CommandResult(String.format(MESSAGE_MATCHED_PROPERTY_SUCCESS, propertyToMatch));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchPropertyCommand // instanceof handles nulls
                && targetIndex.equals(((MatchPropertyCommand) other).targetIndex)); // state check
    }
}
