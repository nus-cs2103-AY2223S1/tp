package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.commons.core.index.Index;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.Person;

/**
 * View a person's particulars identified using their displayed index from the database.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer, not too large, i.e. < 2^31 - 1)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing Person's particulars: %1$s";

    private final Index index;

    public ViewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToView;
        try {
            personToView = lastShownList.get(index.getZeroBased());
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, personToView.getName()),
                    index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && index.equals(((ViewCommand) other).index));
    }

}
