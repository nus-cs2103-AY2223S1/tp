package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.model.Model.PREDICATE_SHOW_ALL_PEOPLE;

import java.util.ArrayList;

import swift.logic.parser.Prefix;
import swift.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "list_contact";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>();

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PEOPLE);
        return new CommandResult(MESSAGE_SUCCESS, CommandType.CONTACTS);
    }
}
