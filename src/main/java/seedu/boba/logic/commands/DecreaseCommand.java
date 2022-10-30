package seedu.boba.logic.commands;

import seedu.boba.commons.core.Messages;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.ParserUtil;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;

import java.util.NoSuchElementException;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.boba.model.BobaBotModel.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Decreases the reward points of an existing Customer in bobaBot.
 */
public class DecreaseCommand extends Command {

    public static final String COMMAND_WORD = "decr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Decreases the reward points of the Customer identified "
            + "by the phone number/ email address used to register for membership. "
            + "Existing reward points will be overwritten by the input values.\n"
            + "Parameters: REWARD (must be a POSITIVE integer) "
            + "Parameters: p/PHONE_NUMBER or e/EMAIL \n"
            + "Example: " + COMMAND_WORD + " 1000" + " p/98349032  or  "
            + COMMAND_WORD + " 500" + " e/example@gmail.com";

    private final String decrementReward;

    private Phone phoneIdentifier = null;
    private Email emailIdentifier = null;

    private final EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

    /**
     * @param phoneIdentifier current phone number of the customer
     * @param rewardPoints    amount of reward points to decrement by
     */
    public DecreaseCommand(Phone phoneIdentifier, String rewardPoints) {
        requireNonNull(phoneIdentifier);

        this.phoneIdentifier = phoneIdentifier;
        this.decrementReward = rewardPoints;
    }

    /**
     * @param emailIdentifier current email address of the customer
     * @param rewardPoints    amount of reward points to decrement by
     */
    public DecreaseCommand(Email emailIdentifier, String rewardPoints) {
        requireNonNull(emailIdentifier);

        this.emailIdentifier = emailIdentifier;
        this.decrementReward = rewardPoints;
    }

    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws ParseException, CommandException {
        requireNonNull(bobaBotModel);
        bobaBotModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        try {
            Reward currentReward = isNull(phoneIdentifier)
                    ? bobaBotModel.getCurrentReward(emailIdentifier)
                    : bobaBotModel.getCurrentReward(phoneIdentifier);
            int newReward = Integer.parseInt(currentReward.value) - Integer.parseInt(decrementReward);
            if (newReward < 0) {
                throw new ParseException(Reward.MESSAGE_NEGATIVE);
            }
            editPersonDescriptor.setReward(ParserUtil.parseReward(String.valueOf(newReward)));
            EditCommand editCommand = isNull(phoneIdentifier)
                    ? new EditCommand(emailIdentifier, editPersonDescriptor)
                    : new EditCommand(phoneIdentifier, editPersonDescriptor);
            return editCommand.execute(bobaBotModel);
        } catch (NoSuchElementException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DecreaseCommand)) {
            return false;
        }

        // state check
        DecreaseCommand e = (DecreaseCommand) other;
        return (isNull(emailIdentifier) && phoneIdentifier.equals(e.phoneIdentifier)
                || isNull(phoneIdentifier) && emailIdentifier.equals(e.emailIdentifier))
                && decrementReward.equals(e.decrementReward)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
