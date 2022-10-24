package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;
import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose project names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neural network/tree";

    private final ProjectNameContainsKeywordsPredicate projectNamePredicate;
    private final TagsContainKeywordsPredicate tagsPredicate;

    /**
     * Takes in two predicates to check if projectName/tags contain keyword (to be specified)
     * @param projectNamePredicate predicate on whether project name contains keyword
     * @param tagsPredicate predicate on whether tags contain keyword
     */
    public FindCommand(ProjectNameContainsKeywordsPredicate projectNamePredicate,
                       TagsContainKeywordsPredicate tagsPredicate) {
        this.projectNamePredicate = projectNamePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.updateFilteredStudentList(projectNamePredicate);
        model.updateFilteredStudentList(tagsPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && projectNamePredicate.equals(((FindCommand) other).projectNamePredicate)
                && tagsPredicate.equals(((FindCommand) other).tagsPredicate)); // state check
    }
}
