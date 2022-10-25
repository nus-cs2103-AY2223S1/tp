package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.predicates.HiddenPredicateSingleton;

/**
 * Unhides all persons and their appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class UnhidePatientsCommand extends Command {

    public static final String COMMAND_WORD = "unhide";
    public static final String DESCRIPTOR_WORD = "patients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unhides all persons whose names or tags contain\n"
            + "any of the specified keywords (case-insensitive) and displays them and \n"
            + "their appointments as 2 lists with index numbers. Also able to unhide by patient tags.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME [MORE_NAMES]]\n"
            + "OR: " + "[" + PREFIX_TAG + "TAG [MORE_TAGS]]...\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " alice bob charlie";

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
        model.updateFilteredAppointmentList(appointmentPredicate);

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
