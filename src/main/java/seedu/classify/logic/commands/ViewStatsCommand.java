package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.CliSyntax;
import seedu.classify.model.Model;
import seedu.classify.model.student.Student;

import java.util.function.Predicate;


public class ViewStatsCommand extends Command {

    public static final String COMMAND_WORD = "viewStats";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieves the grades of the specified exam from the"
            + " students in the specified class, and calculates the mean score within the class. Then, the class list"
            + " is displayed with the students sorted according to grade.\n"
            + "Parameters: " + CliSyntax.PREFIX_CLASS + "CLASS " + CliSyntax.PREFIX_EXAM + "EXAM\n"
            + "Example: " + COMMAND_WORD + " class/4A1 exam/SA1";

    private final Predicate<Student> predicate;

    public ViewStatsCommand(Predicate<Student> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStatsCommand // instanceof handles nulls
                && predicate.equals(((ViewStatsCommand) other).predicate)); // state check
    }
}
