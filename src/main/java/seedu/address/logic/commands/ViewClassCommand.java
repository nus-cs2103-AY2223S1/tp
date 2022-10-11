package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.ClassPredicate;

/**
 * Lists all the students who are in a particular class.
 * Keyword matching is case insensitive.
 */
public class ViewClassCommand extends Command {

    public static final String COMMAND_WORD = "viewClass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students in the given class.\n"
            + "Parameters: CLASSNAME\n"
            + "Example: " + COMMAND_WORD + " class1A";

    private final ClassPredicate predicate;

    public ViewClassCommand(ClassPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_IN_CLASS, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClassCommand // instanceof handles nulls
                && predicate.equals(((ViewClassCommand) other).predicate)); // state check
    }
}
