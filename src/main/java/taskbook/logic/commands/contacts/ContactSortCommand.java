package taskbook.logic.commands.contacts;

import java.util.Comparator;

import taskbook.logic.commands.Command;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.model.person.Person;

/**
 * Sorts the list of persons
 */
public abstract class ContactSortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE =
            ContactCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
                    + ": Sorts list of persons in some order.\n"
                    + "Available sorting commands:\n"
                    + "a: sorts names of persons in alphabetical order"
                    + "ca: sorts persons chronologically by the time they were added into Task Book";
    public static final String MESSAGE_SORT_TASK_SUCCESS = "Persons sorted";
    private final Comparator<Person> comparator;

    public ContactSortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Person> getComparator() {
        return comparator;
    }
}
