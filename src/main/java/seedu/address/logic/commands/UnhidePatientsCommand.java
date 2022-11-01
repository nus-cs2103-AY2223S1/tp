package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.predicates.HiddenPredicateSingleton;

/**
 * Unhides all persons and their appointments in idENTify whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class UnhidePatientsCommand extends Command {

    public static final String COMMAND_WORD = "unhide";
    public static final String DESCRIPTOR_WORD = "patients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unhides (shows) previously "
            + "hidden patients whose names or tags contain any of the\n"
            + "specified keywords (case-insensitive) and displays "
            + "them and their appointments as 2 lists with index numbers.\n"
            + "Parameters: " + PREFIX_NAME + "NAME [n/MORE_NAMES]...\n"
            + "OR: " + PREFIX_TAG + "TAG [t/MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " n/alice n/bob n/charlie\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " t/ear t/nose";

    private Predicate<Person> predicate;

    public UnhidePatientsCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> combinedPredicate = HiddenPredicateSingleton.combineWithUnhiddenPredicate(predicate);
        model.updateFilteredPersonList(combinedPredicate);
        List<Person> validPersons = model.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);

        Predicate<Appointment> combinedApptPredicate =
                HiddenPredicateSingleton.combineWithRegularApptPredicate(appointmentPredicate);

        model.updateFilteredAppointmentList(combinedApptPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnhidePatientsCommand)) {
            return false;
        }

        UnhidePatientsCommand otherCommand = (UnhidePatientsCommand) other;
        return otherCommand.predicate.equals(this.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate);
    }
}
