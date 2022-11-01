package taskbook.logic.commands.contacts;

import static java.util.Objects.requireNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_QUERY;

import java.util.function.Predicate;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Person;

/**
 * Finds all contacts matching a given query exactly in their names.
 * Query matching is case insensitive.
 */
public class ContactFindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_SUCCESS = "Displaying matching persons.";
    public static final String MESSAGE_USAGE =
            ContactCategoryParser
                    .CATEGORY_WORD + " " + COMMAND_WORD
                    + ": Searches all contact names that contain the given query.\n"
                    + "Parameters:\n"
                    + PREFIX_QUERY + "QUERY\n"
                    + "Only names with exact matches with QUERY will be displayed. Can be multiple words. "
                    + "Case insensitive.\n";
    private Predicate<Person> predicate;
    private String query;

    /**
     * Creates a ContactFindCommand to search for persons with the specified {@code Predicate<Person> query}.
     * @param query
     */
    public ContactFindCommand(Predicate<Person> predicate, String query) {
        requireNonNull(predicate);
        requireNonNull(query);
        this.predicate = predicate;
        this.query = query;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredPersonListPredicate(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS + "\n"
                + model.getFilteredPersonList().size() + " persons listed.\n"
                + "Query: " + query));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ContactFindCommand)) {
            return false;
        }
        ContactFindCommand command = (ContactFindCommand) other;
        return this.query.toUpperCase().equals(command.query.toUpperCase());
    }
}
