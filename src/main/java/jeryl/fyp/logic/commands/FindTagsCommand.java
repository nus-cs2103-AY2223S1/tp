package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTagsCommand extends Command {

    public static final String COMMAND_WORD = "find-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neural network/tree";

    private final TagsContainKeywordsPredicate tagsPredicate;

    /**
     * Takes in a predicate (to be specified)
     * @param tagsPredicate predicate on whether student's tags contains keyword
     */
    public FindTagsCommand(TagsContainKeywordsPredicate tagsPredicate) {
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(tagsPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagsCommand // instanceof handles nulls
                && tagsPredicate.equals(((FindTagsCommand) other).tagsPredicate)); // state check
    }
}
