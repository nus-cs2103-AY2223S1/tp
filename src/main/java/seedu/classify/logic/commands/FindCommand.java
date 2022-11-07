package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.parser.CliSyntax;
import seedu.classify.model.Model;
import seedu.classify.model.student.Student;

/**
 * Finds and lists all students in the Student Record whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students based on the specified search"
            + " keywords (case-insensitive) and displays them as a list. Search"
            + " for a student using parts of their name or their ID.\n"
            + "Parameters: " + CliSyntax.PREFIX_STUDENT_NAME + "NAME TO SEARCH or "
            + CliSyntax.PREFIX_ID + "ID TO SEARCH\n" + "Example: " + COMMAND_WORD + " nm/alice";

    private final Predicate<Student> predicate;

    public FindCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.storePredicate(predicate);
        model.updateFilteredStudentList(predicate);
        int numberOfStudents = model.getFilteredStudentList().size();
        if (numberOfStudents == 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_SINGLE_PERSON_LISTED_OVERVIEW,
                            model.getFilteredStudentList().size()));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
