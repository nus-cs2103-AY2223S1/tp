package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STRICT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.buyer.FilterBuyersCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.FilterBuyerByPricePredicate;
import seedu.address.model.buyer.FilterBuyerContainingAllCharacteristicsPredicate;
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

    public static final String MESSAGE_MATCHED_PROPERTY_SUCCESS = "%s matched buyers for the property:\n%s";

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
            if (isMatchingAll) {
                predicatesList.add(new FilterBuyerContainingAllCharacteristicsPredicate(
                        propertyToMatch.getCharacteristics().get()));
            } else {
                predicatesList.add(new FilterBuyerContainingAnyCharacteristicPredicate(
                        propertyToMatch.getCharacteristics().get()));
            }
        }

        // predicatesList must not be empty, since at least FilterBuyerByPricePredicate should be added
        assert(!predicatesList.isEmpty());

        Predicate<Buyer> combinedPredicate;
        combinedPredicate = predicatesList.stream().reduce(Predicate::and).get();

        new FilterBuyersCommand(combinedPredicate).execute(model);

        return new CommandResult(String.format(MESSAGE_MATCHED_PROPERTY_SUCCESS,
                model.getFilteredBuyerList().size(), propertyToMatch));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchPropertyCommand // instanceof handles nulls
                && targetIndex.equals(((MatchPropertyCommand) other).targetIndex))
                && isMatchingAll == (((MatchPropertyCommand) other).isMatchingAll); // state check
    }
}
