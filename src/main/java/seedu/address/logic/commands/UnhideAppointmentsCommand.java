package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.HiddenPredicateSingleton;

/**
 * Hides all persons and their appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class UnhideAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "unhide";
    public static final String DESCRIPTOR_WORD = "appts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unhides all appointments which reason or tags"
            + " contain any of the specified keywords (case-insensitive) and displays\n"
            + "the appointments which are not hidden. Also able to hide by marked/unmarked appointments\n"
            + "Parameters: [" + PREFIX_REASON + "REASON [MORE_REASONS]]\n"
            + "OR: [" + PREFIX_TAG + "TAG [MORE_TAGS]]\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " " + PREFIX_REASON + "pain infection";

    private Predicate<Appointment> predicate;

    public UnhideAppointmentsCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Appointment> combinedPredicate =
                HiddenPredicateSingleton.combineWithUnhiddenApptPredicate(predicate);
        model.updateFilteredAppointmentList(combinedPredicate);

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

        if (!(other instanceof UnhideAppointmentsCommand)) {
            return false;
        }

        UnhideAppointmentsCommand otherCommand = (UnhideAppointmentsCommand) other;
        return otherCommand.predicate.equals(this.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate);
    }
}
