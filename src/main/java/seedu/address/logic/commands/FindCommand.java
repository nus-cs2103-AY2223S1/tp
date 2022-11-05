package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.predicates.CombinedAppointmentPredicate;
import seedu.address.model.person.predicates.CombinedPersonPredicate;
import seedu.address.model.person.predicates.HiddenPredicateSingleton;

/**
 * Finds and lists all persons and their appointments in idENTify whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons and their appointments whose fields"
            + " contain all of the fields specified (case-insensitive) and displays them.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_REASON + "REASON] "
            + "[" + PREFIX_DATE_TIME_START + "DATE_START] "
            + "[" + PREFIX_DATE_TIME_END + "DATE_END]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_PHONE + "9876 "
            + PREFIX_EMAIL + "johnd "
            + PREFIX_ADDRESS + "Clementi "
            + PREFIX_TAG + "ear "
            + PREFIX_TAG + "nose "
            + PREFIX_REASON + "checkup "
            + PREFIX_DATE_TIME_START + "2022-10-20 00:00 "
            + PREFIX_DATE_TIME_END + "2023-10-20 00:00";

    private final CombinedPersonPredicate personPredicate;
    private final CombinedAppointmentPredicate appointmentPredicate;
    private final boolean isUsingAppointmentPredicate;

    /**
     * Creates a findCommand to search for specific details in the app.
     *
     * @param personPredicate A predicate for searching person details.
     * @param appointmentPredicate A predicate for searching appointment details.
     * @param isUsingAppointmentPredicate True if any search fields relating to appointments are entered,
     *                                   false otherwise.
     */
    public FindCommand(CombinedPersonPredicate personPredicate, CombinedAppointmentPredicate appointmentPredicate,
                       boolean isUsingAppointmentPredicate) {
        this.personPredicate = personPredicate;
        this.appointmentPredicate = appointmentPredicate;
        this.isUsingAppointmentPredicate = isUsingAppointmentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        /*
        Finds all persons that satisfies the given personPredicate with at least one appointment that matches
        the appointmentPredicate if any input related to appointments are present (Reason, date),
        and updates the model accordingly.
         */
        Predicate<Person> personFulfillingBothPredicates = !isUsingAppointmentPredicate
                ? personPredicate
                : personPredicate.and(person -> person.getAppointments().stream().anyMatch(appointmentPredicate));

        /*
        Creates a Predicate<Appointment> that returns true if an appointment's patient is one who satisfies
        the above predicate.
         */
        List<Person> validPersons = model.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentOfFilteredPersonsPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);

        /*
        Finds all appointments that satisfy the given appointmentPredicate whose patient matches the personPredicate,
        and updates the model accordingly.
         */
        Predicate<Appointment> appointmentFulfillingBothPredicates =
                appointmentOfFilteredPersonsPredicate.and(appointmentPredicate);

        Predicate<Person> combinedPersonPredicate =
                HiddenPredicateSingleton.combineWithRegularPredicate(personFulfillingBothPredicates);

        Predicate<Appointment> combinedApptPredicate =
                HiddenPredicateSingleton.combineWithRegularApptPredicate(appointmentFulfillingBothPredicates);

        model.updateFilteredPersonList(combinedPersonPredicate);
        model.updateFilteredAppointmentList(combinedApptPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        FindCommand otherFindCommand = (FindCommand) other;
        return personPredicate.equals(otherFindCommand.personPredicate)
                && appointmentPredicate.equals(otherFindCommand.appointmentPredicate)
                && isUsingAppointmentPredicate == otherFindCommand.isUsingAppointmentPredicate;
    }
}
