package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;
//import jeryl.fyp.model.student.TagsContainKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindProjectNameCommand extends Command {

    public static final String COMMAND_WORD = "find-proj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose project names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neural network/tree";

    private final ProjectNameContainsKeywordsPredicate projectNamePredicate;
    //private final TagsContainKeywordsPredicate tagsPredicate;

    /**
     * Takes in a predicate (to be specified)
     * @param projectNamePredicate predicate on whether student's projectName contains keyword
     */
    public FindProjectNameCommand(ProjectNameContainsKeywordsPredicate projectNamePredicate) {
        this.projectNamePredicate = projectNamePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(projectNamePredicate);
        //model.updateFilteredStudentList(tagsPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectNameCommand // instanceof handles nulls
                && projectNamePredicate.equals(((FindProjectNameCommand) other).projectNamePredicate)); // state check
    }
}
