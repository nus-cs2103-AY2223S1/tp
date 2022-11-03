package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.person.Patient;

/**
 * Adds a person to the uninurse book.
 */
public class AddPatientCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the uninurse book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_CONDITION + "CONDITION]... "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_CONDITION + "Cerebral palsy "
            + PREFIX_MEDICATION + "Amoxicillin | 0.5 g every 8 hours "
            + PREFIX_TASK_DESCRIPTION + "Refer to rehabilitation therapist "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "highRisk";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the uninurse book";

    public static final CommandType ADD_PATIENT_COMMAND_TYPE = CommandType.ADD_PATIENT;

    private final Patient toAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Patient}
     */
    public AddPatientCommand(Patient person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        PatientListTracker patientListTracker = model.addPerson(toAdd);
        model.setPatientOfInterest(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                ADD_PATIENT_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                && toAdd.equals(((AddPatientCommand) other).toAdd));
    }
}
