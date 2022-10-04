package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a patient to the patient list.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "addPatient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the patient list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_TASK_DESCRIPTION + "TASK DESCRIPTION ";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the patient list";

    private final Person toAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Person}
     *
     * @param patient to be added
     */
    public AddPatientCommand(Person patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                && toAdd.equals(((AddPatientCommand) other).toAdd));
    }
}
