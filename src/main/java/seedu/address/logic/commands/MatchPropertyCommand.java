package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STRICT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            + ": Matches a property with all buyers who are most suitable to purchase the property.\n"
            + "Pass in " + PREFIX_STRICT + " after the index for a stricter but possibly less matches."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STRICT;

    public static final String MESSAGE_MATCHED_PROPERTY_SUCCESS = "Matched Property: %1$s";

    private final Index targetIndex;
    private final boolean isMatchingAll;

    /**
     * Constructor for MatchPropertyCommand.
     *
     * @param targetIndex the index of the property to be matched.
     * @param isMatchingAll whether all the conditions specified must be satisfied in each of the resulting buyers.
     */
    public MatchPropertyCommand(Index targetIndex, boolean isMatchingAll) {
        this.targetIndex = targetIndex;
        this.isMatchingAll = isMatchingAll;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToMatch = lastShownList.get(targetIndex.getZeroBased());

        ArrayList<Predicate<Buyer>> predicatesList = new ArrayList<>();
        predicatesList.add(new FilterBuyerByPricePredicate(propertyToMatch.getPrice()));
        if (propertyToMatch.getCharacteristics().isPresent()) {
            predicatesList.add(
                    new FilterBuyerContainingAnyCharacteristicPredicate(propertyToMatch.getCharacteristics().get()));
        }

        Optional<Predicate<Buyer>> combinedPredicate;
        if (isMatchingAll) {
            combinedPredicate = predicatesList.stream().reduce(Predicate::and);
        } else {
            combinedPredicate = predicatesList.stream().reduce(Predicate::or);
        }

        // combinedPredicate must exist, since predicatesList should contain at least one predicate
        assert(combinedPredicate.isPresent());
        new MultiFlagFilterBuyersCommand(combinedPredicate.get()).execute(model);

        return new CommandResult(String.format(MESSAGE_MATCHED_PROPERTY_SUCCESS, propertyToMatch));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchPropertyCommand // instanceof handles nulls
                && targetIndex.equals(((MatchPropertyCommand) other).targetIndex)); // state check
    }
}
