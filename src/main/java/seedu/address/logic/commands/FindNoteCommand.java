package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.note.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all notes in address book whose title contains any of the argument keywords.
 * Keyword matching is case insensitive and ignores special characters in the title.
 * Keyword must not be only special characters. Numbers are allowed.
 */
public class FindNoteCommand extends Command {

    public static final String COMMAND_WORD = "findNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all notes whose title contain any of "
            + "the specified keywords (case-insensitive, ignores special characters) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " club meeting";

    private final TitleContainsKeywordsPredicate predicate;

    public FindNoteCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredNoteList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_NOTES_LISTED_OVERVIEW, model.getFilteredNoteList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNoteCommand // instanceof handles nulls
                && predicate.equals(((FindNoteCommand) other).predicate)); // state check
    }

}
