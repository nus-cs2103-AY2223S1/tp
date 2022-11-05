package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.MainPanelName;

/**
 * Sorts and lists all person in address book with comparator given.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons with specified fields\n"
            + "Parameters: (" + buildParameters(PREFIX_NAME, PREFIX_ROLE, PREFIX_ADDRESS) + ")[/desc]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME.getAlias().replace("/", "")
            + ", " + COMMAND_WORD + " " + PREFIX_NAME + "desc";

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    private static String buildParameters(Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .map(s -> s.getAlias().replace("/", ""))
                .collect(Collectors.joining("|"));
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getSortedFilteredPersonList().size()));
    }

    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.List);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
