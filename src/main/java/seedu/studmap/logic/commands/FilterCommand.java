package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.AssignmentContainsKeywordsPredicate;
import seedu.studmap.model.student.ModuleContainsKeywordsPredicate;
import seedu.studmap.model.student.TagContainsKeywordsPredicate;


/**
 * Filters and list all students in the student map
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all students with tags/module/assignment "
            + "that contain any of "
            + "the specified alphabets (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "t/" + " friends";

    private final TagContainsKeywordsPredicate tPredicate;

    private final ModuleContainsKeywordsPredicate mPredicate;

    private final AssignmentContainsKeywordsPredicate aPredicate;

    /**
     * Constructor for FilterCommand
     * @param tPredicate
     * @param mPredicate
     * @param aPredicate
     */
    public FilterCommand(TagContainsKeywordsPredicate tPredicate, ModuleContainsKeywordsPredicate mPredicate,
                         AssignmentContainsKeywordsPredicate aPredicate) {
        this.tPredicate = tPredicate;
        this.mPredicate = mPredicate;
        this.aPredicate = aPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        if (tPredicate == null && aPredicate == null) {
            requireNonNull(mPredicate);
            model.filterStudentListWithModule(mPredicate);
        } else if (mPredicate == null && aPredicate == null) {
            requireNonNull(tPredicate);
            model.filterStudentListWithTag(tPredicate);
        } else {
            requireNonNull(aPredicate);
            model.filterStudentListWithAssignment(aPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }
}
