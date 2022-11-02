package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STRICT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.FilterPropsByPricePredicate;
import seedu.address.model.property.FilterPropsContainingAllCharacteristicsPredicate;
import seedu.address.model.property.FilterPropsContainingAnyCharacteristicPredicate;
import seedu.address.model.property.Property;

/**
 * Matches {@code properties} to {@code buyers} that either has a price within the buyer's price range,
 * or has at least 1 characteristic that the buyer has as well.
 */
public class MatchBuyerCommand extends Command {

    public static final String COMMAND_WORD = "matchbuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Matches a buyer with all properties that satisfy the buyer's budget"
            + " and at least one of the buyer's desired characteristics.\n"
            + "Pass in " + PREFIX_STRICT + " to reduce the matches to only properties that satisfy all of the"
            + " buyer's desired characteristics.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_STRICT;

    public static final String MESSAGE_MATCHED_BUYER_SUCCESS = "%s matched properties for the buyer:\n%s";

    private final Index targetIndex;
    private final boolean isMatchingAll;

    /**
     * Constructor for MatchBuyerCommand.
     *
     * @param targetIndex the index of the property to be matched.
     * @param isMatchingAll whether all the conditions specified must be satisfied in each of the resulting buyers.
     */
    public MatchBuyerCommand(Index targetIndex, boolean isMatchingAll) {
        this.targetIndex = targetIndex;
        this.isMatchingAll = isMatchingAll;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Buyer> lastShownList = model.getFilteredBuyerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
        }

        Buyer buyerToMatch = lastShownList.get(targetIndex.getZeroBased());

        ArrayList<Predicate<Property>> predicatesList = new ArrayList<>();

        if (buyerToMatch.getDesiredCharacteristics().isEmpty() && buyerToMatch.getPriceRange().isEmpty()) {
            throw new
                    CommandException(Messages.MESSAGE_INVALID_PROPERTY_BUYER_MATCH);
        } else {
            if (buyerToMatch.getPriceRange().isPresent()) {
                predicatesList.add(new FilterPropsByPricePredicate(buyerToMatch.getPriceRange().get()));
            }

            if (buyerToMatch.getDesiredCharacteristics().isPresent()) {
                if (isMatchingAll) {
                    predicatesList.add(new FilterPropsContainingAllCharacteristicsPredicate(
                            buyerToMatch.getDesiredCharacteristics().get()));
                } else {
                    predicatesList.add(new FilterPropsContainingAnyCharacteristicPredicate(
                            buyerToMatch.getDesiredCharacteristics().get()));
                }
            }
        }

        assert(!predicatesList.isEmpty());

        Predicate<Property> combinedPredicate;
        combinedPredicate = predicatesList.stream().reduce(Predicate::and).get();

        new FilterPropertiesCommand(combinedPredicate).execute(model);

        return new CommandResult(String.format(MESSAGE_MATCHED_BUYER_SUCCESS,
                model.getFilteredPropertyList().size(), buyerToMatch));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchBuyerCommand // instanceof handles nulls
                && targetIndex.equals(((MatchBuyerCommand) other).targetIndex))
                && isMatchingAll == (((MatchBuyerCommand) other).isMatchingAll);
    }
}
