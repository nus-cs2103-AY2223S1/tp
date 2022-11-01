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
                    + " s/SORT_TYPE: Sorts list of persons in some order.\n"
                    + "Available SORT_TYPEs:\n"
                    + "a: sorts names of persons in alphabetical order.\n"
                    + "ra: sorts names of persons in reverse alphabetical order.\n"
                    + "ca: sorts persons chronologically by the time they were added into Task Book.\n"
                    + "p: sorts phone numbers of persons in ascending order.\n"
                    + "rp: sorts phone numbers of persons in descending order.\n";
    public static final String MESSAGE_SORT_TASK_SUCCESS = "Contacts sorted";
    public final String messageSortType;
    private final Comparator<Person> comparator;

    /**
     * Creates a contact sorting command.
     * @param comparator
     * @param sortType
     */
    public ContactSortCommand(Comparator<Person> comparator, String sortType) {
        this.comparator = comparator;
        this.messageSortType = sortType;
    }

    public Comparator<Person> getComparator() {
        return comparator;
    }
}
