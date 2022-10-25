package jeryl.fyp.logic.commands;

import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose project name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindProjectNameCommand extends FindCommand {

    public static final String COMMAND_WORD = "find-proj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose project names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neural network/tree";

    /**
     * Takes in a predicate (to be specified)
     * @param projectNamePredicate predicate on whether student's projectName contains keyword
     */
    public FindProjectNameCommand(ProjectNameContainsKeywordsPredicate projectNamePredicate) {
        super(projectNamePredicate);
    }
}
