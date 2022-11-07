package jeryl.fyp.logic.commands;

import static java.util.Objects.requireNonNull;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.student.FieldContainsKeywordsPredicate;

/**
 * Finds and lists all students in FYP manager whose field contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String MESSAGE_USAGE = "Finds all students matching the given name of a certain field.\n"
            + "Examples:\n"
            + "1) by ProjectName: " + FindProjectNameCommand.COMMAND_WORD + " KEYWORD/[KEYWORD2/KEYWORD3/...]\n"
            + "2) by StudentId: " + FindStudentIdCommand.COMMAND_WORD + " KEYWORD/[KEYWORD2/KEYWORD3/...]\n"
            + "3) by StudentName: " + FindStudentNameCommand.COMMAND_WORD + " KEYWORD/[KEYWORD2/KEYWORD3/...]\n"
            + "4) by Tags: " + FindTagsCommand.COMMAND_WORD + " KEYWORD/[KEYWORD2/KEYWORD3/...]";

    private final FieldContainsKeywordsPredicate fieldPredicate;

    /**
     * Takes in a predicate (to be specified)
     * @param fieldPredicate predicate on whether student's projectName contains keyword
     */
    public FindCommand(FieldContainsKeywordsPredicate fieldPredicate) {
        this.fieldPredicate = fieldPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(fieldPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && fieldPredicate.equals(((FindCommand) other).fieldPredicate)); // state check
    }

}
