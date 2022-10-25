package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.MainPanelName;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

/**
 * Sorts and lists all person in address book with comparator given.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons with specified fields\n"
            + "Parameters: [" + buildParameters(PREFIX_NAME, PREFIX_ROLE, PREFIX_ADDRESS,
                PREFIX_TELEGRAM, PREFIX_SLACK, PREFIX_PHONE, PREFIX_EMAIL) + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME.getPrefix().replace("/", "");

    private final Comparator<Person> comparator;

    private static String buildParameters(Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .map(s -> s.getPrefix().replace("/", ""))
                .collect(Collectors.joining("|"));
    }

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedFilteredPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getSortedFilteredPersonList().size()));
    }

    @Override
    public boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.List);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
