package paymelah.logic.commands;

import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;
import paymelah.model.person.NameComparator;
import paymelah.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 * Sorts persons in the address book by the alphabetical order their names.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted persons by name";

    private final Comparator<Person> comparator = new NameComparator();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortAddressBookPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
