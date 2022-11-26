package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EXAM;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;

/**
 * Adds an exam to the exam list.
 */
public class AddExamCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD + ": Adds an exam to the exam list. "
            + "\n"
            + "Parameters: "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_EXAM_DESCRIPTION + "EXAMDESCRIPTION "
            + PREFIX_EXAM_DATE + "EXAMDATE "
            + "\n"
            + "Example: e " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_EXAM_DESCRIPTION + "Midterms "
            + PREFIX_EXAM_DATE + "20-12-2022";

    public static final String MESSAGE_SUCCESS = "New exam added: %1$s";

    private final Exam toAdd;

    /**
     * Creates a AddExamCommand to add the specified {@code Exam}
     */
    public AddExamCommand(Exam exam) {
        requireNonNull(exam);
        toAdd = exam;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(toAdd.getModule())) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        } else if (model.hasExam(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }
        model.addExam(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExamCommand // instanceof handles nulls
                && toAdd.equals(((AddExamCommand) other).toAdd));
    }
}
