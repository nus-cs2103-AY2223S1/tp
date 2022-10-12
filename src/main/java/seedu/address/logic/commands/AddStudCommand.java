package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Adds a student into the record.
 */
public class AddStudCommand extends Command {

    public static final String COMMAND_WORD = "addstud";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student into the record. "
            + "Parameters: "
            + PREFIX_STUDENT_NAME + "STUDENT NAME "
            + PREFIX_ID + "ID "
            + PREFIX_CLASS + "CLASS "
            + PREFIX_PARENT_NAME + "PARENT NAME "
            + PREFIX_PHONE + "PHONE NUMBER "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NAME + "John Doe "
            + PREFIX_ID + "928C "
            + PREFIX_CLASS + "1A "
            + PREFIX_PARENT_NAME + "Bob Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "Allergy";
    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the record";

    private final Student toAdd;

    /**
     * Creates an AddStudCommand to add the specified {@code Student}
     */
    public AddStudCommand(Student person) {
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
                || (other instanceof AddStudCommand // instanceof handles nulls
                && toAdd.equals(((AddStudCommand) other).toAdd));
    }
}
