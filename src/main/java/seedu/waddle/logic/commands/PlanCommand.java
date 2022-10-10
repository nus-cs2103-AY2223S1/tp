package seedu.waddle.logic.commands;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class PlanCommand extends Command {
    private Index index;

    public static final String COMMAND_WORD = "plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": selects an itinerary for planning "
            + "by the index number used in the last itineraries listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    /**
     * @param index of the itinerary to plan
     */
    public PlanCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PlanCommand)) {
            return false;
        }

        // state check
        PlanCommand e = (PlanCommand) other;
        return index.equals(e.index);
    }
}