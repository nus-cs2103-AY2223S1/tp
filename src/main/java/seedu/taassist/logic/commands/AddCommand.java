package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.model.Model;
import seedu.taassist.model.student.Student;

/**
 * Adds a student to TA-Assist.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "> Adds a student to TA-Assist.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_MODULE_CLASS + "CLASS...]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "Prince George Park Residence 4 "
            + PREFIX_MODULE_CLASS + "CS1101S "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Added new student: [ %1$s ]";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Student [ %1$s ] already exists in TA-Assist!";

    private final Student student;

    /**
     * Creates an AddCommand to add the specified {@code Student}.
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        this.student = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(student)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STUDENT, student.getName()));
        }

        if (!model.hasModuleClasses(student.getModuleClasses())) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        model.addStudent(student);
        return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && student.equals(((AddCommand) other).student));
    }
}
