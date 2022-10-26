package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTOR;

import seedu.address.logic.commands.CommandResult.CommandType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTutorCommand extends ListCommand {
    public static final String COMMAND_LIST_TUTOR_STRING = "list tutor";

    public static final String MESSAGE_SUCCESS = "Listed all tutors";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentListType(Model.ListType.TUTOR_LIST);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTOR);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.LIST);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListTutorCommand;
    }
}
