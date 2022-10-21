package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.AppointmentByDatePredicate;

/**
 * Finds and lists all patients on the same appointment date.
 *
 */
public class GetAppointmentByDateCommand extends GetCommand {

    public static final String APPOINTMENT_BY_DATE_PREFIX = "/apptbd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all patients who are on the same appointment"
            + "date "
            + "and displays them as a list with index numbers. \n"
            + "Parameters: "
            + APPOINTMENT_BY_DATE_PREFIX + " DATE [dd-MM-yyyy]\n"
            + "Example: "
            + COMMAND_WORD + " "
            + APPOINTMENT_BY_DATE_PREFIX
            + " 21-12-2020";

    private final AppointmentByDatePredicate predicate;

    /**
     * Constructor for GetAppointmentByDateCommand
     * @param predicate predicate for appointments.
     */
    public GetAppointmentByDateCommand(AppointmentByDatePredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command.
     * @param model {@code Model} which the command should operate on.
     * @return the result of the executed command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Compares the similarity of two objects.
     * @param other the object to be compared
     * @return true if they are equal objects, otherwise false
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetAppointmentByDateCommand // instanceof handles nulls
                && predicate.equals(((GetAppointmentByDateCommand) other).predicate)); // state check
    }
}

