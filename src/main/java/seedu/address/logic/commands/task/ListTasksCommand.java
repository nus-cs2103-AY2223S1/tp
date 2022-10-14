package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lists all tasks in the task panel to the user that match the specified requirements.
 */
public class ListTasksCommand extends TaskCommand {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Lists all tasks that satisfy the specified requirements.\n"
            + "Parameters: "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + PREFIX_CONTACT + "2 "
            + PREFIX_CONTACT + "3 ";

    public static final String MESSAGE_SUCCESS = "Found %1$s tasks %2$s %3$s";

    private final Optional<String> keywordFilter;
    private final Set<Index> personIndexes = new HashSet<>();

    public ListTasksCommand(Optional<String> keywordFilter, Set<Index> personsIndexes) {
        requireNonNull(personsIndexes);
        this.keywordFilter = keywordFilter;
        this.personIndexes.addAll(personsIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonsList = model.getFilteredPersonList();
        Set<Contact> contacts = new HashSet<>();
        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= lastShownPersonsList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Contact c =
                    new Contact(lastShownPersonsList.get(personIndex.getZeroBased()).getName().fullName);
            contacts.add(c);
        }

        final Predicate<Task> filter = task -> {
                boolean isIncluded = true;

                if (keywordFilter.isPresent()) {
                    isIncluded = isIncluded && task.getTitle().value.contains(keywordFilter.get());
                }

                isIncluded = isIncluded && task.getAssignedContacts().containsAll(contacts);

                return isIncluded;
        };

        model.updateFilteredTaskList(filter);
        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        model.getFilteredTaskList().size(),
                        keywordFilter.map(s -> String.format("containing '%s'", s)).orElse("").trim(),
                        contacts.isEmpty()
                                ? ""
                                : String.format("that are assigned to %s",
                                contacts.stream().map(c -> c.contactName).collect(Collectors.joining(", ")))
                )
        );
    }

}
