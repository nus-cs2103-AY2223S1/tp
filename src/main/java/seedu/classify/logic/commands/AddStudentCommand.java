package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.classify.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;

import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.model.Model;
import seedu.classify.model.student.Student;


/**
 * Adds a student into the record.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "addStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student into the record. "
            + "Parameters: "
            + PREFIX_STUDENT_NAME + "STUDENT NAME "
            + PREFIX_ID + "ID "
            + PREFIX_CLASS + "CLASS "
            + "[" + PREFIX_PARENT_NAME + "PARENT NAME] "
            + "[" + PREFIX_PHONE + "PARENT PHONE NUMBER] "
            + "[" + PREFIX_EMAIL + "PARENT EMAIL] "
            + "[" + PREFIX_EXAM + "NAME SCORE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NAME + "John Doe "
            + PREFIX_ID + "928C "
            + PREFIX_CLASS + "1A "
            + PREFIX_PARENT_NAME + "Bob Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "bobdoe@gmail.com "
            + PREFIX_EXAM + "CA1 50";
    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the record";

    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
