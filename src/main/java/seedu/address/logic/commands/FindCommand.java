package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Finds and lists all modules and persons in address book that contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE;

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds all Modules and Persons which contain any of "
        + "the specified keywords (not case-sensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " cs2100";

    private final List<String> keywords;

    /**
     * Constructor
     *
     * @param keywords keywords to filter lists by.
     */
    public FindCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        new FindPersonCommand(keywords).execute(model);
        new FindModuleCommand(keywords).execute(model);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
            model.getFilteredPersonList().size())
            + " " + String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW,
            model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && keywords.equals(((FindCommand) other).keywords)); // state check
    }
}
