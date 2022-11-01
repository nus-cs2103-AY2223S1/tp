package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.offer.Offer;

/**
 * Deletes an offer identified using it's displayed index from the address book.
 */
public class DeleteOfferCommand extends Command {

    public static final String COMMAND_WORD = "delO";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the offer identified by the index number used in the displayed offer list.\n"
        + "Parameters INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_OFFER_SUCCESS = "Deleted Offer: %1$s";

    private final Index targetIndex;

    public DeleteOfferCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Offer> lastShownList = model.getFilteredOfferList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Offer offerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOffer(offerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_OFFER_SUCCESS, offerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteOfferCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteOfferCommand) other).targetIndex)); // state check
    }
}
