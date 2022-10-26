package seedu.address.logic.commands;


import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Parameters: INDEX (must be a positive integer) ";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Showing Person: %1$s";

    private final Index index;

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
        Person person = lastShownList.get(index.getZeroBased());
        model.updateCurrentlyViewedPerson(person);

        return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS,
                person.getName()), false, false, false, true);
    }
}
