package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.boba.model.BobaBotModel.PREDICATE_SHOW_ALL_PERSONS;

import seedu.boba.model.BobaBotModel;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) {
        requireNonNull(bobaBotModel);
        bobaBotModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
