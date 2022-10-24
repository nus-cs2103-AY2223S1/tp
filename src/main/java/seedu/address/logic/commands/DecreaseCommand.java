package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reward;

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

    private String decrementReward;

    private Phone phoneIdentifier = null;
    private Email emailIdentifier = null;

    private final EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

    /**
     * @param phoneIdentifier current phone number of the person
     * @param rewardPoints amount of reward points to decrement by
     */
    public DecreaseCommand(Phone phoneIdentifier, String rewardPoints) {
        requireNonNull(phoneIdentifier);

        this.phoneIdentifier = phoneIdentifier;
        this.decrementReward = rewardPoints;
    }

    /**
     * @param emailIdentifier current email address of the person
     * @param rewardPoints amount of reward points to decrement by
     */
    public DecreaseCommand(Email emailIdentifier, String rewardPoints) {
        requireNonNull(emailIdentifier);

        this.emailIdentifier = emailIdentifier;
        this.decrementReward = rewardPoints;
    }

    @Override
    public CommandResult execute(Model model) throws ParseException, CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        Reward currentReward = isNull(phoneIdentifier)
                ? model.getCurrentReward(emailIdentifier)
                : model.getCurrentReward(phoneIdentifier);
        int newReward = Integer.parseInt(currentReward.value) - Integer.parseInt(decrementReward);
        if (newReward < 0) {
            throw new ParseException(Reward.MESSAGE_CONSTRAINTS);
        }
        editPersonDescriptor.setReward(ParserUtil.parseReward(String.valueOf(newReward)));
        EditCommand editCommand = isNull(phoneIdentifier)
                ? new EditCommand(emailIdentifier, editPersonDescriptor)
                : new EditCommand(phoneIdentifier, editPersonDescriptor);
        return editCommand.execute(model);
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
