package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.PersonComparators.DEFAULT_COMPARATOR;

import seedu.address.model.Model;
import seedu.address.ui.MainPanelName;

/**
 * Lists all persons in the address book to the user.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reset search condition";
    public static final String MESSAGE_SUCCESS = "Reset search condition";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(DEFAULT_COMPARATOR);
        model.filterPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.List);
    }
}
