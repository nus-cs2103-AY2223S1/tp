package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;

import java.util.function.Predicate;


/**
 * Filters and list all students in the student map
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all students with tags/module/assignment "
            + "that contain any of "
            + "the specified alphabets (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + PREFIX_TAG + " friends";

    private final Predicate<Student> predicate;

    /**
     * Constructor for FilterCommand
     * @param predicate
     */
    public FilterCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.filterStudentListWithPredicate(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }
}
