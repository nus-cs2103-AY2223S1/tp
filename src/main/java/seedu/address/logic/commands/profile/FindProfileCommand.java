package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.profile.NameContainsKeywordsPredicate;

/**
 * Finds and lists all profiles in NUScheduler whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindProfileCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "f";
    public static final String MESSAGE_PROFILE_LISTED_OVERVIEW = "1 profile listed!";
    public static final String MESSAGE_NO_MATCH = "There are no matching results!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Finds all profiles whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " alice bob charlie";

    public static final String MESSAGE_HELP = "Finds profiles matching the keywords. "
            + "Case insensitive and will return partial matches.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " KEYWORDS [MORE KEYWORDS]";

    private final NameContainsKeywordsPredicate predicate;

    public FindProfileCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProfileList(predicate);
        int size = model.getFilteredProfileList().size();
        switch (size) {
        case 0:
            return new CommandResult(MESSAGE_NO_MATCH);
        case 1:
            return new CommandResult(MESSAGE_PROFILE_LISTED_OVERVIEW);
        default:
            return new CommandResult(String.format(Messages.MESSAGE_PROFILES_LISTED_OVERVIEW, size));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProfileCommand // instanceof handles nulls
                && predicate.equals(((FindProfileCommand) other).predicate)); // state check
    }
}
