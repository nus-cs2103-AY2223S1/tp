package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Finds and lists all students in the Student Record whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students based on the specified search"
            + " keywords (case-insensitive) and displays them as a list with index numbers and all fields. Search"
            + " for a student using parts of their name or their ID.\n"
            + "Parameters: " + PREFIX_STUDENT_NAME + "NAME TO SEARCH or " + PREFIX_ID + "ID TO SEARCH\n"
            + "Example: " + COMMAND_WORD + " nm/alice";

    private final Predicate<Student> predicate;

    public ViewCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStudentListInfoConcise(false);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
