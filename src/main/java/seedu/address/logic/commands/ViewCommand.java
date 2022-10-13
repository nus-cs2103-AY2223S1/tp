package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.SamePersonPredicate;

/**
 * Views the details of an existing person in the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the details of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Viewed Person: %1$s";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to view
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(index.getZeroBased());

        model.updateViewedPersonList(new SamePersonPredicate(personToView));
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToView.getName().fullName));
    }

}
