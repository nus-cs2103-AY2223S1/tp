package taskbook.logic.commands.contacts;

import static java.util.Objects.requireNonNull;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.model.Model;

/**
 * Lists all persons in the task book to the user.
 */
public class ContactListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all persons";
    public static final String MESSAGE_USAGE = ContactCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
        + ": Shows a list of all contacts in the taskbook in alphabetical order.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonListPredicate(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
