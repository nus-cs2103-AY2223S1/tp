package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_PERSON;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonDetailsContainsKeywordsPredicate;


/**
 * Finds and lists all persons in address book whose fields contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_TYPE = "find";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_PERSON;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
        + "the specified keywords (not case-sensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice 97018232 cs2100";

    private final List<String> keywords;

    public FindPersonCommand(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new PersonDetailsContainsKeywordsPredicate(keywords));
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindPersonCommand // instanceof handles nulls
            && keywords.equals(((FindPersonCommand) other).keywords)); // state check
    }
}
