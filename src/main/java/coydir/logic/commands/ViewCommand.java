package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.commons.core.index.Index;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.Person;

/**
 * view a person's particular identified using it's displayed index from the address book.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
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
                    true, index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
