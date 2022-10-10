package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getCurrentListType() == Model.ListType.STUDENT_LIST) {
            model.updateFilteredStudentList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));

        }

        if (model.getCurrentListType() == Model.ListType.TUTOR_LIST) {
            model.updateFilteredTutorList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW, model.getFilteredTutorList().size()));

        }

        if (model.getCurrentListType() == Model.ListType.PERSON_LIST) {
            model.updateFilteredPersonList(predicate);
            return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                    model.getFilteredPersonList().size()));

        }

        assert (model.getCurrentListType() == Model.ListType.TUITIONCLASS_LIST);
        model.updateFilteredTuitionClassList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW,
                model.getFilteredTuitionClassList().size()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
