package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.CliSyntax;
import seedu.classify.model.Model;
import seedu.classify.model.student.ClassPredicate;
import seedu.classify.model.student.GradeComparator;
import seedu.classify.model.student.GradeLessThanMeanPredicate;

public class ViewStatsCommand extends Command {

    public static final String COMMAND_WORD = "viewstats";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieves the grades of the specified exam from the"
            + " students in the specified class, calculates the mean score within the class, then sorts the class list"
            + " according to grade. If filter tag is on, only students whose grade falls below the mean will be"
            + " displayed.\n"
            + "Parameters: " + CliSyntax.PREFIX_CLASS + "CLASS " + CliSyntax.PREFIX_EXAM + "EXAM filter/FILTER\n"
            + "Example: " + COMMAND_WORD + " class/4A1 exam/SA1 filter/on";

    private final ClassPredicate predicate;
    private final String className;
    private final String exam;
    private final boolean isFilterOn;

    public ViewStatsCommand(ClassPredicate predicate, String className, String exam, boolean isFilterOn) {
        this.predicate = predicate;
        this.className = className.toUpperCase();
        this.exam = exam.toUpperCase();
        this.isFilterOn = isFilterOn;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        double mean = model.calculateExamMean(exam);
        model.sortFilteredStudentList(new GradeComparator(exam));
        if (isFilterOn) {
            model.updateFilteredStudentList(new GradeLessThanMeanPredicate(className, mean, exam));
        }
        return new CommandResult(String.format(Messages.MESSAGE_CLASS_SORTED_BY_GRADE, className)
                + String.format(Messages.MESSAGE_DISPLAY_MEAN, exam, className, mean));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStatsCommand // instanceof handles nulls
                && predicate.equals(((ViewStatsCommand) other).predicate)); // state check
    }
}
