package seedu.modquik.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.function.Predicate;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.student.Student;


/**
 * Finds and lists all persons in ModQuik whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds students by names, student ID, module or "
            + "tutorial, by checking if respective attribute contains any of the given keywords.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "STUDENT_ID] "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_TUTORIAL + "TUTORIAL]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John";
    private final Predicate<Student> predicate;

    public FindCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()),
                ModelType.STUDENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
