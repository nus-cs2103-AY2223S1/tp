package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.note.NoteTagsContainsKeywordsPredicate;
import seedu.address.model.person.PersonTagsContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book who are tagged with any of the argument tags.
 * Tag matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are tagged with any of"
            + "the specified tags (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " Finance Tech";

    private final PersonTagsContainsKeywordsPredicate personPredicate;
    private final NoteTagsContainsKeywordsPredicate notePredicate;

    /**
     * Constructs a FindTagCommand with the given {@code PersonTagsContainsKeywordsPredicate}
     * and {@code NoteTagsContainsKeywordsPredicate}
     */
    public FindTagCommand(PersonTagsContainsKeywordsPredicate personPredicate,
                          NoteTagsContainsKeywordsPredicate notePredicate) {
        this.personPredicate = personPredicate;
        this.notePredicate = notePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(personPredicate);
        model.updateFilteredNoteList(notePredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && personPredicate.equals(((FindTagCommand) other).personPredicate)
                && notePredicate.equals(((FindTagCommand) other).notePredicate)); // state check
    }
}
