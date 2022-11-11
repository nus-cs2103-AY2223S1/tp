package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;



/**
 * Filters and list all students in the student map
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all students with either tag, "
            + "module or assignment (one only!) that contain any of "
            + "the specified alphabets (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_TAG + "KEYWORD [MORE_KEYWORDS]...] "
            + "[" + PREFIX_MODULE + "KEYWORD [MORE_KEYWORDS]...] "
            + "[" + PREFIX_ASSIGNMENT + "KEYWORD [MORE_KEYWORDS]...]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "friends family";

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
