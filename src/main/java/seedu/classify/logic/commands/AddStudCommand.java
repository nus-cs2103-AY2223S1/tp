package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.CliSyntax;
import seedu.classify.model.Model;
import seedu.classify.model.student.Student;


/**
 * Adds a student into the record.
 */
public class AddStudCommand extends Command {

    public static final String COMMAND_WORD = "addstud";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student into the record. "
            + "Parameters: "
            + CliSyntax.PREFIX_STUDENT_NAME + "STUDENT NAME "
            + CliSyntax.PREFIX_ID + "ID "
            + CliSyntax.PREFIX_CLASS + "CLASS "
            + "[" + CliSyntax.PREFIX_PARENT_NAME + "PARENT NAME] "
            + "[" + CliSyntax.PREFIX_PHONE + "PARENT PHONE NUMBER] "
            + "[" + CliSyntax.PREFIX_EMAIL + "PARENT EMAIL] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_STUDENT_NAME + "John Doe "
            + CliSyntax.PREFIX_ID + "928C "
            + CliSyntax.PREFIX_CLASS + "1A "
            + CliSyntax.PREFIX_PARENT_NAME + "Bob Doe "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + CliSyntax.PREFIX_EMAIL + "bobdoe@gmail.com "
            + CliSyntax.PREFIX_TAG + "Allergy";
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
