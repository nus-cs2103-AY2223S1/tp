package jeryl.fyp.logic.commands;

import jeryl.fyp.model.student.StudentNameContainsKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindStudentNameCommand extends FindCommand {

    public static final String COMMAND_WORD = "find -n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD/[MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " neural network/tree";

    /**
     * Takes in a predicate (to be specified)
     * @param studentNamePredicate predicate on whether student's studentName contains keyword
     */
    public FindStudentNameCommand(StudentNameContainsKeywordsPredicate studentNamePredicate) {
        super(studentNamePredicate);
    }

}
