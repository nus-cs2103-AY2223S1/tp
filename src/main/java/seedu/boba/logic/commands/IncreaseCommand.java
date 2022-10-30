package seedu.boba.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.boba.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.NoSuchElementException;

import seedu.boba.commons.core.Messages;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.ParserUtil;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.Model;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;

/**
 * Increases the reward points of an existing Customer in bobaBot.
 */
public class IncreaseCommand extends Command {

    public static final String COMMAND_WORD = "incr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Increases the reward points of the Customer identified "
            + "by the phone number/ email address used to register for membership. "
            + "Existing reward points will be overwritten by the input values.\n"
            + "Parameters: REWARD (must be a POSITIVE integer) "
            + "Parameters: p/PHONE_NUMBER or e/EMAIL \n"
            + "Example: " + COMMAND_WORD + " 1000" + " p/98349032  or  "
            + COMMAND_WORD + " 500" + " e/example@gmail.com";

    private String incrementReward;

    private Phone phoneIdentifier = null;
    private Email emailIdentifier = null;

    private final EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

    /**
     * @param phoneIdentifier current phone number of the customer
     * @param rewardPoints amount of reward points to increment by
     */
    public IncreaseCommand(Phone phoneIdentifier, String rewardPoints) {
        requireNonNull(phoneIdentifier);

        this.phoneIdentifier = phoneIdentifier;
        this.incrementReward = rewardPoints;
    }

    /**
     * @param emailIdentifier current email address of the customer
     * @param rewardPoints amount of reward points to increment by
     */
    public IncreaseCommand(Email emailIdentifier, String rewardPoints) {
        requireNonNull(emailIdentifier);

        this.emailIdentifier = emailIdentifier;
        this.incrementReward = rewardPoints;
    }

    @Override
    public CommandResult execute(Model model) throws ParseException, CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        try {
            Reward currentReward = isNull(phoneIdentifier)
                    ? model.getCurrentReward(emailIdentifier)
                    : model.getCurrentReward(phoneIdentifier);
            editPersonDescriptor.setReward(ParserUtil.parseReward(
                    String.valueOf(Integer.parseInt(currentReward.value) + Integer.parseInt(incrementReward))));
            EditCommand editCommand = isNull(phoneIdentifier)
                    ? new EditCommand(emailIdentifier, editPersonDescriptor)
                    : new EditCommand(phoneIdentifier, editPersonDescriptor);
            return editCommand.execute(model);
        } catch (NoSuchElementException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_INFORMATION);
        } catch (IllegalArgumentException e) {
            throw new CommandException(Reward.MESSAGE_MAX_EXCEEDED);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IncreaseCommand)) {
            return false;
        }

        // state check
        IncreaseCommand e = (IncreaseCommand) other;
        return (isNull(emailIdentifier) && phoneIdentifier.equals(e.phoneIdentifier)
                || isNull(phoneIdentifier) && emailIdentifier.equals(e.emailIdentifier))
                && incrementReward.equals(e.incrementReward)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
