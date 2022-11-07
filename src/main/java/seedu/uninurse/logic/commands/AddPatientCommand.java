package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.uninurse.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.person.Patient;

/**
 * Adds a patient to the patient list.
 */
public class AddPatientCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient.\n"
            + "Format: " + COMMAND_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION | <DATE TIME> | <INTERVAL TIME_PERIOD>]... "
            + "[" + PREFIX_CONDITION + "CONDITION]... "
            + "[" + PREFIX_MEDICATION + "MEDICATION_TYPE | DOSAGE]... "
            + "[" + PREFIX_REMARK + "REMARK]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing | 1-1-22 1345 | 2 days "
            + PREFIX_TASK_DESCRIPTION + "Take X-rays "
            + PREFIX_MEDICATION + "Amoxicillin | 0.5 g every 8 hours";
    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final CommandType COMMAND_TYPE = CommandType.ADD_PATIENT;

    private final Patient toAdd;

    /**
     * Creates an AddPatientCommand to add the specified patient.
     *
     * @param patient The given patient to be added.
     */
    public AddPatientCommand(Patient patient) {
        requireAllNonNull(patient);
        this.toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (model.hasPerson(toAdd)) {
            model.setPatientOfInterest(toAdd);
            throw new DuplicateEntryException(Messages.MESSAGE_DUPLICATE_PATIENT);
        }

        PersonListTracker personListTracker = model.addPatient(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.setPatientOfInterest(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                COMMAND_TYPE, personListTracker);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientCommand // instanceof handles nulls
                && toAdd.equals(((AddPatientCommand) other).toAdd));
    }
}
